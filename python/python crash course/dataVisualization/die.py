from random import randint

class Die(object):

    def __init__(self, num_side=6):
        self.num_side = num_side

    def roll(self):
        # 返回一个1~面数间的随机整数
        return randint(1, self.num_side)