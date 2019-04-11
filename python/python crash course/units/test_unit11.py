import unittest
from units.unit11 import AnonymousSurvey


# # 对get_formatted_name进行单元测试
# class NameTestCase(unittest.TestCase):
#
#     def test_first_last_name(self):
#         # 调用要被测试的"get_formatted_name"
#         formatted_name = get_formatted_name("janis", "joplin")
#         # 对输出结果进行断言
#         self.assertEqual(formatted_name, "Janis Joplin")
#         # 一些其他常用断言方法
#         '''
#         self.assertNotEqual(a, b)
#         self.assertTrue(x)
#         self.assertFalse(x)
#         self.assertIn(item, list)
#         self.assertNotIn(item, list)
#         '''
#
#     # 当单元测试运行时，所有test_开头的方法将会被运行
#     def test_self(self):
#         print("测试")

# 多个测试
# class TestAnonymousSurvey(unittest.TestCase):
#
#     def setUp(self):
#         question = "你先学会什么语言"
#
#     def test_store_single_response(self):
#         question = "你最先学会什么语言"
#         my_survey = AnonymousSurvey(question)
#         my_survey.store_response("English")
#
#         self.assertIn("English", my_survey.responses)
#
#     def test_store_three_response(self):
#         question = "你最先学会什么语言"
#         my_survey = AnonymousSurvey(question)
#         responses = ["English", "Chinese", "Japanese"]
#         for response in responses:
#             my_survey.store_response(response)
#         for response in responses:
#             self.assertIn(response, my_survey.responses)


class TestAnonymousSurvey(unittest.TestCase):

    def setUp(self):
        question = "你先学会什么语言"
        self.my_survey = AnonymousSurvey(question)
        self.responses = ["English", "Chinese", "Japanese"]

    def test_store_single_response(self):

        self.my_survey.store_response(self.responses[0])
        self.assertIn("English", self.my_survey.responses)

    def test_store_three_response(self):
        for response in self.responses:
            self.my_survey.store_response(response)
        for response in self.responses:
            self.assertIn(response, self.my_survey.responses)


if __name__ == '__main__':
    unittest.main()
