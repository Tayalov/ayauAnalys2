package org.example;

import java.util.*;

public class Graph {
    private final int V;
    private final List<Edge> edges = new ArrayList<>();
    private final List<List<Edge>> adj;
    private final List<String> labels;
    private final Map<String,Integer> labelToIndex = new HashMap<>();

    public Graph(List<String> labels) {
        this.V = labels.size();
        this.labels = new ArrayList<>(labels);
        for (int i = 0; i < labels.size(); i++) labelToIndex.put(labels.get(i), i);
        this.adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(String fromLabel, String toLabel, double w) {
        Integer u = labelToIndex.get(fromLabel);
        Integer v = labelToIndex.get(toLabel);
        if (u == null || v == null) throw new IllegalArgumentException("Unknown node label");
        addEdge(u, v, w);
    }

    public void addEdge(int u, int v, double w) {
        Edge e = new Edge(u, v, w);
        edges.add(e);
        adj.get(u).add(e);
        adj.get(v).add(e);
    }

    public int vertexCount() { return V; }
    public int edgeCount() { return edges.size(); }
    public List<Edge> getEdges() { return Collections.unmodifiableList(edges); }
    public List<List<Edge>> getAdj() { return adj; }
    public List<String> getLabels() { return Collections.unmodifiableList(labels); }
    public String labelOf(int idx) { return labels.get(idx); }
}
