import pygame
from pygame.sprite import Sprite


class Alien(Sprite):

    def __init__(self, ai_settings, screen):
        super().__init__()
        self.screen = screen
        self.ai_settings = ai_settings

        # 加载图像，设置rect
        self.image = pygame.image.load("./images/alien.bmp")
        self.rect = self.image.get_rect()

        # 设置初始位置（左上角）
        self.rect.x = self.rect.width
        self.rect.y = self.rect.height

        # 储存x坐标
        self.x = float(self.rect.x)

    # 绘制函数
    def blitme(self):
        self.screen.blit(self.image, self.rect)

    # 边缘检查函数
    def check_edges(self):
        screen_rect = self.screen.get_rect()
        if self.rect.right >= screen_rect.right:
            return True
        elif self.rect.left <= 0:
            return True

    # 更新函数
    def update(self):
        self.x += self.ai_settings.alien_speed_factor * self.ai_settings.fleet_direction
        self.rect.x = self.x