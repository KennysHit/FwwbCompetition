import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

/**
 * 广度优先遍历
 * @author kennys
 */
public class sspBFS {
	private Graph graph;
	private int sourcePoint;
	private int target;
	
	private int[] pre;
	private boolean[] visited;
	
	public sspBFS(Graph graph, int sourcePoint, int target) {
		// TODO Auto-generated constructor stub
		this.graph = graph;
		this.sourcePoint = sourcePoint;
		this.target = target;
		this.visited = new boolean[this.graph.getV()];
		this.pre = new int[this.graph.getV()];
		for(int i=0;i<this.graph.getV();i++) {
			this.visited[i] = false;
		}
		for(int i=0;i<this.graph.getV();i++) {
			this.pre[i] = -1;
		}
		this.bfs(this.sourcePoint, this.target);
	}
	
	public void bfs(int sourcePoint, int target) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(sourcePoint);
		this.pre[sourcePoint] = sourcePoint;
		this.visited[sourcePoint] = true;
		if(sourcePoint==target) return;
		while(!queue.isEmpty()) {
			int out = queue.remove();
			if(out==target) return;
			for(int w : this.graph.consecutivePoint(out)) {
				if(!this.visited[w]) {
					queue.add(w);
					this.visited[w] = true;
					this.pre[w] = out;
				}
			}
		}
	}
	
	public Iterable<Integer> findPath() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int current = this.target;
		if(this.visited[current]==false) return null;
		while(current!=this.sourcePoint) {
			result.add(current);
			current = this.pre[current];
		}
		result.add(current);
		Collections.reverse(result);
		return result;
	}
	
	public String getVisited() {
		StringBuilder stringBuilder = new StringBuilder();
		for(boolean w : this.visited) {
			stringBuilder.append(String.format("%s ", w));
		}
		return stringBuilder.toString();
	}
	
	public String getPre() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int w : this.pre) {
			stringBuilder.append(String.format("%d ", w));
		}
		return stringBuilder.toString();
	}
	public static void main(String[] args) {
		Graph graph = new Graph("graph.txt");
		sspBFS sspBFS = new sspBFS(graph, 2, 0);
		System.out.println(sspBFS.getPre());
		System.out.println(sspBFS.getVisited());
		System.out.print(sspBFS.findPath());
		
	}
}
