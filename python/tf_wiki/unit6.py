import tensorflow as tf

from unit5 import MNISTLoader


# CNN模型
class CNN(tf.keras.Model):
    def __init__(self):
        super().__init__()
        # 调用tf.keras的2D卷积层定义所需的卷积层
        self.conv1 = tf.keras.layers.Conv2D(
            filters=32,             # 卷积层神经元（卷积核）数目
            kernel_size=[5, 5],     # 感受野大小
            padding='same',         # padding策略（vaild 或 same）
            activation=tf.nn.relu   # 激活函数
        )
        self.pool1 = tf.keras.layers.MaxPool2D(pool_size=[2, 2], strides=2)  # 池化层
        self.conv2 = tf.keras.layers.Conv2D(
            filters=64,
            kernel_size=[5, 5],
            padding='same',
            activation=tf.nn.relu
        )
        self.pool2 = tf.keras.layers.MaxPool2D(pool_size=[2, 2], strides=2)
        # 展平后接两个全连接层
        self.flatten = tf.keras.layers.Reshape(target_shape=(7 * 7 * 64,))
        self.dense1 = tf.keras.layers.Dense(units=1024, activation=tf.nn.relu)
        self.dense2 = tf.keras.layers.Dense(units=10)

    def call(self, inputs):
        x = self.conv1(inputs)                  # [batch_size, 28, 28, 32]
        x = self.pool1(x)                       # [batch_size, 14, 14, 32]
        x = self.conv2(x)                       # [batch_size, 14, 14, 64]
        x = self.pool2(x)                       # [batch_size, 7, 7, 64]
        x = self.flatten(x)                     # [batch_size, 7 * 7 * 64]
        x = self.dense1(x)                      # [batch_size, 1024]
        x = self.dense2(x)                      # [batch_size, 10]
        output = tf.nn.softmax(x)
        return output

if __name__ == '__main__':
    # 设定运行参数
    num_epochs = 5
    batch_size = 50
    learning_rate = 0.001

    # 创建模型、数据获取器、优化器
    model = CNN()
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