import java.util.ArrayList;
import java.util.Collections;

/**
 * 单一源路径查找
 */
public class sspDFS {
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
	public sspDFS(Graph graph, int sourcePoint, int target) {
		// TODO Auto-generated constructor stub
		this.graph = graph;
		this.sourcePoint = sourcePoint;
		this.target = target;
		this.visited = new boolean[graph.getV()];
		this.pre = new int[graph.getV()];
		
		for(int i=0;i<this.graph.getV();i++) {
			this.visited[i] = false;
		}
		for(int i=0;i<this.graph.getV();i++) {
			this.pre[i] = -1;
		}
		
		dfs(this.sourcePoint, this.sourcePoint, this.target);
	}
	
	/**
	 * 将路径上各点的前置节点存入pre列表中
	 * @param sourcePoint 源点
	 * @param parent 该点的前置节点
	 * @param target 目标节点
	 * @return ture：源点到目标节点存在一条路径 / false：源点到目标节点不存在一条路径
	 */
	private boolean dfs(int sourcePoint, int parent, int target) {
		this.visited[sourcePoint] = true;
		this.pre[sourcePoint] = parent;
		if(sourcePoint == target) {
			return true;
		}
		for(int w : this.graph.consecutivePoint(sourcePoint)) {
			if(!this.visited[w]) {
				if(dfs(w, sourcePoint, target)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isConnectTo(int target) {
		return this.visited[target];
	}
	
	/**
	 * 
	 * @return 源点到目标节点存在的一条路径
	 */
	public Iterable<Integer> findPath(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(!isConnectTo(this.target)) return null;
		
		int current = this.target;
		while(current!=this.sourcePoint) {
			result.add(current);
			current = pre[current];
		}
		result.add(this.sourcePoint);
		Collections.reverse(result);
		
		return result;
	}
	
	public int[] getPre() {
		return this.pre;
	}
	
	public boolean[] getVisited() {
		return this.visited;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph("graph.txt");
		sspDFS singleSourcePath = new sspDFS(graph, 1, 4);
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
