import numpy as np

class Momentum:
    def __init__(self, lr=0.01, momentum=0.9):
        self.lr = lr
        self.momentum = momentum
        self.v = None # 保存之前的前进方向

    def update(self, params, grads):
        if self.v is None: # 如果没有前进过
            self.v = {}
            for key, val in params.items():
                self.v[key] = np.zeros_like(val) # 保存这次的前进方向

        for key in params.keys():
            self.v[key] = self.momentum * self.v[key] - self.lr * grads[key] # 更新前进方向
            params[key] += self.v[key] # 更新参数

class AdaGrad:
    def __init__(self, lr=0.01):
        self.lr = lr
        self.h = None

    def update(self, params, grads):
        if self.h is None:
            self.h = {}
            for key, val in params.items():
                self.h[key] = np.zeros_like(val)

        for key in params.keys():
            self.h[key] += grads[key] * grads[key] # 累积每个key的方向
            params[key] -= self.lr * grads[key] / (np.sqrt(self.h[key]) + 1e-7) # 除以h累计值的开方，用一个极小值防止除0