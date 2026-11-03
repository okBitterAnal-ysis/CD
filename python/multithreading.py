import csv
import threading
import time

lock = threading.Lock()

def task(r):
    print(r["task_id"], "Processing")
    time.sleep(int(r["burst_ms"]) / 1000)

    with lock:
        print(r["task_id"], "Completed")

with open("tasks_4.csv") as f:
    rows = list(csv.DictReader(f))

threads = []
for r in rows:
    t = threading.Thread(target=task, args=(r,))
    threads.append(t)
    t.start()

for t in threads:
    t.join()

print("All tasks completed")