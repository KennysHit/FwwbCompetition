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
		this.visited = new boolean[this.graph.getV()];
		for(int i=0;i<this.graph.getV();i++) {
			this.visited[i] = false;
		}
		for(int i=0;i<this.graph.getV();i++) {
			if(!this.visited[i]) {
				this.bfs(i);
				this.result.add(-1); //-1为一个连通分量分界线
			}
		}
	}
	
	public void bfs(int sourcePoint) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(sourcePoint);
		this.visited[sourcePoint] = true;
		while(!queue.isEmpty()) {
			int out = queue.remove();
			this.result.add(out);
			for(int w : this.graph.consecutivePoint(sourcePoint)) {
				if(!this.visited[w]) {
					queue.add(w);
					this.visited[w] = true;
				}
			}
		}
	}
	
	public Iterable<Integer> getResult() {
		return this.result;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph("graph.txt");
		GraphBFS gbfs = new GraphBFS(graph);
		for(int w : gbfs.getResult()) {
			System.out.print(w + " ");
		}
	}
}
