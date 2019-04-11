import pygame
from pygame.sprite import Group

from AlienInvasion.setting import Setting
from AlienInvasion.ship import Ship
import AlienInvasion.game_functions as gf


# 运行游戏入口
def run_game():
    # pygame初始化
    pygame.init()
    # 属性对象初始化
    ai_settings = Setting()
    # 设置窗口大小
    screen = pygame.display.set_mode((ai_settings.screen_width, ai_settings.screen_height))
    # 设置标题
    pygame.display.set_caption("外星人入侵")
    # 飞船对象
    ship = Ship(ai_settings, screen)
    # 子弹编组
    bullets = Group()

    # 主循环
    while True:
        # 用户事件监听
        gf.check_events(ai_settings, screen, ship, bullets)
        # 监听用户事件后重新绘制单位
        ship.update()
        # 更新子弹
        gf.update_bullets(bullets)
        # 更新屏幕
        gf.update_screen(ai_settings, screen, ship, bullets)


if __name__ == '__main__':
    run_game()
