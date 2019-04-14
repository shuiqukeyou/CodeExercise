import matplotlib.pylab as plt

input_values = [1, 2, 3, 4, 5]
squares = [1, 4, 9, 16, 25]

# X轴值list、Y轴值list、linewidth:线条粗细
plt.plot(input_values, squares, linewidth=5)
# 设置标题及字号
plt.title("Square Numbers", fontsize=24)
# 设置X轴标签及字号
plt.xlabel("Value", fontsize=14)
# 设置Y轴标签及字号
plt.ylabel("Square Value", fontsize=14)
# 指定对轴的刻度、字体进行修改
plt.tick_params(axis="both", labelsize=14)
plt.show()
