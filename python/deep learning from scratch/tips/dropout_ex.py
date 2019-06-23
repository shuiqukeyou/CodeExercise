import numpy as np

class Dropout:
    def __init__(self, dropout_ratio = 0.5):
        self.dropout_ratio = dropout_ratio
        self.mask = None

    def forward(self,x,train_flg=True):
        if train_flg:
            # mask矩阵尺寸和x一样，将值比dropout_ratio大的位置设为True
            self.mask = np.random.rand(*x.shape) > self.dropout_ratio
            # 并只向前传播mask值为True的部分
            return x * self.mask
        else:
            return x * (1.0 - self.dropout_ratio)

    def backward(self, dout):
        # 反向传播时，对于正向传播是传播了数据的节点，原样传播，对于没有传播的节点，也不进行反向传播
        return dout * self.mask