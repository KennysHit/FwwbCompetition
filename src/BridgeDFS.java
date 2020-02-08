import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BridgeDFS {

    private Graph graph;
    private HashSet<String> bridge =  new HashSet<String>();
    private int record = 0;
    private Boolean[] visited;
    private int[] order;
    private int[] low;

    public BridgeDFS(Graph graph){
        this.graph = graph;
        visited = new Boolean[graph.getV()];
        for(int i=0;i<graph.getV();i++){
            visited[i] = false;
        }
        order = new int[this.graph.getV()];
        low = new int[this.graph.getV()];
        order[0] = -1;
        for(int i=0;i<graph.getV();i++){
            if(!visited[i]){
                dfs(0,0);
            }
        }

    }

    private void dfs(int source, int parent){
        visited[source] = true;
        order[source] = record;
        low[source] = record;
        record++;
        for (int w: graph.consecutivePoint(source)){
            if(!visited[w]){
                dfs(w,source);
                low[source] = Math.min(low[source], low[w]);
                if(order[source]<low[w]){
                    bridge.add(String.valueOf(source) + "-" + String.valueOf(w));
                }
            }else if(visited[w] && w != parent){
                low[source] = Math.min(low[source], low[w]);
            }
        }
    }
    public HashSet<String> getBridge(){
        return bridge;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("graphTest.txt");
        BridgeDFS bridgeDFS = new BridgeDFS(graph);
        System.out.println(bridgeDFS.getBridge());
    }
}
