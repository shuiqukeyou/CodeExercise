import re

# match是从头匹配
m = re.match('foo', 'foo') # 成功
m = re.match('foo', 'fot') # 失败
m = re.match('foo', 'foot') # 成功
m = re.match('foo', 'seafood') # 失败
if m is not None:
    print(m.group())

# search是从全文匹配
m = re.search('foo', 'seafood') # 成功
if m is not None:
    print(m.group())

# 匹配多个字符串
bt = 'bat|bet|bit'
m = re.match(bt, 'bat') # 成功
m = re.match(bt, 'bet') # 成功
m = re.match(bt, 'a bat') # 失败
m = re.search(bt, 'a bit') # 成功
if m is not None:
    print(m.group())

# 通配单个字符
bt = '.end'
m = re.match(bt, 'bend') # 成功
m = re.match(bt, 'end') # 失败
if m is not None:
    print(m.group())

# 模糊通配
bt = '[ac][19][514][bl]' # 第一个字符必须是a或c，第二个字符必须是1或9...
m = re.match(bt, 'c94l') # 成功
m = re.match(bt, 'ec94l') # 失败
m = re.search(bt, 'ec94l') # 成功
if m is not None:
    print(m.group())

