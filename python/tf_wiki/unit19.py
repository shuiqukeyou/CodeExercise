import tensorflow as tf
import os
import json
import tensorflow_datasets as tfds
# 分布式学习

# 单机分布式
strategy = tf.distribute.MirroredStrategy()

with strategy.scope():
    # 模型构建代码
    pass

# 单机分布式实例
num_epochs = 5
batch_size_per_replica = 64
learning_rate = 0.001

# 创建单机分布式计算对象
strategy = tf.distribute.MirroredStrategy()
print('Number of devices: %d' % strategy.num_replicas_in_sync)  # 输出设备数量
batch_size = batch_size_per_replica * strategy.num_replicas_in_sync


# 载入数据集并预处理
def resize(image, label):
    image = tf.image.resize(image, [224, 224]) / 255.0
    return image, label


# 使用 TensorFlow Datasets 载入猫狗分类数据集，详见“TensorFlow Datasets数据集载入”一章
dataset = tfds.load("cats_vs_dogs", split=tfds.Split.TRAIN, as_supervised=True)
dataset = dataset.map(resize).shuffle(1024).batch(batch_size)

# 在strategy中声明模型代码
with strategy.scope():
    model = tf.keras.applications.MobileNetV2()
    model.compile(
        optimizer=tf.keras.optimizers.Adam(learning_rate=learning_rate),
        loss=tf.keras.losses.sparse_categorical_crossentropy,
        metrics=[tf.keras.metrics.sparse_categorical_accuracy]
    )

model.fit(dataset, epochs=num_epochs)


# 多机分布式学习，需要指定各个机器的参数
# cluster 说明了整个多机集群的结构和每台机器的网络地址（IP + 端口号）。对于每一台机器，cluster 的值都是相同的；
# task 说明了当前机器的角色。例如， {'type': 'worker', 'index': 0} 说明当前机器是 cluster 中的第 0 个 worker（即 localhost:20000 ）。
# 每一台机器的 task 值都需要针对当前主机进行分别的设置。

# 设置完之后在所有子机器上都执行该程序
os.environ['TF_CONFIG'] = json.dumps({
    'cluster': {
        'worker': ["localhost:20000", "localhost:20001"]
    },
    'task': {'type': 'worker', 'index': 0}
})


# 多机分布式实例
num_epochs = 5
batch_size_per_replica = 64
learning_rate = 0.001

# 指定分布式参数
num_workers = 2
os.environ['TF_CONFIG'] = json.dumps({
    'cluster': {
        'worker': ["localhost:20000", "localhost:20001"]
    },
    'task': {'type': 'worker', 'index': 0}
})
strategy = tf.distribute.experimental.MultiWorkerMirroredStrategy()
batch_size = batch_size_per_replica * num_workers


def resize(image, label):
    image = tf.image.resize(image, [224, 224]) / 255.0
    return image, label


dataset = tfds.load("cats_vs_dogs", split=tfds.Split.TRAIN, as_supervised=True)
dataset = dataset.map(resize).shuffle(1024).batch(batch_size)

# 同样在分布器内定义模型
with strategy.scope():
    model = tf.keras.applications.MobileNetV2()
    model.compile(
        optimizer=tf.keras.optimizers.Adam(learning_rate=learning_rate),
        loss=tf.keras.losses.sparse_categorical_crossentropy,
        metrics=[tf.keras.metrics.sparse_categorical_accuracy]
    )

model.fit(dataset, epochs=num_epochs)