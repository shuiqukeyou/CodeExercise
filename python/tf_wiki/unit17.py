import tensorflow as tf
# tf.TensorArray()：tensorflow的可变数组，如果要使用图计算模式，就不能使用python自带的数组


# write(index, value) ：将 value 写入数组的第 index 个位置；
# read(index) ：读取数组的第 index 个值；

@tf.function
def array_write_and_read():
    arr = tf.TensorArray(dtype=tf.float32, size=3)
    arr = arr.write(0, tf.constant(0.0))
    arr = arr.write(1, tf.constant(1.0))
    arr = arr.write(2, tf.constant(2.0))
    arr_0 = arr.read(0)
    arr_1 = arr.read(1)
    arr_2 = arr.read(2)
    return arr_0, arr_1, arr_2

a, b, c = array_write_and_read()
print(a, b, c)