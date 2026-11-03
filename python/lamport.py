import csv

clock = {}
messages = {}

with open("events_7.csv") as f:
    for r in csv.DictReader(f):
        p = r["process_id"]
        clock.setdefault(p, 0)

        if r["event_type"] == "INTERNAL":
            clock[p] += 1
        elif r["event_type"] == "SEND":
            clock[p] += 1
            messages[p + r["message_to"]] = clock[p]
        else:
            sender = r["message_to"]
            clock[p] = max(clock[p], messages[sender + p]) + 1

        print(r["event_id"], p, "Clock =", clock[p])