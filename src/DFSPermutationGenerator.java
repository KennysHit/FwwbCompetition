import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class DFSPermutationGenerator {
    private WeightGraph weightgraph;
    private boolean[] used;
    private int[] result;
    private float[] goods;
    private LinkedList<int[]> allResults;

    public DFSPermutationGenerator(WeightGraph weightGraph) {
        this.weightgraph = weightGraph;
        goods = weightGraph.getGoods();
        used = new boolean[weightGraph.getV()+1];
        result = new int[weightGraph.getV()-1];
        allResults = new LinkedList<int[]>();

        find(0);
    }

    private void find(int level) {
        for (int i = 2; i <= weightgraph.getV() ; i++)
            if (!used[i]) {
                used[i] = true;
                result[level] = i-1;
                find(level + 1);
                used[i] = false;
            }

        if (level == weightgraph.getV() - 1) {
            boolean isAdd = true;

            for(int i=0;i<weightgraph.getV()-2;i++)
                if(!weightgraph.hasEdge(result[i], result[i+1])) isAdd = false;

            if(isAdd){
                int[] a = (int[])result.clone();
                allResults.add(a);
            }

        }
    }

    public Iterable<int[]> getAllResult(){
        return allResults;
    }

    public static void main(String[] args) {
        WeightGraph graph = new WeightGraph();
        DFSPermutationGenerator generator = new DFSPermutationGenerator(graph);

        for(int[] w: generator.getAllResult())
            System.out.println(Arrays.toString(w));
    }

}