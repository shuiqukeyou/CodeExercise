import numpy as np
import matplotlib.pylab as plt

# 阶跃函数
def step_function(x):
    y = x > 0 # 返回x中各个元素是否大于0的布尔值（np的方法）
    return  y.astype(np.int)# 转换布尔值为0/1

# sigmoid函数
def sigmoid(x):
    return 1/(1 + np.exp(-x))

def relu(x):
    return np.maximum(0,x) # 返回0和x中较大的那个

def identity_function(x): # 一个原样输出的激活函数
    return x

def init_network():
    network = {}
    network['w1'] = np.array([
        [0.1,0.3,0.5],
        [0.2,0.4,0.6]
    ])
    network['b1'] = np.array([0.1,0.2,0.3])
    network['w2'] = np.array([
        [0.1,0.4],
        [0.2,0.5],
        [0.3,0.6]
    ])
    network['b2'] = np.array([0.1,0.2])
    network['w3'] = np.array([
        [0.1,0.3],
        [0.2,0.4]
    ])
    network['b3'] = np.array([0.1,0.2])
    return network

def forward(network, x):
    W1, W2, W3 = network['w1'],network['w2'],network['w3']
    b1,b2,b3 = network['b1'],network['b2'],network['b3']

    A1 = np.dot(x, W1) + b1
    Z1 = sigmoid(A1)
    A2 = np.dot(Z1, W2) + b2
    Z2 = sigmoid(A2)
    B3 = np.array([0.1,0.2])
    A3 = np.dot(Z2,W3) + b3
    y = identity_function(A3)
    return y

def softmax(a):
    c = np.max(a)
    exp_a = np.exp(a - c) # 防溢出
    sum_exp_a = np.sum(exp_a)
    y = exp_a/sum_exp_a
    return y

if __name__ == '__main__':
    # # .astype方法的作用
    # x = np.array([-1,-2,4,1])
    # y = x >0
    # y = y.astype(np.int)
    # print(y)

    # # 跃阶函数的图像
    # x = np.arange(-5,5,0.1)
    # y = step_function(x)
    # plt.plot(x,y)
    # plt.ylim(-0.1,1.1) # 指定y轴的范围
    # plt.show()

    # # sigmoid函数的图像
    # x = np.arange(-5,5,0.1)
    # y = sigmoid(x)
    # plt.plot(x,y)
    # plt.ylim(-0.1,1.1) # 指定y轴的范围
    # plt.show()

    # a = np.array([
    #     [1,2],
    #     [3,4]
    # ])
    # print(np.ndim(a)) # 返回a的维数（列数）
    # b = np.array([
    #     [5,6],
    #     [7,8]
    # ])
    # print(np.dot(a,b)) # 矩阵乘法
    #
    # # 一个3层神经网络 A = X*W + B
    # # 第一层
    # X = np.array([1,0.5])
    # W1 = np.array([
    #     [0.1,0.3,0.5],
    #     [0.2,0.4,0.6]
    # ])
    # B1 = np.array([0.1,0.2,0.3])
    # A1 = np.dot(X,W1)+ B1
    # Z1 = sigmoid(A1)
    # # 第二层
    # W2 = np.array([
    #     [0.1,0.4],
    #     [0.2,0.5],
    #     [0.3,0.6]
    # ])
    # B2 = np.array([0.1,0.2])
    # A2 = np.dot(Z1, W2) + B2
    # Z2 = sigmoid(A2)
    # # 第三层
    # W3 = np.array([
    #     [0.1,0.3],
    #     [0.2,0.4]
    # ])
    # B3 = np.array([0.1,0.2])
    # A3 = np.dot(Z2,W3) + B3
    # Y = identity_function(A3)
    # print(Y)

    network = init_network()
    x = np.array([1,0.5])
    y = forward(network,x)
    print(y)