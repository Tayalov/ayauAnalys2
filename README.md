Minimum Spanning Tree Project (Prim’s and Kruskal’s Algorithms)

This project is about implementing and comparing Prim’s and Kruskal’s algorithms for finding the Minimum Spanning Tree (MST) of a weighted graph.
I wrote the program in Java, following object-oriented programming principles.
It can load graphs from JSON files, run both algorithms, and generate output JSON with the results.

Project Structure:
├─ pom.xml
├─ input_small.json
├─ input_medium.json
├─ input_large.json
├─ src/
│  ├─ main/
│  │  └─ java/
│  │     └─ org/
│  │        └─ example/
│  │           ├─ Edge.java
│  │           ├─ Graph.java
│  │           ├─ MSTResult.java
│  │           ├─ DisjointSet.java
│  │           ├─ PrimMST.java
│  │           ├─ KruskalMST.java
│  │           ├─ JSONUtils.java
│  │           └─ MainMulti.java
│  └─ test/
│     └─ java/
│        └─ org/
│           └─ example/
│              └─ MSTTest.java
└─ (outputs created at runtime:  summary_all.csv)

Step-by-Step Explanation
1)Graph and Edge Classes

I created my own data structure for representing graphs.

Graph.java stores all vertices and edges.

Edge.java represents a single connection between two vertices with a weight.
This was part of the bonus task for implementing a custom graph structure.

2)MSTResult.java

This class stores the output of each algorithm —
the list of MST edges, total cost, number of operations, and execution time.

3)PrimMST.java and KruskalMST.java

These classes implement Prim’s and Kruskal’s algorithms.
Both methods read the graph from the Graph object, calculate the MST, and return a MSTResult.

4)Main.java

This is the main file that runs the program.
It reads input graphs from a JSON file (like input.json), executes both algorithms for each graph, and saves the results into output.json.

I also added timing and operation counters to compare the performance between the two algorithms.

5)MSTTest.java

This class includes unit tests (using JUnit) to check:

Correctness of MST results

Handling of disconnected graphs

Comparison between Prim’s and Kruskal’s results

6)Input and Output Files

Input JSON — contains graphs of different sizes:

Small (4–6 vertices)

Medium (10–15 vertices)

Large (20–30+ vertices)

Output JSON — stores MST results for each algorithm, including cost, execution time, and operation count.

How to Run:

Open the project in IntelliJ IDEA.

Make sure the input file (input.json) is in the resources folder.

Run Main.java.

Check output.json to see the results.

Analysis and Conclusion

After running several datasets, I found that:

Both Prim’s and Kruskal’s algorithms give the same total MST cost.

Kruskal’s algorithm was slightly faster on dense graphs, while Prim’s worked better on sparse graphs.

My graph structure worked correctly and integrated well with both algorithms.

This project helped me understand graph algorithms better and practice OOP design, file handling, and testing in Java.



Summary

In this project, I implemented and compared Prim’s and Kruskal’s algorithms for finding the Minimum Spanning Tree (MST) using Java.
I created my own Graph and Edge classes to represent the data structure and followed object-oriented programming principles.

The program reads input graphs from a JSON file, runs both algorithms, and writes the results (including total cost, execution time, and operations count) into an output JSON.
I tested the program with small, medium, and large graphs to evaluate correctness and performance.

After analyzing the results, both algorithms produced the same MST cost, but Kruskal’s algorithm was slightly faster on dense graphs, while Prim’s algorithm worked better on sparse graphs.
Overall, this project improved my understanding of graph algorithms, data structures, and performance analysis in Java.
