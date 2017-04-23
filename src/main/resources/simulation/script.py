from __future__ import division

import random
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.animation as animation


def add_events(data, csv):
    cur_data = pd.read_csv(csv)
    value = (cur_data['Close'] - cur_data['Open']) / cur_data['Open']
    events = np.asarray(value, dtype='|S6').astype(np.float)
    return np.concatenate([data, events])


# TODO sort events on date
# read data for few companies
events = add_events([], './data/PayPal.csv')
events = add_events(events, './data/Apple.csv')
count = events.__len__()

# users & events matrix
users_events = [[0 for e in range(count)] for u in range(1000)]

# fill stupid
stupid = int(users_events.__len__() / 10)
for i in range(stupid):
    for j in range(count):
        pred = random.randint(0, 100)
        users_events[i][j] = pred if pred != 50 else random.randint(0, 100)

# fill others
sure_percent = 1
sure = int(round(count * (sure_percent / 100)))
# percent of known event results from 1(%) to upper_bound(%)
upper_bound = 20  # see previous comment
step = int((users_events.__len__() - stupid) / upper_bound)
step_iter = 0
for i in range(stupid, users_events.__len__()):
    sure_idx = random.sample(range(count), sure)
    for j in range(count):
        if sure_idx.__contains__(j):
            if events[j] < 0:
                users_events[i][j] = random.randint(0, 49)
            else:
                users_events[i][j] = random.randint(51, 100)
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

# plt.hist(users, bins='auto')
# plt.title("User's weights")
# plt.show()
#
# plt.cla()

# It's possible to animate weights construction

# number_of_frames = 5
#
# def update_hist(num, data):
#     plt.cla()
#     plt.hist(data)
#
# fig = plt.figure()
# hist = plt.hist(users)
#
# animation = animation.FuncAnimation(fig, update_hist, number_of_frames, fargs=(users,), repeat=False)
# plt.show()

# count decision trades with regard to threshold: 50 +- offset (%)

offset = 5
all_trades = 0
profit_trades = 0
profit_decisions = []
for i in range(decisions.__len__()):
    decision = decisions[i]
    if decision == 50 or decision < 50 < decision + offset or decision > 50 > decision - offset:
        continue

    all_trades += 1
    if events[i] < 0 and decision < 50 or events[i] > 0 and decision > 50:
        profit_trades += 1
        profit_decisions.append(events[i])

# visualise results
print(decisions)
print("All trades count = " + str(all_trades))
print("Successful trades count = " + str(profit_trades))

plt.autoscale(enable=True, axis='both', tight=None)
event_count = 1

print("Users money before = " + str(sum(users_money)))
# invest all people's money here. Count profit.
for profit_decision in profit_decisions:
    abs_delta = 1 + abs(profit_decision)

    for i in range(len(users_money)):
        users_money[i] *= abs_delta

    plt.scatter(event_count, int(sum(users_money)))
    plt.pause(1)

    event_count += 1
plt.show()
plt.close()
print("Users money after = " + str(sum(users_money)))
