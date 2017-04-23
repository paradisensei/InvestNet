from __future__ import division

import random
import numpy as np
import pandas as pd


def add_events(data, csv):
    cur_data = pd.read_csv(csv)
    events = np.asarray(cur_data['Close'] - cur_data['Open'], dtype='|S6').astype(np.float)
    return np.concatenate([data, events])


# read data for few companies
events = add_events([], './data/PayPal.csv')
events = add_events(events, './data/Apple.csv')
count = events.__len__()

# users & events matrix
users_events = [[0 for e in range(count)] for u in range(100)]

# fill stupid
stupid = int(users_events.__len__() / 10)
for i in range(stupid):
    for j in range(count):
        pred = random.randint(0, 100)
        users_events[i][j] = pred if pred != 50 else random.randint(0, 100)

# fill others
sure_percent = 1
sure = int(round(count * (sure_percent / 100)))
step = int((users_events.__len__() - stupid) / 50)
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

# visualise results
print(decisions)
print(users)
