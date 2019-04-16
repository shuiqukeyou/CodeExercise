import requests
import pygal

from pygal.style import LightColorizedStyle as LCS, LightenStyle as LS

url = "https://api.github.com/search/repositories?q=language:python&sort=stars"

r = requests.get(url)
print("响应码:" + str(r.status_code))

response_dict = r.json()
# print("Total Repositories:", response_dict["total_count"])

repo_dicts = response_dict["items"]
# print("Repositories returned:", len(repo_dicts))

# names, stars = [], []

# repo_dict = repo_dicts[0]
# print("\nKeys:",len(repo_dict))
# for key in sorted(repo_dict.keys()):
#     print(key)

# print("每个项目的相关信息")
# for repo_dict in repo_dicts:
# print("Name:",repo_dict["name"])
# print("Owner:",repo_dict["owner"]['login'])
# print("Stars:",repo_dict["stargazers_count"])
# print("Repository:",repo_dict["html_url"])
# print("Created:",repo_dict["created_at"])
# print("updated:",repo_dict["updated_at"])
# print("Description:",repo_dict["description"])
# names.append(repo_dict["name"])
# stars.append(repo_dict["stargazers_count"])

names, plot_dicts = [], []
for repo_dict in repo_dicts:
    names.append(repo_dict["name"])

    plot_dict = {
        "value": repo_dict["stargazers_count"],
        "label": repo_dict["description"],
        "xlink": repo_dict["html_url"]
    }
    plot_dicts.append(plot_dict)

# SS的当前star数为29514，但是SS项目删除了自己的描述，该值为空，会引起BUG
for plot_dict in plot_dicts:
    for i in plot_dict:
        if plot_dict[i] == None:
            plot_dict[i] = " "
            print("发现空项目")
            print(plot_dict)
            print(i)

# 定义样式
my_styel = LS("#333366", base_style=LCS)

# 自定义样式
# 样式实例
my_config = pygal.Config()
# x标签旋转角度
my_config.x_label_rotation = 45
# 不显示标签
my_config.show_legend = False
# 图标标题字号
my_config.title_font_size = 24
# 副标签字号
my_config.label_font_size = 14
# 主标签字号
my_config.major_label_font_size = 18
# 标签省略到15个字符（鼠标移上去之后可以看到完整版）
my_config.truncate_label = 15
# 不显示Y坐标轴的水平线
my_config.show_y_guides = False
# 自定义图表宽度
my_config.width = 1000
# 设置样式
chart = pygal.Bar(my_config, styel=my_styel)

# # 自带样式
# chart = pygal.Bar(style=my_styel, x_label_rotation=40,show_legend=False)


chart.title = "最多star的一些python项目"
chart.x_labels = names
# 添加数据
# chart.add("", stars)
chart.add("", plot_dicts)
chart.render_to_file("python项目报告3.svg")
