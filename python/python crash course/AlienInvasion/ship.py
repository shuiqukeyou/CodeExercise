import pygame

class Ship(object):

    def __init__(self, ai_settings, screen):
        self.screen = screen
        self.ai_settings = ai_settings

        # 加载图像并取其外接矩形
        self.image = pygame.image.load("./images/ship.bmp")
        self.rect = self.image.get_rect()
        self.screen_rect = screen.get_rect()

        # 设置飞船坐标（底部中央）
        self.rect.centerx = self.screen_rect.centerx
        self.rect.bottom = self.screen_rect.bottom

        # 位置
        self.center = float(self.rect.centerx)

        # 移动标志
        self.moving_right = False
        self.moving_left = False

    def update(self):
        # 更新center值
        # 如果有右移事件且坐标小于窗口右边框
        if self.moving_right and self.rect.right < self.screen_rect.right:
            self.center += self.ai_settings.ship_speed_factor

        # 如果有左移事件且坐标小于窗口左边框
        if self.moving_left and self.rect.left > 0:
            self.center -= self.ai_settings.ship_speed_factor

        # 根据center更新rect
        self.rect.centerx = self.center

    # 绘制飞船
    def blitme(self):
        self.screen.blit(self.image, self.rect)

    # 重置位置
    def center_ship(self):
        self.center = self.screen_rect.centerx