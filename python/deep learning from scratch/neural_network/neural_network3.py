import numpy as np

from functions import softmax, cross_entropy_error
from gradient import numerical_gradient

class simpleNet:
    def __init__(self):
        self.W = np.random.randn(2,3) # 高斯分布随机初始化

    def predict(self, x):
        return np.dot(x,self.W)

    def loss(self, x, t):
        z = self.predict(x)
        y = softmax(z)
        loss = cross_entropy_error(y,t)

        return loss

if __name__ == '__main__':
    net = simpleNet() # 随机生成一个初始网络 2x3
    print(net.W)

    x = np.array([0.6,0.9]) # 给定初始x 1x2
    p = net.predict(x) # 利用给定的初始x进行预测 p = 1x3
    print(p)
    print(np.argmax(p)) # 获取最大值的索引

    t = np.array([0,0,1]) # 指定真实标签
    print(net.loss(x,t)) # 计算成本
    def f(W):
        return net.loss(x,t)

    dW = numerical_gradient(f, net.W) # 函数包的梯度下降算法，利用成本函数和初始网络计算出梯度
    print(dW)