import pickle
import numpy as np





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

def getdata():
    (x_train, t_train),(x_test, t_test) = load_mnist(normalize=True, flatten=True, one_hot_label=True)
    return x_test, t_test


# 均方误差函数
def mean_squared_error(y,t):
    return 0.5 * np.sum((y-t)**2)

# 交叉熵误差函数
def cross_entropy_error(y,t):
    delta = 1e-7 # 防止得出无穷大的极小值
    return -np.sum(t * np.log(y + delta))

# 交叉熵误差函数(mini-batch/one-hot)
def cross_entropy_error(y,t):
    if y.ndim == 1: # 若y的维度为1
        t = t.reshape(1,t.size)# 重整数据集为1维
        y = y.reshape(1,y.size)# 重整标签
    batch_size = y.shape[0]
    return -np.sum(t * np.log(y + 1e-7))/batch_size

# 交叉熵误差函数(mini-batch/非one-hot)
# 非ont-hot:数据集不是只有0/1
def cross_entropy_error(y,t):
    if y.ndim == 1: # 若y的维度为1
        t = t.reshape(1,t.size)# 重整数据集为1维
        y = y.reshape(1,y.size)
    batch_size = y.shape[0]
    # np.arange(batch_size)：生成一个0~batch_size-1的一维数组
    # 当t = [2,7,0,9,4]，batch_size=5时，此处会生成[y[0,2], y[1,7], y[2,0], y[3,9], y[4,4]]
    return -np.sum(np.log(y[np.arange(batch_size), t] + 1e-7))/batch_size

# 导数近似
def numerical_diff(f,x):
    h = 1e-4 # 即0.0001
    return (f(x+h) - f(x-h))/2*h

# 梯度（使用近似导数）
def numerical_gradient(f,x):
    h = 1e-4
    grad = np.zeros_like(x) # 生成一个和x大小一样，各项全为0的梯度矩阵
    for i in range(x.size):
        tmp_val = x[i]  # 暂存当前的x

        # 修改x[i]
        x[i] = tmp_val + h
        # 因为是用近似值计算，所以需要将修改后的x整个放到函数中计算一次，得到修改后的输出
        fxh1 = f(x)

        x[i] = tmp_val - h
        fxh2 = f(x)

        grad[i] = (fxh1-fxh2)/(2*h) # 进行梯度的近似计算
        x[i] = tmp_val # 将x改回去
    return grad


def gradient_descent(f, init_x, lr=0.01, step_num=100):
    x = init_x

    for i in range(step_num):
        grad = numerical_gradient(f,x)
        x -= lr * grad
    return x

if __name__ == '__main__':
    x_train, t_train = getdata()
    # print(x_train.shape)
    # print(t_train.shape)
    # train_size = x_train.shape[0]
    # batch_size = 10
    # batch_mask = np.random.choice(train_size,batch_size)# train_size:指定随机范围为0~train_size-1，batch_size：随机范围尺寸
    # x_batch = x_train[batch_mask]
    # t_batch = t_train[batch_mask]
    # print(x_batch)
    # print(t_batch)
    def function_1(x):
        return x[0]**2 + x[1]**2

    init_x = np.array([-3.0,4.0])
    x = gradient_descent(function_1, init_x=init_x, lr=0.1, step_num=100)
    print(x)