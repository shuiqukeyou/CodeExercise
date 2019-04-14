import pygal

from dataVisualization.die import Die

die1 = Die()
die2 = Die(10)

results = []
for roll_num in range(50000):
    result = die1.roll() + die2.roll()
    results.append(result)

frequencies = []
max_result = die1.num_side + die2.num_side
for value in range(1, max_result + 1):
    # 统计各种值出现的次数
    frequency = results.count(value)
    frequencies.append(frequency)

print(frequencies)

hist = pygal.Bar()

hist.title = "Result of rolling a D6 and a D10 50000 times."
hist.x_labels = ["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"]
hist.x_title = "Result"
hist.y_title = "Frequency of Result"

hist.add("D6 + D6", frequencies)
hist.render_to_file("die_visual.svg")
