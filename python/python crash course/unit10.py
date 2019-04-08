# 打开文件
# 这个结构自带close()方法
# win下使用“\”，linux使用“/”（视版本和开发环境有时候不用区分）
# 由于\又是转义标记，故在使用\时，在字符串前加上r声明为原始字符串最保险
with open(r"./data/pi_digits.txt") as file_object:
    # read方法在到达文件尾部时会返回一个空行
    contents = file_object.read()
    print(contents.rstrip())
