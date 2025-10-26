package org.example;

import java.util.*;

public class KruskalMST {
    public static MSTResult run(Graph g) {
        long comps = 0;
        long unions = 0;

        long start = System.nanoTime();

        List<Edge> edges = new ArrayList<>(g.getEdges());
        Collections.sort(edges);
        comps += edges.size();

        DisjointSet ds = new DisjointSet(g.vertexCount());
        List<Edge> result = new ArrayList<>();

        for (Edge e : edges) {
            comps++;
            if (ds.find(e.u) != ds.find(e.v)) {
                ds.union(e.u, e.v);
                result.add(e);
            }
            if (result.size() == g.vertexCount() - 1) break;
        }

        unions = ds.getUnions();
        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;
        double total = result.stream().mapToDouble(x -> x.weight).sum();
        return new MSTResult(result, total, g.vertexCount(), g.edgeCount(), comps, unions, timeMs);
    }
}
