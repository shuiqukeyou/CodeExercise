from functions import *
from gradient import numerical_gradient

class TwoLayerNet:

    def __init__(self, input_size, hidden_size, output_size, weight_init_std=0.01):
        self.params = {}
        self.params["W1"] = weight_init_std * np.random.randn(input_size, hidden_size) # 随机初始化
        self.params["b1"] = np.zeros(hidden_size) # 指定初始偏置
        self.params["W2"] = weight_init_std * np.random.randn(hidden_size, output_size)# 随机初始化
        self.params["b2"] = np.zeros(output_size) # 指定初始偏置

    # 预测
    def predict(self, x):
        W1, W2 = self.params["W1"], self.params["W2"]
        b1, b2 = self.params["b1"], self.params["b2"]

        a1 = np.dot(x,W1) + b1
        z1 = sigmoid(a1)
        a2 = np.dot(z1, W2) + b2
        y = softmax(a2)

        return y

    # 计算成本
    def loss(self,x ,t):
        y = self.predict(x)
        return cross_entropy_error(y,t)

    # 计算正确率
    def accuracy(self, x, t):
        y = self.predict(x)
        y = np.argmax(y, axis=1)
        t = np.argmax(t ,axis=1)

        accuracy = np.sum(y == t)/float(x.shape[0])
        return accuracy

    # 计算了梯度
    def numerical_gradient(self,x,t):
        loss_W = lambda W:self.loss(x,t) # 计算损失
        grads = {}
        # 根据损失计算梯度
        self.params["W1"] = numerical_gradient(loss_W, self.params["W1"])
        self.params["b1"] = numerical_gradient(loss_W, self.params["b1"])
        self.params["W2"] = numerical_gradient(loss_W, self.params["W2"])
        self.params["b2"] = numerical_gradient(loss_W, self.params["b2"])

        return grads