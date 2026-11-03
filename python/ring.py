import csv

nodes = {}

with open("ring_nodes_15.csv") as f:
    for r in csv.DictReader(f):
        nodes[int(r["node_id"])] = (
            int(r["next_node_id"]), r["is_active"].upper() == "TRUE")

start = current = 2
token = []

print("Node 2 starts election")

while True:
    token.append(current)
    nxt = nodes[current][0]

    while not nodes[nxt][1]:
        print("Node", nxt, "is DOWN")
        nxt = nodes[nxt][0]

    if nxt == start:
        break

    print("Token", token, "passed to", nxt)
    current = nxt

leader = max(token)
print("Token returned:", token)
print("Node", leader, "elected Coordinator")