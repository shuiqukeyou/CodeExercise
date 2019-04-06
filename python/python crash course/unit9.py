# 括号内为继承父类，不填入时默认为object
class Dog(object):
    # 初始化方法，每次创建新对象时使用
    # 本质就是个方法，所以各种形参的变化也可用
    def __init__(self, name, age=5):
        self.name = name
        self.age = age

    def sit(self):
        print("坐下")

    def pork(self):
        print(self.name)

    def older(self):
        self.age += 1


dog = Dog("poi", 8)
print(dog.age)
dog.sit()
dog.pork()


# 继承
class Labrador(Dog):
    def __init__(self, name, age):
        # super()指调用父类方法，此处为调用父类的初始化方法，参数用自己的这个
        super().__init__(name, age)
        # 使用对象做属性
        self.collar = Collar("red", "big")

    def find_road(self):
        print("寻路成功")

    # 覆盖父类方法
    def sit(self):
        print("老老实实坐下")


class Collar(object):
    def __init__(self, color, size):
        self.color = color
        self.size = size


lab = Labrador("dio", 10)
lab.sit()
lab.collar.size