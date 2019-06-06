import numpy as np

# 生成向量
x = np.array([1,2,3])
print(x)
print(type(x))

# 向量加减乘除
y = np.array([2,3,4])
print(x + y)
print(x - y)
print(x * y) # 为对应元素逐个相乘
print(x / y) # 为对应元素逐个相除

# 生成矩阵
x = np.array([
    [1,2],
    [3, 4]
])
print(x)

# 获取矩阵尺寸
print(x.shape)
y = np.array([
    [5,6],
     [7,8]
])
# 也是对应元素逐个相乘/除
print(x * y)
print(x / y)

# 自动扩增（广播？）
y = np.array([1,2])
print(x * y) # 并不会出错，np会将尺寸较小的矩阵自动扩大，使计算可以进行

# 访问元素（基本同数组）
print(x[0]) # 第0行
print(x[0][1]) # 第0行第1个
for row in x:
    print(row)

# 将矩阵转换为向量
x = x.flatten()
print(x)

# 按索引批量获取（索引以向量传入）
# 获取单个元素时，按list方法操作即可
print(x[np.array([0,1,3])])

# 返回所有元素是否大于/小于阙值的布尔值
print(x>2)
