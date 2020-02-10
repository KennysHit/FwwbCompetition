import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * 图结构类
 */

public class Graph {
	private int V; //节点的个数
	private int E; //所有节点之间边的个数
	private LinkedList<Integer>[] adj;
	private float[] goods;
	
	@SuppressWarnings("unchecked")
	public Graph(String fileName) {
		Scanner scanner;
		
		try {
			scanner = new Scanner(new File(fileName));
			
			V = scanner.nextInt();
			adj = new LinkedList[V];
			goods = new float[V];
			for(int i=0;i<V;i++) {
				adj[i] = new LinkedList<Integer>();
			}
			E = scanner.nextInt();
			
			for(int i=0;i<E;i++) {
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

	public LinkedList<Integer> getNeighbor(int i){
		return adj[i];
	}

	public int getE() {
		return E;
	}

	private void readGoods(){
		try {
			Scanner scanner = new Scanner(new File("goods.txt"));
			for(int i=0;i<getV();i++){
				goods[i] = scanner.nextFloat();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public float[] getGoods(){
		readGoods();
		return goods;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%d %d\n", V, E));
		for(int i=0;i<V;i++) {
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
