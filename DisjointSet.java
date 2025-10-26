package org.example;

public class DisjointSet {
    private final int[] parent;
    private final int[] rank;
    private long unions = 0;
    private long finds = 0;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int x) {
        finds++;
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx == ry) return false;
        unions++;
        if (rank[rx] < rank[ry]) parent[rx] = ry;
        else if (rank[ry] < rank[rx]) parent[ry] = rx;
        else { parent[ry] = rx; rank[rx]++; }
        return true;
    }

    public long getUnions() { return unions; }
    public long getFinds() { return finds; }
}
