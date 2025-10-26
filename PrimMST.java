package org.example;

import java.util.*;

public class PrimMST {
    public static MSTResult run(Graph g) {
        final int V = g.vertexCount();
        long comps = 0;
        boolean[] inMST = new boolean[V];

        long start = System.nanoTime();

        class Entry implements Comparable<Entry> {
            final double w; final int from; final int to;
            Entry(double w, int from, int to) { this.w = w; this.from = from; this.to = to; }
            public int compareTo(Entry o) { return Double.compare(this.w, o.w); }
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>();
        pq.add(new Entry(0.0, -1, 0));
        List<Edge> result = new ArrayList<>();

        while (!pq.isEmpty() && result.size() < V - 1) {
            Entry e = pq.poll();
            comps++;
            int to = e.to;
            if (inMST[to]) continue;
            inMST[to] = true;
            if (e.from >= 0) result.add(new Edge(e.from, e.to, e.w));
            for (Edge adj : g.getAdj().get(to)) {
                int other = (adj.u == to) ? adj.v : adj.u;
                if (!inMST[other]) {
                    pq.add(new Entry(adj.weight, to, other));
                    comps++;
                }
            }
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;
        double total = result.stream().mapToDouble(x -> x.weight).sum();
        return new MSTResult(result, total, g.vertexCount(), g.edgeCount(), comps, 0L, timeMs);
    }
}
