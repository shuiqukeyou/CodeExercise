import os
import pprint
import tensorflow as tf
import numpy as np


# 吊用TPU
if 'COLAB_TPU_ADDR' not in os.environ:
    print('ERROR: Not connected to a TPU runtime')
else:
    tpu_address = 'grpc://' + os.environ['COLAB_TPU_ADDR']
    print ('TPU address is', tpu_address)

    with tf.Session(tpu_address) as session:
      devices = session.list_devices()

    print('TPU devices:')
    pprint.pprint(devices)


# 分布式TPU实例
(x_train, y_train), (x_test, y_test) = tf.keras.datasets.fashion_mnist.load_data()

# 添加空的颜色实例
x_train = np.expand_dims(x_train, -1)
x_test = np.expand_dims(x_test, -1)

# 定义一个创建模型的函数
def create_model():
    model = tf.keras.models.Sequential()

    model.add(tf.keras.layers.Conv2D(64, (3, 3), input_shape=x_train.shape[1:]))
    model.add(tf.keras.layers.MaxPooling2D(pool_size=(2, 2), strides=(2,2)))
    model.add(tf.keras.layers.Activation('relu'))

    model.add(tf.keras.layers.Flatten())
    model.add(tf.keras.layers.Dense(10))
    model.add(tf.keras.layers.Activation('softmax'))

    return model


resolver = tf.distribute.resolver.TPUClusterResolver(tpu='grpc://' + os.environ['COLAB_TPU_ADDR']) # 创建TPU单元
tf.config.experimental_connect_to_host(resolver.master())  # 连接到TPU
tf.tpu.experimental.initialize_tpu_system(resolver)  # 初始化
strategy = tf.distribute.experimental.TPUStrategy(resolver)  # 创建TPU分布器

# 分布式计算，代码写在分布器内
with strategy.scope():
    model = create_model()
    model.compile(
        optimizer=tf.keras.optimizers.Adam(learning_rate=1e-3),
        loss=tf.keras.losses.sparse_categorical_crossentropy,
        metrics=[tf.keras.metrics.sparse_categorical_accuracy])

# 拟合
model.fit(
    x_train.astype(np.float32), y_train.astype(np.float32),
    epochs=5,
    steps_per_epoch=60,
    validation_data=(x_test.astype(np.float32), y_test.astype(np.float32)),
    validation_freq=5
)
