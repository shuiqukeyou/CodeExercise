import pygame

class Button():

    def __init__(self, ai_settings, screen, msg):
        self.screen = screen
        self.screen_rect = screen.get_rect()

        # 设置尺寸及其他属性
        self.width, self.height = 200, 50
        self.button_color = (0, 255, 0)
        self.text_color = (255, 255 ,255)
        self.font = pygame.font.SysFont(None, 48)

        # 按键的rect
        self.rect = pygame.Rect(0, 0, self.width, self.height)
        self.rect.center = self.screen_rect.center

        # 输入信息
        self.prep_msg(msg)

    # 渲染msg为图形，并显示在按钮上层
    def prep_msg(self, msg):
        self.msg_image = self.font.render(msg, True, self.text_color, self.button_color)
        self.msg_iamge_rect = self.msg_image.get_rect()
        self.msg_iamge_rect.center = self.rect.center

    # 绘制按钮
    def draw_button(self):
        self.screen.fill(self.button_color, self.rect)
        self.screen.blit(self.msg_image, self.msg_iamge_rect)
