import pygame
from pygame.sprite import Sprite


# 子弹类
class Bullet(Sprite):

    def __init__(self, ai_sittings, screen, ship):
        super(Bullet, self).__init__()
        self.screen = screen

        # 创建子弹并且设置位置(飞船当前位置，并且从飞船顶部出现)
        self.rect = pygame.Rect(0, 0, ai_sittings.bullet_width, ai_sittings.bullet_height)
        # X坐标初始化之后将不再变化
        self.rect.centerx = ship.rect.centerx
        self.rect.top = ship.rect.top

        # 子弹位置储存
        self.y = float(self.rect.y)

        self.color = ai_sittings.bullet_color
        self.speed_factor = ai_sittings.bullet_speed_factor

    # 更新子弹数据
    def update(self):
        self.y -= self.speed_factor
        self.rect.y = self.y

    # 重绘制子弹图像
    def draw_bullet(self):
        pygame.draw.rect(self.screen, self.color, self.rect)
