import numpy as py

from layers import *
from neural_network.neural_network_bp import *
from collections import OrderedDict # 有序dict，可以有序的保存参数
from gradient import numerical_gradient
from functions import sigmoid_grad

class TwoLayerNet:

    def __init__(self, input_size, hidden_size, output_size, weight_init_std=0.01):
        # 初始化权重
        self.params = {}
        self.params['W1'] = weight_init_std * np.random.randn(input_size, hidden_size)
        self.params['b1'] = np.zeros(hidden_size)
        self.params['W2'] = weight_init_std * np.random.randn(hidden_size, output_size)
        self.params['b2'] = np.zeros(output_size)

        # 按结构构件各层
        self.layers = OrderedDict()
        self.layers["Affine1"] = Affine(self.params["W1"], self.params["b1"]) # 第一层
        self.layers["Relu1"] = ReLU() # 第一层ReLU
        self.layers["Affine2"] = Affine(self.params["W2"], self.params["b2"]) # 第二层

        self.lastLayer = SoftmaxWithLoss() # 第二层softmax


    def predict(self, x):
        for layer in self.layers.values(): # 逐层向前
            x = layer.forward(x)

        return x


    # x:输入数据, t:监督数据
    def loss(self, x, t):
        y = self.predict(x)

        return cross_entropy_error(y, t)

    def accuracy(self, x, t):
        y = self.predict(x)
        y = np.argmax(y, axis=1)
        t = np.argmax(t, axis=1)

        accuracy = np.sum(y == t) / float(x.shape[0])
        return accuracy

    # 慢速梯度下降
    def numerical_gradient(self, x, t):
        loss_W = lambda W: self.loss(x, t)

        grads = {}
        grads['W1'] = numerical_gradient(loss_W, self.params['W1'])
        grads['b1'] = numerical_gradient(loss_W, self.params['b1'])
        grads['W2'] = numerical_gradient(loss_W, self.params['W2'])
        grads['b2'] = numerical_gradient(loss_W, self.params['b2'])

        return grads

    def gradient(self, x, t):
        # 向前
        self.loss(x,t)

        # 向后
        dout = 1
        dout = self.lastLayer.backward(dout)

        layers = list(self.layers.values())
        layers.reverse()
        for layer in layers:
            dout = layer.backward(dout) # 调用各层的反向传播，调用时会把反向传播的导数记录在各层的对象中

        grads = {}
        # 直接读取各层对象中储存的
        grads["W1"] = self.layers["Affine1"].dw
        grads["b1"] = self.layers["Affine1"].db
        grads["W2"] = self.layers["Affine2"].dw
        grads["b2"] = self.layers["Affine2"].db

        return grads