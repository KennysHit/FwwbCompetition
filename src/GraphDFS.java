import java.util.ArrayList;
/**
 * 深度优先遍历
 * @author kennys
 *
 */
public class GraphDFS {
	private Graph graph;
	
	private boolean[] visited;
	private ArrayList<Integer> result = new ArrayList<Integer>();
	
	public GraphDFS(Graph graph) {
		// TODO Auto-generated constructor stub
		graph = graph;
		visited = new boolean[graph.getV()];
		for(int i=0;i<graph.getV();i++) {
			visited[i] = false;
		}
		
		for(int i=0;i<graph.getV();i++) {
			if(!visited[i]) {
				dfs(i);
				result.add(-1); //-1为一个连通分量分界线
			}
		}
	}
	
	private void dfs(int source) {
		visited[source] = true;
		result.add(source);
		for(int w: graph.getNeighbor(source)) {
			if(!visited[w]) {
				dfs(w);
			}
		}
	}
	
	public Iterable<Integer> getResult() {
		return result;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph("graph.txt");
		GraphDFS gDfs = new GraphDFS(graph);
		for(int w : gDfs.getResult()) {
			System.out.print(w + " ");
		}
	}
}
