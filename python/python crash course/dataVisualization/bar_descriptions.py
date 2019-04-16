import pygal

from pygal.style import LightColorizedStyle as LCS, LightenStyle as LC

my_style = LC("#333366", base_style=LCS)
chart = pygal.Bar(style=my_style, x_lable_rotation=45, show_legend=False)

chart.title = "python 项目"
chart.x_labels = ["awesome-python", "system design primer", "public-apis"]

plot_dicts = [{"value": 65833, "label": "Description of awesome-python"},
              {"value": 61473, "label": "Description of system design primer"},
              {"value": 55743, "label": "Description of public-apis"}]

# 根据dict形成条状图
chart.add("", plot_dicts)
chart.render_to_file("项目描述条.svg")
