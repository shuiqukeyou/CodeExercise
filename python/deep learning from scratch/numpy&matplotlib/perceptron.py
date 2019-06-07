def AND(x1,x2):
    w1, w2, threshold = 0.5, 0.5,0.7
    temp = x1 * w1 + x2 * w2
    if temp <= threshold:
        return 0
    else:
        return 1
def OR(x1, x2):
    w1, w2, threshold = 0.7, 0.7,0.7
    temp = x1 * w1 + x2 * w2
    if temp < threshold:
        return 0
    else:
        return 1

def NAND(x1, x2):
    w1, w2, threshold = -0.5, -0.5,0
    temp = x1 * w1 + x2 * w2
    if temp < threshold:
        return 1
    else:
        return 0
def XOR(x1,x2):
    s1 = NAND(x1, x2)
    s2 = OR(x1, x2)
    y = AND(s1, s2)
    return y

import numpy as np
x = np.array([0,1])
w = np.array([0.5, 0.5])
b = -0.7
print(np.sum(x*w) + b)

if __name__ == '__main__':
    print(AND(0,0))
    print(AND(1,0))
    print(AND(1,1))
    pass
    print(OR(0,0))
    print(OR(1,0))
    print(OR(1,1))

    print(NAND(0,0))
    print(NAND(1,0))
    print(NAND(1,1))


    print(XOR(0,1))
    print(XOR(0,0))