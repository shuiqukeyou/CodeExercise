import matplotlib.pylab as plt

x_values = list(range(1, 1001))
y_value = [x ** 2 for x in x_values]

# X值、Y值、点位直径、点的描边、点的颜色(也可使用c=(255,255,255)指定)
# 颜色映射：令图表进行颜色渐变，需要对c值指定一组渐变值，并通过cmap指定颜色映射
plt.scatter(x_values, y_value, s=40, edgecolors="none", c=y_value, cmap=plt.cm.Blues)

plt.title("Square Number", fontsize=24)
plt.xlabel("Value", fontsize=14)
plt.ylabel("Square of Value", fontsize=14)

plt.tick_params(axis="both", which="major", labelsize=14)
# 设定坐标范围
plt.axis([0, 1100, 0, 1100000])

# plt.show()
# 保存图表，bbox_inches="tight"：裁剪空白
# 不能先调用show()再调用保存
plt.savefig("squares_plot.png", bbox_inches="tight")
