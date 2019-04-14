from random import choice


class RandomWalk(object):

    def __init__(self, num_point=5000):
        self.num_point = num_point

        self.x_values = [0]
        self.y_values = [0]

    def fill_walk(self):
        while len(self.x_values) < self.num_point:
            # 随机方向
            x_direction = choice([-1, 1])
            # 随机距离
            x_distance = choice([0, 1, 2, 4, 5])
            # 随机移动
            x_step = x_direction * x_distance

            y_direction = choice([-1, 1])
            y_distance = choice([0, 1, 2, 3, 4, 5])
            y_step = y_direction * y_distance

            # 不原地踏步
            if x_step == 0 and y_step == 0:
                continue

            # 根据上次的最终位置计算这次的位置
            next_x = self.x_values[-1] + x_step
            next_y = self.y_values[-1] + y_step

            # 将这次移动结果加入到位置list中
            self.x_values.append(next_x)
            self.y_values.append(next_y)
