import csv
from datetime import datetime

from matplotlib import pylab as plt

# filename = "./data/sitka_weather_2014.csv"
# 数据集部分缺失测试
filename = "./data/death_valley_2014.csv"
with open(filename) as f:
    reader = csv.reader(f)
    # 返回下一行，只调用一次则只返回第一行
    header_row = next(reader)

    dates, highs, lows = [], [], []
    # 最高气温list
    highs = []
    for row in reader:
        # 格式化最高气温并记录
        try:
            current_date = datetime.strptime(row[0], "%Y-%m-%d")
            high = int(row[1])
            low = int(row[3])
        except ValueError:
            print(current_date, "缺少数据")
        else:
            dates.append(current_date)
            highs.append(high)
            lows.append(low)
    # print(highs)
    # # 获取每行的序号和索引值
    # for index, column_header in enumerate(header_row):
    #     print(index, column_header)

    # 创建图形
    fig = plt.figure(dpi=128, figsize=(10, 6))
    # alpha:不透明度
    plt.plot(dates, highs, c="red", alpha=0.5)
    plt.plot(dates, lows, c="blue",alpha=0.5)
    # 线间填充：x坐标、参考线1、参考线2、颜色、不透明度
    plt.fill_between(dates, highs, lows, facecolor="blue", alpha=1)

    # 设置图形
    plt.title("Daily high and low temperatures,2014", fontsize=24)
    plt.title("Daily high and low temperatures,2014\nDeath valley", fontsize=24)
    plt.xlabel("",fontsize=16)
    fig.autofmt_xdate()
    plt.ylabel("Temperature(F)",fontsize=16)
    plt.tick_params(axis="both",which="major",labelsize=16)

    plt.show()