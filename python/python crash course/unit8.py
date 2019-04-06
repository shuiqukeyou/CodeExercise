# 参数默认值指定，没有默认值的形参必须在有默认值的之前
def func(attr1, attr2=20):
    print("1:" + str(attr1))
    print("2:" + str(attr2))


# 定向实参传递，对于某些参数特别多但是又只需要传入一部分参数值时
func(attr1=50, attr2=0)
test_list = [5, 4, 7]


def func2(tlist):
    tlist.append(9)
    print(tlist)


# 传送list副本，传参时执行全切片
func2(test_list[:])
print(test_list)


# 任意数量参数，“*变量名”可以将收到的参数储存到一个tuple里，需要在形参表最后
def func3(attr1, *attrs):
    print(attr1)
    print(attrs)
    print(type(attrs))


func3(111, "aaa", "bbb", "ccc")


# 任意数量参数组，“**变量名”可以将收到的参数储存到一个dict里，需要在形参表最后
def func4(attr1, **attrs):
    print(attr1)
    print(attrs)


func4(222, a1="aa1", a2="aa2", a3="aa3")
