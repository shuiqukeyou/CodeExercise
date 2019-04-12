import sys
from time import sleep

import pygame
from AlienInvasion.bullet import Bullet
from AlienInvasion.alien import Alien


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
    elif event.key == pygame.K_q:
        sys.exit()


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


def update_screen(ai_settings, screen, ship, aliens, bullets):
    # 每次循环都重绘屏幕
    screen.fill(ai_settings.bg_color)
    # 更新子弹组
    for bullet in bullets:
        bullet.draw_bullet()
    # 更新飞船
    ship.blitme()
    # 更新外星人
    aliens.draw(screen)
    # 更新屏幕
    pygame.display.flip()


def get_number_aliens_x(ai_settings, alien_width):
    available_space_x = ai_settings.screen_width - 2 * alien_width
    # 计算可放置的外星人数量（int）
    number_alien_x = int(available_space_x / (2 * alien_width))
    return number_alien_x


def get_number_rows(ai_settings, ship_height, alien_height):
    available_space_y = ai_settings.screen_height - 3 * alien_height - ship_height
    number_rows = int(available_space_y / (2 * alien_height))
    return number_rows


def create_alien(ai_settings, screen, aliens, alien_number, row_number):
    # 创建一个外星人对象作为参考
    aline = Alien(ai_settings, screen)
    # 获取这个对象的宽度
    alien_width = aline.rect.width
    # 计算可用的X轴空间
    aline.x = alien_width + 2 * alien_width * alien_number
    aline.rect.x = aline.x
    aline.rect.y = aline.rect.height + 2 * aline.rect.height * row_number
    aliens.add(aline)



def create_fleet(ai_settings, screen, ship, aliens):
    # 创建一个外星人对象作为参考
    aline = Alien(ai_settings, screen)
    number_alien_x = get_number_aliens_x(ai_settings, aline.rect.width)
    number_rows = get_number_rows(ai_settings, ship.rect.height, aline.rect.height)

    # # 获取这个对象的宽度
    # alien_width = aline.rect.width
    # # 计算可用的X轴空间
    # available_space_x = ai_settings.screen_width - 2 * alien_width
    # # 计算可放置的外星人数量（int）
    # number_alien_x = int(available_space_x / (2 * alien_width))

    # 按行生成外星人群
    for row_number in range(number_rows):
        for alien_number in range(number_alien_x):
            create_alien(ai_settings, screen, aliens, alien_number, row_number)
            # # 创建对象
            # aline = Alien(ai_settings, screen)
            # # x坐标递增
            # aline.x = alien_width + 2 * alien_width * alien_number
            # # 将计算出的X坐标放到alien对象中
            # aline.rect.x = aline.x
            # # 将创建完的alien对象加入到aliens列表中
            # aliens.add(aline)


# 边缘检查
def check_fleet_edges(ai_settings, aliens):
    for alien in aliens.sprites():
        if alien.check_edges():
            change_fleet_direction(ai_settings, aliens)
            break


# 下移并变换方向
def change_fleet_direction(ai_settings, aliens):
    for alien in aliens.sprites():
        alien.rect.y += ai_settings.fleet_drop_speed
    ai_settings.fleet_direction *= -1


# 更新外星人
def update_aliens(ai_settings, stats, screen, ship, aliens, bullets):
    check_fleet_edges(ai_settings, aliens)
    aliens.update()

    # 检测碰撞
    if pygame.sprite.spritecollideany(ship, aliens):
        ship_hit(ai_settings, stats, screen, ship, aliens, bullets)
        ship.center_ship()


# 更新子弹
def update_bullets(ai_settings, screen, ship, aliens, bullets):
    bullets.update()
    # 删除已经超出屏幕边框的子弹
    for bullet in bullets.copy():
        if bullet.rect.bottom <= 0:
            bullets.remove(bullet)
    check_bullet_alien_collisions(ai_settings, screen, ship, aliens, bullets)


def check_bullet_alien_collisions(ai_settings, screen, ship, aliens, bullets):
    collisions = pygame.sprite.groupcollide(bullets, aliens, True, True)
    # 若外星人数量为0
    if len(aliens) == 0:
        bullets.empty()
        create_fleet(ai_settings, screen, ship, aliens)


def check_aliens_bottom(ai_settings, stats, screen, ship, aliens, bullets):
    screen_rect = screen.get_rect()
    # 检测所有外星人
    for alien in aliens.sprites():
        if alien.rect.bottom >= screen_rect.bottom:
            # 如果到达底端按飞船被击中处理
            ship_hit(ai_settings, stats, screen, ship, aliens, bullets)


def ship_hit(ai_settings, stats, screen, ship, alien, bullets):
    if stats.ship_left > 0:
        # 生命-1
        stats.ship_left -= 1

        # 清空外星人和子弹
        alien.empty()
        bullets.empty()

        # 创建新的外星人并重置飞船位置
        create_fleet(ai_settings, screen, ship, alien)
        ship.center_ship()

        sleep(0.5)
    else:
        stats.game_active = False

