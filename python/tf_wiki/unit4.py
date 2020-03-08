import tensorflow as tf
# 构建model和layer


X = tf.constant([[1.0, 2.0, 3.0], [4.0, 5.0, 6.0]])
y = tf.constant([[10.0], [20.0]])


class Linear(tf.keras.Model):  # 自定义model需继承tf.keras.Model
    def __init__(self):  # 定义model中需要初始化的参数
        super().__init__()
        self.dense = tf.keras.layers.Dense(  # 创建一个全连接层，读取尺寸为1，无激活函数，w和b初始化为0
            units=1,
            activation=None,
            kernel_initializer=tf.zeros_initializer(),
            bias_initializer=tf.zeros_initializer()
        )

    def call(self, inputs):  # 定义model被调用时的行为
        output = self.dense(inputs)  # 调用定义的全连接层计算输出
        return output


# 以下代码结构与前节类似
model = Linear()
optimizer = tf.keras.optimizers.SGD(learning_rate=0.01)
for i in range(100):
    with tf.GradientTape() as tape:
        y_pred = model(X)      # 调用模型 y_pred = model(X) 而不是显式写出 y_pred = a * X + b
        loss = tf.reduce_mean(tf.square(y_pred - y))
    grads = tape.gradient(loss, model.variables)    # 使用 model.variables 这一属性直接获得模型中的所有变量
    optimizer.apply_gradients(grads_and_vars=zip(grads, model.variables))
print(model.variables)