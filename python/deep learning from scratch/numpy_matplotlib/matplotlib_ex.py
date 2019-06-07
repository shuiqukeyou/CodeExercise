import numpy as np
import matplotlib.pyplot as plt # matplotlib的绘图模块

from matplotlib.image import imread # 读取图像的库

x = np.arange(0,6,0.1) # 以0.1为单位，生成0~6的向量
# y = np.sin(x) # 对x的每个元素执行sin函数
# plt.plot(x,y) # 传入x和y
# plt.show() # 绘图

y1 = np.sin(x)
y2 = np.cos(x)

# plt.plot(x, y1, label = "sin") # 指定标签的传入数据
# plt.plot(x, y2, label = "cos")
# plt.xlabel("x") # 指定x轴标签
# plt.ylabel("y") # 指定y轴标签
# plt.title("sin & cos") # 指定标题
# plt.legend() # 图例控制函数
# plt.show()

img = imread("C:/Users/shuiqukeyou/Desktop/20190423172513.png") # 仅支持png格式
plt.imshow(img)
plt.show()