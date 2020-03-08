import tensorflow as tf
import numpy as np


# 在线下载MNIST数据集并进行处理
class MNISTLoader():
    def __init__(self):
        mnist = tf.keras.datasets.mnist  # 下载数据集
        (self.train_data, self.train_label), (self.test_data, self.test_label) = mnist.load_data()
        # MNIST中的图像默认为uint8（0-255的数字）。以下代码将其归一化到0-1之间的浮点数，并在最后增加一维作为颜色通道
        self.train_data = np.expand_dims(self.train_data.astype(np.float32) / 255.0, axis=-1)      # [60000, 28, 28, 1]
        self.test_data = np.expand_dims(self.test_data.astype(np.float32) / 255.0, axis=-1)        # [10000, 28, 28, 1]
        self.train_label = self.train_label.astype(np.int32)    # [60000]
        self.test_label = self.test_label.astype(np.int32)      # [10000]
        self.num_train_data, self.num_test_data = self.train_data.shape[0], self.test_data.shape[0]

    def get_batch(self, batch_size):
        # 从数据集中随机取出batch_size个元素并返回
        index = np.random.randint(0, np.shape(self.train_data)[0], batch_size)
        return self.train_data[index, :], self.train_label[index]


# 定义一个MLP模型
class MLP(tf.keras.Model):
    def __init__(self):
        super().__init__()
        # 定义需要的层
        self.flatten = tf.keras.layers.Flatten()  # Flatten层将除第一维（batch_size）以外的维度展平，即将一个2D图片转换为一个1D序列
        self.dense1 = tf.keras.layers.Dense(units=100, activation=tf.nn.relu)
        self.dense2 = tf.keras.layers.Dense(units=10)

    # 对各个层进行连接
    def call(self, inputs):         # [batch_size, 28, 28, 1]
        x = self.flatten(inputs)    # [batch_size, 784]
        x = self.dense1(x)          # [batch_size, 100]
        x = self.dense2(x)          # [batch_size, 10]
        output = tf.nn.softmax(x)
        return output


if __name__ == '__main__':
    # 设定运行参数
    num_epochs = 5
    batch_size = 50
    learning_rate = 0.001

    # 创建模型、数据获取器、优化器
    model = MLP()
    data_loader = MNISTLoader()
    optimizer = tf.keras.optimizers.Adam(learning_rate=learning_rate)

    # 训练
    num_batches = int(data_loader.num_train_data // batch_size * num_epochs)  # 获取总共可用的batch数
    for batch_index in range(num_batches):
        X, y = data_loader.get_batch(batch_size)
        with tf.GradientTape() as tape:
            y_pred = model(X)
            loss = tf.keras.losses.sparse_categorical_crossentropy(y_true=y, y_pred=y_pred)  # 交叉熵损失
            loss = tf.reduce_mean(loss)  # reduce_mean：沿指定维度计算均值，默认为维度0
            print("batch %d: 损失 %f" % (batch_index, loss.numpy()))
        grads = tape.gradient(loss, model.variables)  # 自动获得每个变量对于loss 的梯度
        optimizer.apply_gradients(grads_and_vars=zip(grads, model.variables))

    # 模型评估：tf.keras.metrics
    sparse_categorical_accuracy = tf.keras.metrics.SparseCategoricalAccuracy()  # 创建评估器
    num_batches = int(data_loader.num_test_data // batch_size)  # 计算可用batch 数
    for batch_index in range(num_batches):
        start_index, end_index = batch_index * batch_size, (batch_index + 1) * batch_size
        y_pred = model.predict(data_loader.test_data[start_index: end_index])  # 进行预测
        # 调用评估器进行评估，评估器接受y_true和y_pred两个参数
        sparse_categorical_accuracy.update_state(y_true=data_loader.test_label[start_index: end_index], y_pred=y_pred)
    print("测试正确率: %f" % sparse_categorical_accuracy.result())
