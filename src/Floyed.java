import java.util.Arrays;

public class Floyed {

    private WeightGraph weightGraph;
    private int[][] dis;

    public Floyed(WeightGraph weightGraph){

        this.weightGraph = weightGraph;
        dis = new int[weightGraph.getV()][weightGraph.getV()];

        for (int i=0;i<weightGraph.getV();i++)
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        for (int v=0;v<weightGraph.getV();v++){
            dis[v][v] = 0;
            for (int w: weightGraph.getNeighbor(v))
                dis[v][w] = weightGraph.getWeight(v, w);
        }

        for (int t=0;t<weightGraph.getV();t++)
            for (int v=0;v<weightGraph.getV();v++)
                for (int w=0;w<weightGraph.getV();w++)
                    if (dis[v][t]!=Integer.MAX_VALUE && dis[t][w]!=Integer.MAX_VALUE && dis[v][t]+dis[t][w]<dis[v][w])
                        dis[v][w] = dis[v][t] + dis[t][w];

    }

    public boolean isConnected(int v, int w){

        weightGraph.validateVertex(v);
        weightGraph.validateVertex(w);

        return dis[v][w] != Integer.MAX_VALUE;

    }

    public boolean hasNegativeCycle(){

        for (int v=0;v<weightGraph.getV();v++)
            if (dis[v][v] < 0)
                return true;

        return false;

    }

    public int distanceTo(int v, int w){

        weightGraph.validateVertex(v);
        weightGraph.validateVertex(w);

        if (hasNegativeCycle())
            throw new RuntimeException("exists negative cycle!");
        if (!isConnected(v, w))
            throw new RuntimeException(v + " and " + w + " is not connected!");

        return dis[v][w];

    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
        Floyed floyed = new Floyed(weightGraph);
        for (int i=1;i<weightGraph.getV();i++){
            System.out.println(floyed.distanceTo(0, i));
        }
    }

}
