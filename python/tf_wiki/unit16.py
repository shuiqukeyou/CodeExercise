import tensorflow as tf
import numpy as np
# 图执行模式：tf.function


# 当被 @tf.function 修饰的函数第一次被调用的时候，进行以下操作：
#
# 在即时执行模式关闭的环境下，函数内的代码依次运行。也就是说，每个 tf. 方法都只是定义了计算节点，而并没有进行任何实质的计算。
# 使用 AutoGraph 将函数中的 Python 控制流语句转换成 TensorFlow 计算图中的对应节点
# （比如说 while 和 for 语句转换为 tf.while ， if 语句转换为 tf.cond 等等）；
# 基于上面的两步，建立函数内代码的计算图表示（为了保证图的计算顺序，图中还会自动加入一些 tf.control_dependencies 节点）；
# 运行一次这个计算图；
# 基于函数的名字和输入的函数参数的类型生成一个哈希值，并将建立的计算图缓存到一个哈希表中。

# 使用@tf.function装饰器的函数内必须只有tf自己的计算成分，例如此处的输出被替换为了tf.print()
@tf.function
def f(x):
    print("The function is running in Python")
    tf.print(x)


a = tf.constant(1, dtype=tf.int32)
f(a)
b = tf.constant(2, dtype=tf.int32)
f(b)
b_ = np.array(2, dtype=np.int32)
f(b_)
c = tf.constant(0.1, dtype=tf.float32)
f(c)
d = tf.constant(0.2, dtype=tf.float32)
f(d)

'''
理论输出结果
The function is running in Python  # 初次空执行，构建计算图
1  # 输出，下同，只执行计算过程而不是执行函数本身
2
2
The function is running in Python  # 变更了输入的数据类型，重新构建计算图
0.1
0.2
'''

print(tf.autograph.to_code(f.python_function))  # tf.autograph.to_code()：将图形化后的函数输出