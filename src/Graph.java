/**
 * 图结构类
 */

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
	private int V; //节点的个数
	private int E; //所有节点之间边的个数
	private LinkedList<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public Graph(String fileName) {
		Scanner scanner;
		
		try {
			scanner = new Scanner(new File(fileName));
			
			this.V = scanner.nextInt();
			this.adj = new LinkedList[this.V];
			for(int i=0;i<this.V;i++) {
				adj[i] = new LinkedList<Integer>();
			}
			this.E = scanner.nextInt();
			
			for(int i=0;i<this.E;i++) {
				int a = scanner.nextInt();
				int b = scanner.nextInt();
				
				if(a==b) System.out.println("存在自环边");
				if(adj[a].contains(b)) System.out.println("存在平行边");
				
				adj[a].add(b);
				adj[b].add(a);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getV() {
		return V;
	}
	/**
	 * 得到某一点的相邻节点
	 * @param 待查寻的某一点
	 * @return 该点的相邻节点
	 */
	public Iterable<Integer> consecutivePoint(int i){
		return adj[i];
	}
	public int getE() {
		return E;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%d %d\n", this.V, this.E));
		for(int i=0;i<this.V;i++) {
			sb.append(String.format("%d : ",i));
			for(int w : adj[i]) {
				sb.append(String.format("%d ", w));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Graph adjList = new Graph("graph.txt");
		System.out.println(adjList);
	}
}
