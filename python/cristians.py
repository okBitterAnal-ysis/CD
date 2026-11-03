import csv

with open("cristian_clocks_8.csv") as f:
    for r in csv.DictReader(f):
        t0 = float(r["t0_send_ms"])
        ts = float(r["t_server_ms"])
        t1 = float(r["t1_recv_ms"])

        rtt = t1 - t0
        estimated = ts + rtt / 2
        offset = estimated - t1

        print("Client:", r["client_id"])
        print("RTT:", rtt)
        print("Estimated Time:", estimated)
        print("Adjusted Time:", t1 + offset)