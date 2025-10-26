package org.example;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class MSTTest {

    @Test
    public void testSmallGraphCorrectness() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        Graph g = new Graph(nodes);
        g.addEdge("A", "B", 1);
        g.addEdge("B", "C", 2);
        g.addEdge("C", "D", 3);
        g.addEdge("A", "C", 4);
        g.addEdge("B", "D", 5);

        MSTResult p = PrimMST.run(g);
        MSTResult k = KruskalMST.run(g);

        assertEquals(p.totalCost, k.totalCost, 1e-6,
                "Prim and Kruskal should produce the same MST total cost");

        assertEquals(g.vertexCount() - 1, p.mstEdges.size(),
                "Prim MST should have V-1 edges");
        assertEquals(g.vertexCount() - 1, k.mstEdges.size(),
                "Kruskal MST should have V-1 edges");

        Set<Integer> covered = new HashSet<>();
        for (Edge e : p.mstEdges) {
            covered.add(e.u);
            covered.add(e.v);
        }
        assertEquals(g.vertexCount(), covered.size(),
                "MST should cover all vertices");
    }

    @Test
    public void testDisconnectedHandled() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        Graph g = new Graph(nodes);
        g.addEdge("A", "B", 1);

        MSTResult p = PrimMST.run(g);
        MSTResult k = KruskalMST.run(g);

        assertTrue(p.mstEdges.size() < g.vertexCount() - 1,
                "Prim should produce incomplete MST for disconnected graph");
        assertTrue(k.mstEdges.size() < g.vertexCount() - 1,
                "Kruskal should produce incomplete MST for disconnected graph");
    }
}
