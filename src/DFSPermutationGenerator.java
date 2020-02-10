import java.util.ArrayList;
import java.util.Arrays;

public class DFSPermutationGenerator {
    private Graph graph;
    private boolean[] used;
    private int[] result;
    private float[] goods;

    public DFSPermutationGenerator(Graph graph) {
        this.graph = graph;
        goods = graph.getGoods();
        used = new boolean[graph.getV()+1];
        result = new int[graph.getV()-1];
        find(0);
    }

    public void find(int level) {
        for (int i = 2; i <= graph.getV() ; i++) {
            if (!used[i]) {
                used[i] = true;
                result[level] = i-1;
                find(level + 1);
                used[i] = false;
            }
        }

        if (level == graph.getV() - 1) {
            if(graph.getNeighbor(result[0]).contains(result[1])
                    && graph.getNeighbor(result[1]).contains(result[2])
                    && graph.getNeighbor(result[2]).contains(result[3])
                    && graph.getNeighbor(result[3]).contains(result[4])){
                    System.out.println(Arrays.toString(result));
            }

        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph("graph.txt");
        DFSPermutationGenerator generator = new DFSPermutationGenerator(graph);
    }

}