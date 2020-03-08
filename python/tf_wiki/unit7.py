import tensorflow as tf
import numpy as np
# RNN模型


# 数据加载器
class DataLoader():
    def __init__(self):
        path = tf.keras.utils.get_file('nietzsche.txt',
            origin='https://s3.amazonaws.com/text-datasets/nietzsche.txt')
        with open(path, encoding='utf-8') as f:
            self.raw_text = f.read().lower()
        self.chars = sorted(list(set(self.raw_text)))  # 获取文本中的全字符
        self.char_indices = dict((c, i) for i, c in enumerate(self.chars))  # 对字符进行编码，下同（枚举）
        self.indices_char = dict((i, c) for i, c in enumerate(self.chars))
        self.text = [self.char_indices[c] for c in self.raw_text]  # 将文本转换为编码

    def get_batch(self, seq_length, batch_size):
        seq = []  # 序列
        next_char = []  # 序列的下个字符
        for i in range(batch_size):
            index = np.random.randint(0, len(self.text) - seq_length)  # 在文本中随机挑选一个位置（ 尾部长度不小于句子长度）
            seq.append(self.text[index:index+seq_length])
            next_char.append(self.text[index+seq_length])
        return np.array(seq), np.array(next_char)       # [batch_size, seq_length], [num_batch]


# 自定义RNN
class RNN(tf.keras.Model):
    def __init__(self, num_chars, batch_size, seq_length):
        super().__init__()
        self.num_chars = num_chars  # 全字符数
        self.seq_length = seq_length  # 序列长度
        self.batch_size = batch_size
        self.cell = tf.keras.layers.LSTMCell(units=256)  # LSTM单元
        self.dense = tf.keras.layers.Dense(units=self.num_chars)  # 全连接

    def call(self, inputs, from_logits=False):
        # 调用tf的one-hot函数，根据depth对inputs进行one-hot化，axis默认为-1
        inputs = tf.one_hot(inputs, depth=self.num_chars)       # [batch_size, seq_length, num_chars]
        state = self.cell.get_initial_state(batch_size=self.batch_size, dtype=tf.float32)  # 创建一个初始state
        for t in range(self.seq_length):
            output, state = self.cell(inputs[:, t, :], state)
        logits = self.dense(output)
        if from_logits:
            return logits
        else:
            return tf.nn.softmax(logits)

    # 预测函数，temperature控制了输出概率分布的平缓程度，输出越多样性越多
    def predict(self, inputs, temperature=1.):
        batch_size, _ = tf.shape(inputs)
        logits = self(inputs, from_logits=True)  # from_logits=True：利用当前的模型的参数进行输出，若为False则是使用训练前的参数
        prob = tf.nn.softmax(logits / temperature).numpy()
        # np.random.choice
        return np.array([np.random.choice(self.num_chars, p=prob[i, :]) for i in range(batch_size.numpy())])


if __name__ == '__main__':
    num_batches = 1000
    seq_length = 40
    batch_size = 50
    learning_rate = 1e-3

    # 创建获取器、模型、优化器
    data_loader = DataLoader()
    model = RNN(num_chars=len(data_loader.chars), batch_size=batch_size, seq_length=seq_length)
    optimizer = tf.keras.optimizers.Adam(learning_rate=learning_rate)

    for batch_index in range(num_batches):
        X, y = data_loader.get_batch(seq_length, batch_size)
        with tf.GradientTape() as tape:
            y_pred = model(X)
            loss = tf.keras.losses.sparse_categorical_crossentropy(y_true=y, y_pred=y_pred)
            loss = tf.reduce_mean(loss)
            print("batch %d: loss %f" % (batch_index, loss.numpy()))
        grads = tape.gradient(loss, model.variables)  # 求各个偏导
        optimizer.apply_gradients(grads_and_vars=zip(grads, model.variables))  # 执行优化

    # 预测不直接取结果的最大值，而是按输出的各项概率取，保证结果的灵活性
    X_, _ = data_loader.get_batch(seq_length, 1)
    for diversity in [0.2, 0.5, 1.0, 1.2]:  # 输出多样性控制
        X = X_
        print("diversity %f:" % diversity)
        for t in range(400):
            y_pred = model.predict(X, diversity)  # 进行预测
            print(data_loader.indices_char[y_pred[0]], end='', flush=True)  # 根据预测的结果，输出其编号
            # 拼接输出结果，即下一轮拿延长后的序列进行读取
            X = np.concatenate([X[:, 1:], np.expand_dims(y_pred, axis=1)], axis=-1)
        print("\n")