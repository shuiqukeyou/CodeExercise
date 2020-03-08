import tensorflow as tf


# tensorflow的Pipeline模型，但是并不建议这么用，和现在主流框架的实现方法不同

# 层Sequential模式，按顺序创建所需的层
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(100, activation=tf.nn.relu),
    tf.keras.layers.Dense(10),
    tf.keras.layers.Softmax()
])

# 更复杂的Pipeline模型
# 按顺序嵌套层
inputs = tf.keras.Input(shape=(28, 28, 1))
x = tf.keras.layers.Flatten()(inputs)
x = tf.keras.layers.Dense(units=100, activation=tf.nn.relu)(x)
x = tf.keras.layers.Dense(units=10)(x)
outputs = tf.keras.layers.Softmax()(x)
# 通过input和output层对象创建模型
model = tf.keras.Model(inputs=inputs, outputs=outputs)

# 创建完模型后，需要调用compile并指定优化器、损失函数、评估方法
model.compile(
    optimizer=tf.keras.optimizers.Adam(learning_rate=0.001),
    loss=tf.keras.losses.sparse_categorical_crossentropy,
    metrics=[tf.keras.metrics.sparse_categorical_accuracy]
)

# 假参数
data_loader = None
num_epochs = None
batch_size = None

# 最后需要指定模型的运行参数（数据、epochs、batch等），再开始进行拟合
model.fit(data_loader.train_data, data_loader.train_label, epochs=num_epochs, batch_size=batch_size)