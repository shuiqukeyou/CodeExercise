def get_formatted_name(first, last):
    full_name = first + " " + last
    return full_name.title()


if __name__ == '__main__':
    print("任何情况下输入q退出")
    while True:
        first = input("请输入姓：")
        if first == "q":
            break
        last = input("请输入名:")
        if last == "q":
            break
        formatted_name = get_formatted_name(first, last)
        print("\t全名是：" + formatted_name)


# 调查类
class AnonymousSurvey(object):

    def __init__(self, question):
        self.question = question
        self.responses = []

    def show_question(self):
        print(self.question)

    def store_response(self, new_response):
        self.responses.append(new_response)

    def show_results(self):
        print("调查结果:")
        for response in self.responses:
            print("-" + response)


question1 = "你最先学会的语言是什么"
my_survey = AnonymousSurvey(question1)

my_survey.show_question()
print("任何时候输入q退出")
while True:
    response = input("语言：")
    if response == "q":
        break
    my_survey.store_response(response)

print("谢谢你参加调查")
my_survey.show_results()