import csv

nodes = []

with open("node_clocks_3.csv") as f:
    for r in csv.DictReader(f):
        if r["is_faulty"].upper() == "FALSE":
            nodes.append( (r["node_id"], float(r["clock_time_sec"])) )
        else:
            print(r["node_id"], "Faulty - ignored")

avg = sum(t for n, t in nodes) / len(nodes)

print("Average Time:", avg)

for n, t in nodes:
    print(n, "Adjustment:", avg - t)