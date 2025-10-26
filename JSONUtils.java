package org.example;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class JSONUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    
    public static List<Graph> readGraphs(Path jsonFile) throws IOException {
        JsonNode root = mapper.readTree(jsonFile.toFile());
        List<Graph> result = new ArrayList<>();
        for (JsonNode gnode : root.get("graphs")) {
            List<String> nodes = new ArrayList<>();
            for (JsonNode n : gnode.get("nodes")) nodes.add(n.asText());
            Graph g = new Graph(nodes);
            for (JsonNode e : gnode.get("edges")) {
                String from = e.get("from").asText();
                String to = e.get("to").asText();
                double w = e.get("weight").asDouble();
                g.addEdge(from, to, w);
            }
            result.add(g);
        }
        return result;
    }

   
    public static void writeCombinedResults(Path outFile, List<Integer> graphIds, List<MSTResult> prims, List<MSTResult> kruskals, List<Graph> graphs) throws IOException {
        ObjectNode root = mapper.createObjectNode();
        ArrayNode results = root.putArray("results");
        for (int i = 0; i < prims.size(); i++) {
            MSTResult p = prims.get(i);
            MSTResult k = kruskals.get(i);
            Graph g = graphs.get(i);
            ObjectNode rn = results.addObject();
            rn.put("graph_id", graphIds.get(i));
            ObjectNode ist = rn.putObject("input_stats");
            ist.put("vertices", p.vertices);
            ist.put("edges", p.edges);

            ObjectNode pnode = rn.putObject("prim");
            ArrayNode pedges = pnode.putArray("mst_edges");
            for (Edge e : p.mstEdges) {
                ObjectNode en = pedges.addObject();
                en.put("from", g.labelOf(e.u));
                en.put("to", g.labelOf(e.v));
                en.put("weight", e.weight);
            }
            pnode.put("total_cost", p.totalCost);
            pnode.put("operations_count", p.comparisons);
            pnode.put("execution_time_ms", roundDouble(p.timeMs, 2));

            ObjectNode knode = rn.putObject("kruskal");
            ArrayNode kedges = knode.putArray("mst_edges");
            for (Edge e : k.mstEdges) {
                ObjectNode en = kedges.addObject();
                en.put("from", g.labelOf(e.u));
                en.put("to", g.labelOf(e.v));
                en.put("weight", e.weight);
            }
            knode.put("total_cost", k.totalCost);
            knode.put("operations_count", k.comparisons);
            knode.put("execution_time_ms", roundDouble(k.timeMs, 2));
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(outFile.toFile(), root);
    }

    private static double roundDouble(double v, int decimals) {
        double scale = Math.pow(10, decimals);
        return Math.round(v * scale) / scale;
    }
}
