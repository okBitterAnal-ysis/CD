import csv

def processData(x):
    return x * 2

def validateDevice(x):
    return "Valid" if 0 <= x <= 500 else "Invalid"

with open("rpc_calls_1.csv") as f:
    for r in csv.DictReader(f):
        x = int(r["param_value"])
        m = r["method_name"]
        print("DISPATCH", m)

        if m == "processData":
            print("RETURN", processData(x))
        else:
            print("RETURN", validateDevice(x))