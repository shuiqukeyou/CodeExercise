# 打开文件
# with结构自带close()方法
# win下使用“\”，linux使用“/”（视版本和开发环境有时候不用区分）
# 由于\又是转义标记，故在使用\时，在字符串前加上r声明为原始字符串最保险
with open(r"./data/pi_digits.txt") as file_object:
    contents = file_object.read()
    # read方法在到达文件尾部时会返回一个空行
    # 输出时调用.rstrip()清除掉空行
    print(contents.rstrip())

# 逐行读取
with open(r"./data/pi_digits.txt") as file_object:
    # 逐行读取时每行后面带一个空行
    # 因为print()输出时最后会给一个换行符，而文件的每行又自带一个换行符
    for line in file_object:
        print(line)

# 按行list化
with open(r"./data/pi_digits.txt") as file_object:
    # 将文件按行转换为list
    # 这个list可以在with之外访问
    lines = file_object.readlines()
for line in lines:
    print(line)
pi_str = ""
for line in lines:
    # .rstrip()仅清除换行
    # .strip()清除所有空白符
    pi_str += line.strip()
print(pi_str)
# 字符替换:替换所有的3为@，非原地替换
print(pi_str.replace("3", "@"))
print(pi_str)

# 打开&编辑文件，r：读、w：写、a：添加模式
# 打开文件模式为w（写入），因为自带close，所以有自动保存
# 以写入模式打开文件是，python会创建该文件，如果文件已存在，则会被覆盖，若要编辑文件，请使用a模式
# write()方法只接受字符串内容传入
with open("./data/programming.txt", 'w') as file_object:
    file_object.write("我喜欢写代码。")
    file_object.write("换行测试\n")
    file_object.write("换行测试")

# 异常捕捉和处理
try:
    print(5/0)
except ZeroDivisionError as e:
    print("发生除以0错误，已捕捉错误对象为e")

# JSON文件
# json相关处理需要导包
import json

numbers = [2, 4, 5, 7, 8, 9]

with open("./data/numbers.json", 'w') as file_object:
    # 将list按json格式储存到文件中
    json.dump(numbers, file_object)

with open("./data/numbers.json") as file_object:
    numbers2 = json.load(file_object)
print(numbers2)
