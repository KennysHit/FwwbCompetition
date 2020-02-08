import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 广度优先遍历
 * @author kennys
 *
 */
public class GraphBFS {
	private Graph graph;
	
	private boolean[] visited;
	private ArrayList<Integer> result = new ArrayList<Integer>();
	
	public GraphBFS(Graph graph) {
		// TODO Auto-generated constructor stub
		this.graph = graph;
		visited = new boolean[graph.getV()];
		for(int i=0;i<graph.getV();i++) {
			visited[i] = false;
		}
		for(int i=0;i<graph.getV();i++) {
			if(!visited[i]) {
				bfs(i);
				result.add(-1); //-1为一个连通分量分界线
			}
		}
	}
	
	private void bfs(int source) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		visited[source] = true;
		while(!queue.isEmpty()) {
			int out = queue.remove();
			this.result.add(out);
			for(int w : graph.consecutivePoint(out)) {
				if(!visited[w]) {
					queue.add(w);
					visited[w] = true;
				}
			}
		}
	}
	
	public Iterable<Integer> getResult() {
		return result;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph("graph.txt");
		GraphBFS gbfs = new GraphBFS(graph);
		for(int w : gbfs.getResult()) {
			System.out.print(w + " ");
		}
	}
}
