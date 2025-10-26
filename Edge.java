package org.example;

public class Edge implements Comparable<Edge> {
    public final int u;
    public final int v;
    public final double weight;

    public Edge(int u, int v, double weight) {
        if (u < 0 || v < 0) throw new IllegalArgumentException("Vertex index must be non-negative");
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return String.format("(%d - %d : %.2f)", u, v, weight);
    }
}
