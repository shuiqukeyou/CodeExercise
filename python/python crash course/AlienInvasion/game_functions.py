import sys

import pygame
from AlienInvasion.bullet import Bullet


# 按下响应
def check_keydown_events(event, ai_settings, screen, ship, bullets):
    # 左移右移
    if event.key == pygame.K_RIGHT:
        ship.moving_right = True
    elif event.key == pygame.K_LEFT:
        ship.moving_left = True
    # 发射子弹
    elif event.key == pygame.K_SPACE:
        fire_bullet(ai_settings, screen, ship, bullets)


# 弹起响应
def check_keyup_event(event, ship):
    if event.key == pygame.K_RIGHT:
        ship.moving_right = False
    elif event.key == pygame.K_LEFT:
        ship.moving_left = False


# 开火
def fire_bullet(ai_settings, screen, ship, bullets):
    # 小于上限数才能发射
    if len(bullets) < ai_settings.bullets_allowed:
        new_bullet = Bullet(ai_settings, screen, ship)
        bullets.add(new_bullet)


# 用户事件监听
def check_events(ai_settings, screen, ship, bullets):
    for event in pygame.event.get():
        # 若为退出事件
        if event.type == pygame.QUIT:
            sys.exit()
        # 若为按键事件
        # 按下
        elif event.type == pygame.KEYDOWN:
            check_keydown_events(event, ai_settings, screen, ship, bullets)
        # 弹起
        elif event.type == pygame.KEYUP:
            check_keyup_event(event, ship)


# 更新子弹
def update_bullets(bullets):
    bullets.update()
    # 删除已经超出屏幕边框的子弹
    for bullet in bullets.copy():
        if bullet.rect.bottom <= 0:
            bullets.remove(bullet)


def update_screen(ai_settings, screen, ship, bullets):
    # 每次循环都重绘屏幕
    screen.fill(ai_settings.bg_color)
    for bullet in bullets:
        bullet.draw_bullet()
    ship.blitme()
    # 更新屏幕
    pygame.display.flip()
