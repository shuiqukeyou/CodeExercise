import numpy as np

from util import im2col

class Convolution:
    def __init__(self,w,b,stride=1, pad = 0):
        self.w = w
        self.b = b
        self.stride = stride
        self.pad = pad

    def forward(self, x):
        FN, C ,FH, FW = self.w.shape
        N,C,H,W = x.shape
        # 计算输出大小
        out_h = int(1 + (H + 2*self.pad- FH) / self.stride)
        out_w = int(1 + (W + 2 * self.pad - FH) / self.stride)

        # 进行im2col
        col = im2col(x,FH,FW,self.stride,self.pad)

        col_W = self.W.reshape(FN,-1).T # 指定一个维度为-1时，reshape会自适应的调整形状
        # 调整完成后 ，进行计算
        out = np.dot(col, col_W) + self.b

        # 调整为输出的形状
        out = out.reshape(N, out_h, out_w, -1).transpose(0,3,1,2)

        return out


class Pooling:
    def __init__(self, pool_h, pool_w, stride = 1, pad=0):
        self.pool_h = pool_h
        self.pool_w = pool_w
        self.stride = 1
        self.pad = 0

    def forward(self,x):
        N,C,H,W = x.shape
        # 计算输出尺寸
        out_h = int(1 + (H - self.pool_h) / self.stride)
        out_w = int(1 + (W - self.pool_w) / self.stride)

        # 进行im2col计算
        col = im2col(x, self.pool_h, self.pool_w, self.stride, self.pad)
        col = col.reshape(-1 ,self.pool_h * self.pool_w)

        # 取每个池化通道的最大值
        out = np.max(col, axis=1)
        # 重组池化输出
        out = out.reshape(N, out_h, out_w, C).transpose(0,3,1,2)

        return out


if __name__ == '__main__':
    x1 = np.random.rand(1,3,7,7)
    col1 = im2col(x1,5,5,stride=1,pad=0)# 输入数据、滤波器的高、滤波器的长、步幅、填充
    print(col1.shape)

    x2 = np.random.rand(10,3,7,7)
    col2 = im2col(x2,5,5,stride=1,pad=0)
    print(col2.shape)