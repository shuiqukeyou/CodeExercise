import tensorflow as tf
import os
# 调用GPU

gpus = tf.config.experimental.list_physical_devices(device_type='GPU')
cpus = tf.config.experimental.list_physical_devices(device_type='CPU')
print(gpus, cpus)

# GPU指定方法1
os.environ['CUDA_VISIBLE_DEVICES'] = "2,3"  # 指定使用GPU 2、3进行训练

# GPU指定方法2
gpus = tf.config.experimental.list_physical_devices(device_type='GPU')
tf.config.experimental.set_visible_devices(devices=gpus[0:2], device_type='GPU')

# 设置虚拟GPU：从GPU 0上划拨1G显存构建一个序列GPU
tf.config.experimental.set_virtual_device_configuration(gpus[0],
    [tf.config.experimental.VirtualDeviceConfiguration(memory_limit=1024)])

