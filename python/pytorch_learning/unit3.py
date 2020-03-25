import torch
import torch.nn as nn
import torch.nn.functional as F


# pytorch的神经网络类：torch.nn
# 自定义模型只需要定义 forward 函数，backward函数会在使用autograd时自动定义
class Net(nn.Module):

    def __init__(self):
        super(Net, self).__init__()
        # 输入图像channel：1；输出channel：6；5x5卷积核
        self.conv1 = nn.Conv2d(1, 6, 5)
        self.conv2 = nn.Conv2d(6, 16, 5)
        # an affine operation: y = Wx + b
        self.fc1 = nn.Linear(16 * 5 * 5, 120)
        self.fc2 = nn.Linear(120, 84)
        self.fc3 = nn.Linear(84, 10)

    def forward(self, x):
        # 2x2 Max pooling
        x = F.max_pool2d(F.relu(self.conv1(x)), (2, 2))
        # 如果是方阵,则可以只使用一个数字进行定义
        x = F.max_pool2d(F.relu(self.conv2(x)), 2)
        # view：重构张量维度
        x = x.view(-1, self.num_flat_features(x))
        x = F.relu(self.fc1(x))
        x = F.relu(self.fc2(x))
        x = self.fc3(x)
        return x

    def num_flat_features(self, x):
        size = x.size()[1:]  # 除去批处理维度的其他所有维度
        num_features = 1
        for s in size:
            num_features *= s
        return num_features


net = Net()
# 输出网络结构
print(net)

# 模型的可学习参数可以通过net.parameters()返回
params = list(net.parameters())
print(len(params))
print(params[0].size())  # conv1的权重

# 随机生成一个32x32的输入：nSamples x nChannels x Height x Width
# pytorch不接受单个输入，必须是批次输入，因此数据的第一维都是样本数
# 如果有必要进行单个输入，使用input.unsqueeze(0)构建一个假批次
input = torch.randn(1, 1, 32, 32)
out = net(input)
print(out)

# 清零梯度并执行异常反向传播
net.zero_grad()
out.backward(torch.randn(1, 10))

# 损失函数
# 一个损失函数接受一对(output, target)作为输入，计算一个值来估计网络的输出和目标值相差多少
output = net(input)
target = torch.randn(10)  # 使用模拟数据
target = target.view(1, -1)  # 使目标值与数据值形状一致
criterion = nn.MSELoss()  # 均方误差，集成在nn模块下

loss = criterion(output, target)
print(loss)

# loss.grad_fn：跟踪反向传播过程
print(loss.grad_fn)  # MSELoss
print(loss.grad_fn.next_functions[0][0])  # Linear
print(loss.grad_fn.next_functions[0][0].next_functions[0][0])  # ReLU

# 反向传播
# 调用loss.backward()后，整张图会开始积分，所有设置了requires_grad=True的张量的.grad属性累积着梯度张量
net.zero_grad()     # 清零所有参数(parameter）的梯度缓存

print('conv1.bias.grad 开始向后传播')
print(net.conv1.bias.grad)  # 输出反向传播前的值

loss.backward()

print('conv1.bias.grad 开始向后传播')
print(net.conv1.bias.grad)

import torch.optim as optim

# 创建优化器(optimizer）
optimizer = optim.SGD(net.parameters(), lr=0.01)

# 在训练的迭代中：
optimizer.zero_grad()   # 清零梯度缓存
output = net(input)  # 计算
loss = criterion(output, target)  # 定义损失函数
loss.backward()  # 反向传播
optimizer.step()    # 更新参数