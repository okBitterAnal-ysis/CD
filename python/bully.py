import csv

alive = {}

with open("bully_cluster_2.csv") as f:
    for r in csv.DictReader(f):
        alive[int(r["node_id"])] = r["is_alive"].upper() == "TRUE"

def election(n):
    higher = [x for x in alive if x > n]
    active = []

    print("\nNode", n, "starts election")

    for x in higher:
        print(n, "sends election to", x)
        if alive[x]:
            print(x, "replies OK")
            active.append(x)
        else:
            print(x, "is DOWN")

    if active:
        election(min(active))
    else:
        print("Node", n, "elected Coordinator")

election(2)