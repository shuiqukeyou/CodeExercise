# dict的定义方法
size_dict = {"bob": 43, "jon": 40, "mary": 36}
print(size_dict)
# dict允许动态添加内容,添加格式为dict[key] = value
size_dict["tom"] = 45
print(size_dict)
print(size_dict["tom"])
# 无返回删除
del size_dict["jon"]
print(size_dict)
# 遍历dict
# 需要现用dict.item()返回dict的所有项目
# dict内的item是无序的，所以dict不支持按序访问，遍历返回的顺序也和原顺序不一样
for name, size in size_dict.items():
    print("name:" + name)
    print("size:" + str(size))
# dict.keys()：返回dict的所有关键字
for name in size_dict.keys():
    print(name)
# dict.values()：返回dict的所有值
for value in size_dict.values():
    print(value)
# 直接遍历dict也是返回dict的所有关键字
for name in size_dict:
    print(name)
# 遍历时外部排序，无指定时以关键字为基准
for name in sorted(size_dict):
    print(name)
# 生成一个set
# set：无序、不可改变的元素集合
for name in set(size_dict.keys()):
    print(name)
