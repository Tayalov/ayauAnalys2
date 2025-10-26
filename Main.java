package org.example;

import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> inputs = Arrays.asList("input_small.json", "input_medium.json", "input_large.json");
        List<MSTResult> prims = new ArrayList<>();
        List<MSTResult> kruskals = new ArrayList<>();
        List<Graph> graphsForJson = new ArrayList<>();
        List<Integer> graphIds = new ArrayList<>();

        int globalId = 1;
        for (String inputFile : inputs) {
            System.out.println("Reading " + inputFile);
            List<Graph> graphs = JSONUtils.readGraphs(Path.of(inputFile));
            for (Graph g : graphs) {
                System.out.printf("Processing graph id=%d V=%d E=%d%n", globalId, g.vertexCount(), g.edgeCount());
                MSTResult p = PrimMST.run(g);
                MSTResult k = KruskalMST.run(g);

                prims.add(p);
                kruskals.add(k);
                graphsForJson.add(g);
                graphIds.add(globalId);

                System.out.printf(Locale.ROOT, "Graph %d: Prim cost=%.2f time=%.2fms ops=%d | Kruskal cost=%.2f time=%.2fms ops=%d%n",
                        globalId, p.totalCost, p.timeMs, p.comparisons, k.totalCost, k.timeMs, k.comparisons);

                globalId++;
            }
        }

        JSONUtils.writeCombinedResults(Path.of("output_all.json"), graphIds, prims, kruskals, graphsForJson);
        writeCSVSummary(prims, kruskals, graphIds, Path.of("summary_all.csv"));
        System.out.println("Wrote output_all.json and summary_all.csv");
    }

    private static void writeCSVSummary(List<MSTResult> prims, List<MSTResult> kruskals, List<Integer> ids, Path out) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("graph_id,vertices,edges,prim_cost,prim_time_ms,prim_operations,kruskal_cost,kruskal_time_ms,kruskal_operations\n");
        for (int i = 0; i < prims.size(); i++) {
            MSTResult p = prims.get(i);
            MSTResult k = kruskals.get(i);
            sb.append(String.format(Locale.ROOT, "%d,%d,%d,%.2f,%.2f,%d,%.2f,%.2f,%d\n",
                    ids.get(i), p.vertices, p.edges, p.totalCost, p.timeMs, p.comparisons,
                    k.totalCost, k.timeMs, k.comparisons));
        }
        java.nio.file.Files.writeString(out, sb.toString());
    }
}
