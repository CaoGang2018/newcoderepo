import com.sun.nio.sctp.NotificationHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * @author admin_cg
 * @data 2020/9/18 18:26
 */
public class Graph {
    private final Map<String, Vertex> graph;

    public static class Edge{
        public final String v1, v2;
        public final int dist;

        public Edge(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    public static class Vertex implements Comparable<Vertex>{
        public final String name;
        public int dist = Integer.MAX_VALUE;
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();

        public Vertex(String name) {
            this.name = name;
        }
        private void printPath(){
            if(this == this.previous){
                System.out.printf("%s", this.name);
            }
            else if(this.previous == null){
                System.out.printf("%s(inreached)", this.name);
            }
            else{
                this.previous.printPath();
                System.out.printf(" -> %s(%d)", this.name, this.dist);
            }
        }

        @Override
        public int compareTo(Vertex o) {
            if(dist == o.dist)
                return name.compareTo(o.name);
            return Integer.compare(dist, o.dist);
        }

        @Override
        public String toString() {
            return "(" + name + ", " + dist + ")";
        }
    }

    public Graph(Edge[] edges) {
        this.graph = new HashMap<>(edges.length);

        for (Edge edge : edges) {
            if(!graph.containsKey(edge.v1))
                graph.put(edge.v1, new Vertex(edge.v1));
            if(!graph.containsKey(edge.v2))
                graph.put(edge.v2, new Vertex(edge.v2));
        }
        for (Edge edge : edges) {
            graph.get(edge.v1).neighbours.put(graph.get(edge.v2), edge.dist);
            //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }

    public void dijkstra(String startName){
        if(!graph.containsKey(startName)){
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        for(Vertex v : graph.values()){
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }
    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
            if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable

            //look at distances to each neighbour
            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); //the neighbour in this iteration

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) { // shorter path to neighbour found
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }
    /** Prints a path from the source to the specified vertex */
    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        graph.get(endName).printPath();
        System.out.println();
    }
    /** Prints the path from the source to every vertex (output order is not guaranteed) */
    public void printAllPaths() {
        for (Vertex v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }
}
