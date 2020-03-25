import torch
import numpy as np


x = torch.Tensor(5, 3)  # 构造一个未初始化的5*3的矩阵
x = torch.rand(5, 3)  # 构造一个随机初始化的矩阵
print(x)
# torch.Size 事实上是一个tuple, 所以其支持相关的操作
print(x.size())


y = torch.rand(5, 3)

# 将两个同形矩阵相加有两种操作方法
x + y  # 语法一
torch.add(x, y)  # 语法二

# 输出tensor也有两种写法
result = torch.Tensor(5, 3)
print(torch.add(x, y, out=result))
print(y.add_(x))  # 将y与x相加

# 任何可以改变tensor内容的操作都会在方法名后加一个下划线'_'
# 例如：x.copy_(y), x.t_(), 这俩都会改变x的值。

# 另外也滋瓷切片操作。
# 这一操作会输出x矩阵的第二列的所有值
print(x[:, 1])

# 如果想改变形状，可以使用torch.view
x = torch.randn(4, 4)
y = x.view(16)
z = x.view(-1, 8)  # -1表示自适应
print(x.size(), y.size(), z.size())

# 如果是仅包含一个元素的tensor，可以使用.item()来得到对应的python数值
x = torch.randn(1)
print(x)
print(x.item())

# pytorch和numpy之间可以快捷的互转
a = torch.ones(5)
b = a.numpy()

# 当修改numpy数组之后,与之相关联的tensor也会相应的被修改
a.add_(1)
print(a)
print(b)

# 将numpy的Array转换为torch的Tensor
a = np.ones(5)
b = torch.from_numpy(a)
np.add(a, 1, out=a)
print(a)
print(b)

# 另外除了CharTensor之外，所有的tensor都可以在CPU运算和GPU运算之间相互转换
# 使用CUDA函数来将Tensor移动到GPU上
# 当CUDA可用时会进行GPU的运算
if torch.cuda.is_available():
    x = x.cuda()
    y = y.cuda()
    print(x + y)

