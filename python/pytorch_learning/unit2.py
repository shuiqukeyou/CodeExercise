import torch

# 创建一个张量并设置requires_grad=True用来追踪其计算历史
x = torch.ones(2, 2, requires_grad=True)
print(x)

y = x + 2
print(y)

# y是计算得到的结果，故它有grad_fn属性
print(y.grad_fn)

z = y * y * 3
out = z.mean()
print(z, out)

a = torch.randn(2, 2)
a = ((a * 3) / (a - 1))
print(a.requires_grad)  # 直接输入的requires_grad属性是False
a.requires_grad_(True)  # 可以通过强制指定修改
print(a.requires_grad)
b = (a * a).sum()
print(b.grad_fn)

out.backward()  # 反向传播
# 输出导数 d(out)/dx
print(x.grad)


x = torch.randn(3, requires_grad=True)
y = x * 2
# y.data.norm():求y的二范数
# 在y的二范数小于1000时反复执行*2操作，这样的计算出的向量不再是标量
while y.data.norm() < 1000:
    y = y * 2
print(y)

#
v = torch.tensor([0.1, 1.0, 0.0001], dtype=torch.float)
y.backward(v)
print(x.grad)

print(x.requires_grad)
print((x ** 2).requires_grad)

# 通过将代码块包装 with torch.no_grad()中，来阻止autograd跟踪设置.requires_grad=True的张量的记录
with torch.no_grad():
    print((x ** 2).requires_grad)