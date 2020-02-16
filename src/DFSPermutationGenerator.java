import java.util.ArrayList;

public class DFSPermutationGenerator {
    private WeightGraph weightgraph;
    private boolean[] used;
    private int[] result;
    private float[] goods;
    private ArrayList<int[]> allResults;

    public DFSPermutationGenerator(WeightGraph weightGraph) {
        this.weightgraph = weightGraph;
        goods = weightGraph.getGoods();
        used = new boolean[weightGraph.getV()+1];
        result = new int[weightGraph.getV()-1];
        allResults = new ArrayList<int[]>();

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

    public float[] getGoods() {
        return goods;
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph();
        DFSPermutationGenerator dfsPermutationGenerator = new DFSPermutationGenerator(weightGraph);

    }

}