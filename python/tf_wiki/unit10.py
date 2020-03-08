import tensorflow as tf


# 自定义层、损失函数、评估指标
# 创建一个自定义的线性回归层
class LinearLayer(tf.keras.layers.Layer):
    # 初始化代码，用于初始化和数据无关的一些基础参数
    def __init__(self, units):
        super().__init__()
        self.units = units

    # build函数用于创建和运行数据相关的参数，input_shape 是一个 TensorShape 类型对象，提供输入的形状
    def build(self, input_shape):
        # 在第一次使用该层的时候调用该部分代码，在这里创建变量可以使得变量的形状自适应输入的形状
        # 而不需要使用者额外指定变量形状。
        # 如果已经可以完全确定变量的形状，也可以在__init__部分创建变量
        self.w = self.add_variable(name='w',
            shape=[input_shape[-1], self.units], initializer=tf.zeros_initializer())
        self.b = self.add_variable(name='b',
            shape=[self.units], initializer=tf.zeros_initializer())

    # 模型调用的代码（处理输入并返回输出）
    def call(self, inputs):
        output = tf.matmul(inputs, self.w) + self.b
        return output


# 使用自定义线性回归层的模型
class LinearModel(tf.keras.Model):
    def __init__(self):
        super().__init__()
        self.layer = LinearLayer(units=1)

    def call(self, inputs):
        output = self.layer(inputs)
        return output


# 自定义损失函数（均方误差），继承tf.keras.losses.Loss
class MeanSquaredError(tf.keras.losses.Loss):
    # 重写call方法令其成为一个可调用对象即可
    def call(self, y_true, y_pred):
        return tf.reduce_mean(tf.square(y_pred - y_true))


# 自定义评估方法（重写SparseCategoricalAccuracy ），继承tf.keras.metrics.Metric
class SparseCategoricalAccuracy(tf.keras.metrics.Metric):
    # 初始化需要使用的参数
    def __init__(self):
        super().__init__()
        self.total = self.add_weight(name='total', dtype=tf.int32, initializer=tf.zeros_initializer())
        self.count = self.add_weight(name='count', dtype=tf.int32, initializer=tf.zeros_initializer())

    # 每次计算的更新方法
    def update_state(self, y_true, y_pred, sample_weight=None):
        # tf.equal：按元素序列返回是否相等
        # tf.cast：数据类型转换
        values = tf.cast(tf.equal(y_true, tf.argmax(y_pred, axis=-1, output_type=tf.int32)), tf.int32)
        self.total.assign_add(tf.shape(y_true)[0])
        self.count.assign_add(tf.reduce_sum(values))

    def result(self):
        # 输出正确率
        return self.count / self.total
