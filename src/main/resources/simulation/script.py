from __future__ import division

import random
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.animation as animation
from matplotlib import style


def add_events(data, csv):
    cur_data = pd.read_csv(csv)
    value = (cur_data['Close'] - cur_data['Open']) / cur_data['Open']
    events = np.asarray(value, dtype='|S6').astype(np.float)
    return np.concatenate([data, events])


def weights_generator(num, weights_graph_arr):
    plt.cla()
    plt.hist(weights_graph_arr[num])


# TODO sort events on date
# read data for few companies
events = add_events([], './data/PayPal.csv')
events = add_events(events, './data/Apple.csv')
count = events.__len__()

# users & events matrix
users_events = [[0 for e in range(count)] for u in range(1000)]

# fill stupid
stupid = int(users_events.__len__() / 7)
for i in range(stupid):
    for j in range(count):
        pred = 0
        if random.random() > 0.5:
            pred = random.randint(0, 100)
        else:
            if (events[j] < 0):
                pred = random.randint(51, 100)
            else:
                pred = random.randint(0, 49)
        users_events[i][j] = pred if pred != 50 else random.randint(0, 100)

# fill others
sure_percent = 1
sure = int(round(count * (sure_percent / 100)))
# percent of known event results from 1(%) to upper_bound(%)
upper_bound = 50  # see previous comment
step = int((users_events.__len__() - stupid) / upper_bound)
step_iter = 0
for i in range(stupid, users_events.__len__()):
    sure_idx = random.sample(range(count), sure)
    for j in range(count):
        if sure_idx.__contains__(j):
            if events[j] < 0:
                users_events[i][j] = random.randint(0, 30)
            else:
                users_events[i][j] = random.randint(70, 100)
        else:
            pred = random.randint(0, 100)
            users_events[i][j] = pred if pred != 50 else random.randint(0, 100)
    step_iter += 1
    if step_iter == step:
        step_iter = 0
        sure_percent += 1
        sure = int(round(count * (sure_percent / 100)))

# user weights
users = [1 for i in range(users_events.__len__())]

# user money
users_money = [random.randint(1, 50) for i in range(len(users))]

# decisions
decisions = [0 for i in range(count)]

weights_graph_arr = []

# run simulation
for i in range(count):
    prod = 0
    weight_sum = 0

    for j in range(users.__len__()):
        pred = users_events[j][i]
        weight = users[j]
        if weight > 0:
            prod += weight * pred
            weight_sum += weight
    decisions[i] = int(round(prod / weight_sum))
    for j in range(users.__len__()):
        if decisions[i] > 50:
            users[j] += users_events[j][i] - 50
        else:
            users[j] += 50 - users_events[j][i]

    weights_graph_arr.append(list(users))

fig = plt.figure()

animation = animation.FuncAnimation(fig, weights_generator, count, fargs=(weights_graph_arr,), repeat=False, interval=1000)
plt.show()

# count decision trades with regard to threshold: 50 +- offset (%)

f = open('events.txt', 'w')
f.close()
print("Users money before = " + str(sum(users_money)))

offset = 10
all_trades = 0
profit_trades = 0
loss_trades = 0
for i in range(decisions.__len__()):
    decision = decisions[i]
    if decision == 50 or decision < 50 < decision + offset or decision > 50 > decision - offset:
        continue

    all_trades += 1
    multiplicator = 1
    if events[i] < 0 and decision < 50 or events[i] > 0 and decision > 50:
        profit_trades += 1
        multiplicator += abs(events[i])
    else:
        loss_trades += 1
        multiplicator -= abs(events[i])
    for i in range(len(users_money)):
        users_money[i] *= multiplicator
    f = open('events.txt', 'a')
    f.write(str(int(sum(users_money))) + '\n')
# visualise results
print(decisions)
print("All trades count = " + str(all_trades))
print("Successful trades count = " + str(profit_trades))
print("Loss trades count = " + str(loss_trades))

# plt.autoscale(enable=True, axis='both', tight=None)

print("Users money after = " + str(sum(users_money)))

style.use('fivethirtyeight')

fig = plt.figure()
ax1 = fig.add_subplot(1, 1, 1)

data = open('events.txt', 'r').read().split('\n')
data = data[:len(data) - 1]


def animate(i):
    # graph_data = open('example.txt', 'r').read()
    global data
    if i > len(data):
        i = len(data)
    # lines = graph_data.split('\n')
    xs = []
    ys = []
    # for line in lines:
    #     if len(line) > 1:
    #         x, y = line.split(',')
    #         xs.append(x)
    #         ys.append(y)

    for k in range(i):
        xs.append(k)
        ys.append(data[k])

    ax1.clear()
    ax1.set_ylim(20000, 40000)
    ax1.set_xlim(0, len(data))
    ax1.plot(xs, ys)


ani = animation.FuncAnimation(fig, animate, interval=500)
plt.show()
