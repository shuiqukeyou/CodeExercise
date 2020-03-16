import tensorflow as tf
import tensorflow_datasets as tfds
import matplotlib.pyplot as plt
import numpy as np


# dataset对象的构建方法
X = tf.constant([2013, 2014, 2015, 2016, 2017])
Y = tf.constant([12000, 14000, 15000, 16500, 17500])

# 也可以使用NumPy数组，效果相同
# X = np.array([2013, 2014, 2015, 2016, 2017])
# Y = np.array([12000, 14000, 15000, 16500, 17500])

dataset = tf.data.Dataset.from_tensor_slices((X, Y))  # 用tuple给定X和Y

for x, y in dataset:
    print(x.numpy(), y.numpy())

"""
tf.data.Dataset 提供了许多预处理数据集的方法
以下为部分方法
Dataset.map(f) ：对数据集中的每个元素应用函数 f
Dataset.shuffle(buffer_size) ：将数据集打乱（设定一个固定大小的缓冲区（Buffer），
                            取出前 buffer_size 个元素放入，并从缓冲区中随机采样，采样后的数据用后续数据替换）
Dataset.batch(batch_size) ：将数据集分成批次，即对每 batch_size 个元素，使用 tf.stack() 在第 0 维合并，成为一个元素；
Dataset.prefetch() ：数据集的预处理，可以让CPU、GPU并行运行
"""

# 加载数据集
dataset = tfds.load("mnist", split=tfds.Split.TRAIN)


# 定义旋转函数
def rot90(image, label):
    image = tf.image.rot90(image)
    return image, label


# dataset.map(）
mnist_dataset = dataset.map(rot90)


# 展示一个旋转后的图片
for image, label in dataset:
    plt.title(label.numpy())
    plt.imshow(image.numpy()[:, :, 0])
    plt.show()


# Dataset.shuffle()、Dataset.batch()：缓冲区10000，batch大小 4
mnist_dataset = dataset.shuffle(buffer_size=10000).batch(4)


# Dataset.prefetch()
# 自适应并行化
mnist_dataset = mnist_dataset.prefetch(buffer_size=tf.data.experimental.AUTOTUNE)