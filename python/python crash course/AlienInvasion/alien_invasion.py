import pygame
from pygame.sprite import Group

from AlienInvasion.setting import Setting
from AlienInvasion.ship import Ship
from AlienInvasion.alien import Alien
from AlienInvasion.game_stats import GameStats
from AlienInvasion.button import Button
from AlienInvasion.scoreboard import Scoreboard
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
    # 设置统计器对象
    stats = GameStats(ai_settings)
    sb = Scoreboard(ai_settings, screen, stats)

    # 飞船对象
    ship = Ship(ai_settings, screen)
    # 子弹编组
    bullets = Group()
    # 外星人对象
    aliens = Group()
    gf.create_fleet(ai_settings, screen, ship, aliens)

    # 创建开始按钮
    play_button = Button(ai_settings, screen, "Play")

    # 主循环
    while True:
        # 用户事件监听
        gf.check_events(ai_settings, screen, stats, play_button, sb, ship, aliens, bullets)
        if stats.game_active:
            # 监听用户事件后重新绘制单位
            ship.update()
            # 更新子弹
            gf.update_bullets(ai_settings, screen, stats, sb, ship, aliens, bullets)
            # 更新外星人
            gf.update_aliens(ai_settings, screen, stats, sb, ship, aliens, bullets)
        # 更新屏幕
        gf.update_screen(ai_settings, screen, stats, sb, ship, aliens, bullets, play_button)


if __name__ == '__main__':
    run_game()
