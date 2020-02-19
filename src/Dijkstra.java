import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {

    private WeightGraph weightGraph;
    private int source;
    private int[] distance;
    private boolean[] visited;
    private int[] pre;

    private class Node implements Comparable{

        public int v, dis;

        public Node(int v, int dis){
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Object o) {

            if(o instanceof Node)
                return dis - ((Node) o).dis;

            throw new IllegalArgumentException(String.format("Error Of Object Type!"));

        }

    }

    public Dijkstra(WeightGraph weightGraph, int source){
        this.weightGraph = weightGraph;
        weightGraph.validateVertex(source);
        this.source = source;
        distance = new int[weightGraph.getV()];
        pre = new int[weightGraph.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(pre, -1);
        distance[source] = 0;
        pre[source] = source;
        visited = new boolean[weightGraph.getV()];

        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        priorityQueue.add(new Node(source, distance[source]));
        while (!priorityQueue.isEmpty()){

            int cur = priorityQueue.remove().v;
            if(visited[cur]) continue;
            visited[cur] = true;

            for (int w: weightGraph.getNeighbor(cur))
                if (!visited[w])
                    if (distance[cur] + weightGraph.getWeight(cur, w) < distance[w]){
                        distance[w] = distance[cur] + weightGraph.getWeight(cur, w);
                        priorityQueue.add(new Node(w, distance[w]));
                        pre[w] = cur;
                    }
        }
    }

    public boolean isConnectedTo(int t){

        weightGraph.validateVertex(t);
        return visited[t];

    }

    public Iterable<Integer> findPath(int t){

        weightGraph.validateVertex(t);

        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        if (!isConnectedTo(t))
            throw new RuntimeException("source can not connected to " + t);

        while (t!=source){
            arrayList.add(t);
            t = pre[t];
        }
        arrayList.add(t);
        Collections.reverse(arrayList);

        return arrayList;
    }

    public int distanceTo(int t){
        weightGraph.validateVertex(t);
        if (!isConnectedTo(t))
            throw new RuntimeException("source can not connected to " + t);
        return distance[t];
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("graphData/weightGraph.txt");
        Dijkstra dijkstra = new Dijkstra(weightGraph, 0);
        System.out.println(dijkstra.distanceTo(6));
        System.out.println(dijkstra.findPath(6));
    }
}
