import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 带权图结构类
 */

public class WeightGraph {
    private int V; //节点的个数
    private int E; //所有节点之间边的个数
    private TreeMap<Integer, Float>[] adj;
    private float[] goods;

    @SuppressWarnings("unchecked")
    public WeightGraph() {
        Scanner scanner1;
        Scanner scanner2;

        try {
            scanner1 = new Scanner(new File("data/graph.txt"));
            scanner2 = new Scanner(new File("data/weight.txt"));
            V = scanner1.nextInt();
            adj = new TreeMap[V];
            goods = new float[V];
            readGoods();

            for(int i=0;i<V;i++)
                adj[i] = new TreeMap<Integer, Float>();

            E = scanner1.nextInt();

            for(int i=0;i<E;i++) {
                int a = scanner1.nextInt();
                int b = scanner1.nextInt();
                validateVertex(a);
                validateVertex(b);
                float weight = scanner2.nextFloat();
                if(a==b) System.out.println("存在自环边");
                if(adj[a].containsKey(b)) System.out.println("存在平行边");

                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int getV() {
        return V;
    }

    public Iterable<Integer> getNeighbor(int i){
        return adj[i].keySet();
    }

    public int getE() {
        return E;
    }

    public float getWeight(int v, int w){
        if (hasEdge(v, w))
            return adj[v].get(w);
        else
            throw new IllegalArgumentException(String.format("Has No Edge:%d-%d", v, w));
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        if(adj[v].containsKey(w))
            return true;
        else
            return false;
    }

    private void validateVertex(int v){
        if(v<0 || v>=getV())
            throw new IllegalArgumentException(String.format("Has No Vertex %d",v));
    }

    private void readGoods(){
        try {
            Scanner scanner = new Scanner(new File("data/goods.txt"));

            for(int i=0;i<getV();i++)
                goods[i] = scanner.nextFloat();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public float[] getGoods(){
        return goods;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d %d\n", V, E));
        for(int i=0;i<V;i++) {
            sb.append(String.format("%d - %.2f(t): ", i, goods[i]));
            Iterator<Integer> iterator = adj[i].keySet().iterator();
            while(iterator.hasNext()){
                Integer key = iterator.next();
                sb.append(String.format("[->%d: %.2f(km)], ", key, adj[i].get(key)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
       WeightGraph weightGraph = new WeightGraph();
       System.out.println(weightGraph);
    }
}