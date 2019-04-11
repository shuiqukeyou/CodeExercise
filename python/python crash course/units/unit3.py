zoo_list = ["cat", "bear", "dog"]
print(zoo_list)
# 末尾添加
zoo_list.append("lion")
print(zoo_list)
# 中间添加
zoo_list.insert(1, "tiger")
print(zoo_list)
# 按序删除，无返回 ，无对象
del zoo_list[0]
print(zoo_list)
# 按序删除，返回被删除的对象
animal = zoo_list.pop(2)
print(animal)
# 按值删除,无返回值（为空）
zoo_list.remove("lion")
print(zoo_list)


# 原生、原地排序
zoo_list = ["cat", "bear", "dog", "lion"]
zoo_list.sort()
print(zoo_list)
# 原生、原地反向排序
zoo_list.sort(reverse=True)
print(zoo_list)

zoo_list = ["cat", "bear", "dog", "lion"]
# 原生、返回新序列、反向排序
new_zoo_list = sorted(zoo_list, reverse=True)
print(zoo_list)
print(new_zoo_list)

# 原地反转list
zoo_list = ["cat", "bear", "dog", "lion"]
zoo_list.reverse()
print(zoo_list)
