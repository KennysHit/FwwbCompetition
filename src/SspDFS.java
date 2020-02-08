import java.util.ArrayList;
import java.util.Collections;

/**
 * 深度优先遍历的单一源路径查找
 */
public class SspDFS {
	private Graph graph;
	private int sourcePoint; //源点
	private int target; //终点
	
	private int[] pre;
	private boolean[] visited;
	
	/**
	 * 构造函数
	 * @param graph 图结构类
	 * @param sourcePoint 源点
	 * @param target 目标点
	 */
	public SspDFS(Graph graph, int sourcePoint, int target) {
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
		
		dfs(sourcePoint, sourcePoint, target);
	}
	
	/**
	 * 将路径上各点的前置节点存入pre列表中
	 * @param source 源点
	 * @param parent 该点的前置节点
	 * @param target 目标节点
	 * @return ture：源点到目标节点存在一条路径 / false：源点到目标节点不存在一条路径
	 */
	private boolean dfs(int source, int parent, int target) {
		visited[source] = true;
		pre[source] = parent;
		if(source == target) {
			return true;
		}
		for(int w: graph.consecutivePoint(source)) {
			if(!visited[w]) {
				if(dfs(w, source, target)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isConnectTo(int target) {
		return visited[target];
	}
	
	/**
	 * 
	 * @return 源点到目标节点存在的一条路径
	 */
	public Iterable<Integer> findPath(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(!isConnectTo(target)) return null;
		
		int current = target;
		while(current!=sourcePoint) {
			result.add(current);
			current = pre[current];
		}
		result.add(sourcePoint);
		Collections.reverse(result);
		
		return result;
	}
	
	public int[] getPre() {
		return pre;
	}
	
	public boolean[] getVisited() {
		return visited;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph("graph.txt");
		SspDFS singleSourcePath = new SspDFS(graph, 1, 4);
		System.out.println("1->4:" + singleSourcePath.findPath());
		for(int w : singleSourcePath.getPre()) {
			System.out.print(String.valueOf(w) + " ");
		}
		System.out.println();
		for(boolean w : singleSourcePath.getVisited()) {
			System.out.print(String.valueOf(w) + " ");
		}
	}
}
