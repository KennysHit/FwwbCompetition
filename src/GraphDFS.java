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
		this.graph = graph;
		this.visited = new boolean[this.graph.getV()];
		for(int i=0;i<this.graph.getV();i++) {
			this.visited[i] = false;
		}
		
		for(int i=0;i<this.graph.getV();i++) {
			if(!this.visited[i]) {
				this.dfs(i);
				this.result.add(-1); //-1为一个连通分量分界线
			}
		}
	}
	
	public void dfs(int sourcePoint) {
		this.visited[sourcePoint] = true;
		this.result.add(sourcePoint);
		for(int w : this.graph.consecutivePoint(sourcePoint)) {
			if(!this.visited[w]) {
				dfs(w);
			}
		}
	}
	
	public Iterable<Integer> getResult() {
		return this.result;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph("graph.txt");
		GraphDFS gDfs = new GraphDFS(graph);
		for(int w : gDfs.getResult()) {
			System.out.print(w + " ");
		}
	}
}
