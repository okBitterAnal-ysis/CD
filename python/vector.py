import csv

p = ["P1", "P2", "P3"]
clock = {x: [0, 0, 0] for x in p}
messages = {}

with open("vector_events_6.csv") as f:
    for r in csv.DictReader(f):
        x, action, target = r["process_id"], r["event_action"], r["target_process"]
        i = p.index(x)

        if action == "SEND":
            clock[x][i] += 1
            messages[x + target] = clock[x].copy()
        elif action == "RECEIVE":
            msg = messages[target + x]
            clock[x] = [max(a, b) for a, b in zip(clock[x], msg)]
            clock[x][i] += 1
        else:
            clock[x][i] += 1

        print(r["event_id"], clock[x])