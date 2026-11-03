import csv
from concurrent.futures import ThreadPoolExecutor

with open("matrix_22.csv") as f:
    m = [[int(r["col_" + str(i)]) for i in range(4)]
         for r in csv.DictReader(f)]

def multiply(row):
    return [sum(row[k] * m[k][j] for k in range(4)) for j in range(4)]

print("Scatter: Assigning rows")

with ThreadPoolExecutor(4) as pool:
    result = list(pool.map(multiply, m))

print("Gather: Final Matrix")
for row in result:
    print(row)