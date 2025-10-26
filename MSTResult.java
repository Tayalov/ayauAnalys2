package org.example;

import java.util.*;

public class MSTResult {
    public final List<Edge> mstEdges;
    public final double totalCost;
    public final int vertices;
    public final int edges;
    public final long comparisons;
    public final long unions;
    public final double timeMs;

    public MSTResult(List<Edge> mstEdges, double totalCost, int vertices, int edges, long comparisons, long unions, double timeMs) {
        this.mstEdges = new ArrayList<>(mstEdges);
        this.totalCost = totalCost;
        this.vertices = vertices;
        this.edges = edges;
        this.comparisons = comparisons;
        this.unions = unions;
        this.timeMs = timeMs;
    }
}
