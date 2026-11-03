import csv

workers = ["W1", "W2", "W3"]
load = [0, 0, 0]
tasks = [0, 0, 0]

with open("workload_5.csv") as f:
    for i, r in enumerate(csv.DictReader(f)):
        w = i % 3
        load[w] += int(r["compute_units"])
        tasks[w] += 1
        print(r["job_id"], "assigned to", workers[w])

for i in range(3):
    print(workers[i], "Jobs:", tasks[i], "Load:", load[i])