import numpy as np
import pickle
def softmax(a):
    c = np.max(a)
    exp_a = np.exp(a - c) # 防溢出
    sum_exp_a = np.sum(exp_a)
    y = exp_a/sum_exp_a
    return y

# 交叉熵误差函数(mini-batch/one-hot)
def cross_entropy_error(y,t):
    if y.ndim == 1: # 若y的维度为1
        t = t.reshape(1,t.size)# 重整数据集为1维
        y = y.reshape(1,y.size)# 重整标签
    batch_size = y.shape[0]
    return -np.sum(t * np.log(y + 1e-7))/batch_size

def sigmoid(x):
    return 1/(1 + np.exp(-x))

# 乘法单元的向前和向后传播
class MuLayer:
    def __init__(self):
        self.x = None
        self.y = None

    def forward(self,x,y):
        self.x = x
        self.y = y
        out = x * y
        return out

    def backward(self, dout):
        dx = dout * self.y
        dy = dout * self.x
        return  dx, dy


# 加法单元的向前和向后传播
class AddLayer:
    def __init__(self):
        pass

    def forward(self,x,y):
        out = x + y
        return out

    def backward(self, dout):
        dx = dout * 1
        dy = dout * 1
        return dx, dy

# 减法、除法和加法乘法本质一样
# ReLU单元的向前和向后传播
class ReLU:
    def __init__(self):
        self.mask = None

    def forwark(self,x):
        self.mask = (x<=0) # 将小于等于0的部分标记为True，反之标记为False
        out = x.copy()
        out[self.mask] = 0 # 将小于等于的元素设置为0，反之原样输出
        return out

    def backward(self,dout):
        dout[self.mask] = 0
        dx = dout
        return dx

class Sigmoid:
    def __init__(self):
        self.out = None

    def forward(self,x):
        out = 1 / (1+ np.exp(-x))
        self.out = out
        return out

    def backward(self, dout):
        dx = dout * (1.0 - self.out) * self.out

        return dx

class Affine:
    def __init__(self,w, b):
        self.w = w
        self.b = b
        self.x = None
        self.dw = None
        self.db = None

    def forward(self,x):
        self.x = x
        out = np.dot(x, self.w) + self.b

        return out

    def backward(self, dout):
        dx = np.dot(dout, self.w.T)
        self.dw = np.dot(self.x.T, dout)
        self.db = np.sum(dout, axis=0)
        return dx

class SoftmaxWithLoss:
    def __init__(self):
        self.loss = None
        self.y = None
        self.t = None

    def forward(self,x,t):
        self.t = t
        self.y = softmax(x)
        self.loss = cross_entropy_error(self.y, self.t)
        return self.loss

    # softmax函数通常只用在最后一层，所以dout默认为1
    def backward(self,dout=1):
        batch_size = self.t.shape[0]
        dx = (self.y - self.t) / batch_size

        return dx

# 以下为复制源码部分的数据读取
def _change_one_hot_label(X):
    T = np.zeros((X.size, 10))
    for idx, row in enumerate(T):
        row[X[idx]] = 1

    return T

def load_mnist(normalize=True, flatten=True, one_hot_label=False):
    """读入MNIST数据集

    Parameters
    ----------
    normalize : 将图像的像素值正规化为0.0~1.0
    one_hot_label :
        one_hot_label为True的情况下，标签作为one-hot数组返回
        one-hot数组是指[0,0,1,0,0,0,0,0,0,0]这样的数组
    flatten : 是否将图像展开为一维数组

    Returns
    -------
    (训练图像, 训练标签), (测试图像, 测试标签)
    """

    with open("./dataset/mnist.pkl", 'rb') as f:
        dataset = pickle.load(f)

    if normalize:
        for key in ('train_img', 'test_img'):
            dataset[key] = dataset[key].astype(np.float32)
            dataset[key] /= 255.0

    if one_hot_label:
        dataset['train_label'] = _change_one_hot_label(dataset['train_label'])
        dataset['test_label'] = _change_one_hot_label(dataset['test_label'])

    if not flatten:
        for key in ('train_img', 'test_img'):
            dataset[key] = dataset[key].reshape(-1, 1, 28, 28)

    return (dataset['train_img'], dataset['train_label']), (dataset['test_img'], dataset['test_label'])
# 以上为复制源码的数据读取


if __name__ == '__main__':
    apple = 100
    apple_num = 2
    orange = 3
    orange_num = 3
    tax = 1.1

    apple_layer = MuLayer() # 林檎总价层（乘法）
    orange_layer = MuLayer() # 橘子总价层（乘法）
    add_apple_orange = AddLayer() # 总价层（加法）
    tax_layer = MuLayer() # 消费税层（乘法）

    # 向前传播
    apple_price = apple_layer.forward(apple, apple_num)
    orange_price = orange_layer.forward(orange, orange_num)
    all_price = add_apple_orange.forward(apple_price, orange_price)
    price = tax_layer.forward(all_price, tax)

    # 向后传播
    dprice = 1 # 价格微分
    dall_price, dtax = tax_layer.backward(dprice) # 反向计算税和总价的微分
    dapple_price, dorange_price = add_apple_orange.backward(dall_price)# 反向计算林檎层和橘子层的微分
    dorange, dorange_num = orange_layer.backward(dorange_price)
    dapple, dapple_num = apple_layer.backward(dall_price)

    print(price)
    print(dapple_num, dapple, dorange, dorange_num, dtax)