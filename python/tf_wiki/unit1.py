import tensorflow as tf
# 基本操作

# 定义一个随机数（标量）
random_float = tf.random.uniform(shape=())

# 定义一个有2个元素的零向量
zero_vector = tf.zeros(shape=(2))

# 定义两个2×2的常量矩阵
# [[1., 2.], [3., 4.]]：
# 1 2
# 3 4
A = tf.constant([[1., 2.], [3., 4.]])
B = tf.constant([[5., 6.], [7., 8.]])

print(A.shape)
print(A.dtype)  # 不使用dtype=XX 指定元素类型时，默认float32
print(A.numpy())

c = tf.matmul(A, B)
d = tf.add(A, B)

print(c)
print(d)

