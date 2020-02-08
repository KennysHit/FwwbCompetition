import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

/**
 * 广度优先遍历的单一源路径查找
 * @author kennys
 */
public class SspBFS {
	private Graph graph;
	private int sourcePoint;
	private int target;
	
	private int[] pre;
	private boolean[] visited;
	
	public SspBFS(Graph graph, int sourcePoint, int target) {
		// TODO Auto-generated constructor stub
		this.graph = graph;
		this.sourcePoint = sourcePoint;
		this.target = target;
		visited = new boolean[graph.getV()];
		pre = new int[graph.getV()];
		for(int i=0;i<graph.getV();i++) {
			visited[i] = false;
		}
		for(int i=0;i<graph.getV();i++) {
			pre[i] = -1;
		}
		bfs(this.sourcePoint, this.target);
	}
	
	private void bfs(int source, int target) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		pre[source] = source;
		visited[source] = true;
		if(source==target) return;
		while(!queue.isEmpty()) {
			int out = queue.remove();
			if(out==target) return;
			for(int w: graph.consecutivePoint(out)) {
				if(!visited[w]) {
					queue.add(w);
					visited[w] = true;
					pre[w] = out;
				}
			}
		}
	}
	
	public Iterable<Integer> findPath() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int current = target;
		if(this.visited[current]==false) return null;
		while(current!=sourcePoint) {
			result.add(current);
			current = pre[current];
		}
		result.add(current);
		Collections.reverse(result);
		return result;
	}
	
	public String getVisited() {
		StringBuilder stringBuilder = new StringBuilder();
		for(boolean w : visited) {
			stringBuilder.append(String.format("%s ", w));
		}
		return stringBuilder.toString();
	}
	
	public String getPre() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int w: pre) {
			stringBuilder.append(String.format("%d ", w));
		}
		return stringBuilder.toString();
	}
	public static void main(String[] args) {
		Graph graph = new Graph("graph.txt");
		SspBFS sspBFS = new SspBFS(graph, 2, 0);
		System.out.println(sspBFS.getPre());
		System.out.println(sspBFS.getVisited());
		System.out.print("2->0: " + sspBFS.findPath());
		
	}
}
