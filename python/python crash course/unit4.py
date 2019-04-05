zoo_list = ["cat", "bear", "dog", "lion"]
for animal in zoo_list:
    print(animal)
# range(a,b)生成数字序列（非list），生成范围为a~b-1
for num in range(1, 5):
    print(num)
# range方法返回的对象就是range对象
print(type(range(1, 5)))
# 转换range对象为list对象
num_list = list(range(1, 5))
print(type(num_list))
# 返回list的最小、最大、和（如果可以求和的话）
print(min(num_list))
print(max(num_list))
print(sum(num_list))

# 使用引用对象作为元素生成list 时，使用引用传值，同步修改
temp = 5
num_list.append(temp)
print(id(temp))
print(id(num_list[4]))

# 另一种创建list的方法
# 可对for方法产生的对象进行预处理
num_list = [num**2 for num in range(1, 10)]
print(num_list)
# 切片
# [a:b]，范围为a~b-1
# [a:]，范围为a~最后一个
# [:b]，范围为第一个~b-1
# [-a,]，范围为倒数第a个~最后一个
print(num_list[1:4])
# [a:b:c]，范围为a~b-1，且每隔c个切片一次
print(num_list[1:8:2])
# 原样切片（复制列表）
num_list2 = num_list[:]
print(num_list2)

# tuple:不可修改的list
num_tuple = (1, 2, 3, 4, 5, 6)
print(num_tuple)
# 不可修改其中的值，如果使用引用对象生成tuple，引用对象在tuple之中将会是一个新副本
temp = 7
num_tuple = (1, 2, 3, 4, 5, 6, temp)
print(num_tuple)
temp = 9
print(num_tuple)
print(id(temp))
print(id(num_tuple[6]))
