import matplotlib.pylab as plt

from dataVisualization.random_walk import RandomWalk

while True:
    rw = RandomWalk(50000)
    rw.fill_walk()
    # 设置绘图窗口的尺寸(单位英寸，python假定屏幕为80像素每英寸)
    plt.figure(figsize=(10,6))
    # 获得随机漫步点序列
    point_numbers = list(range(rw.num_point))
    # 渐变着色
    plt.scatter(rw.x_values, rw.y_values, c=point_numbers, cmap=plt.cm.Blues, s=1)
    # 起点和终点特异标注
    plt.scatter(0, 0, c="green", s=100)
    plt.scatter(rw.x_values[-1], rw.y_values[-1], c="red", s=100)
    # 隐藏坐标轴
    plt.axes().get_xaxis().set_visible(False)
    plt.axes().get_yaxis().set_visible(False)

    plt.show()

    keep_running = input("进行另一次随机漫步吗（y/n）")
    if keep_running == "n":
        break
