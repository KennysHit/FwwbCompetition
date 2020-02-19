import java.util.ArrayList;
import java.util.Arrays;

public class DFSPermutationGenerator {
    private WeightGraph weightgraph;
    private boolean[] used;
    private int[] result;
    private ArrayList<int[]> allResults;

    public DFSPermutationGenerator(WeightGraph weightGraph) {
        this.weightgraph = weightGraph;
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
                if(!weightgraph.hasEdge(result[i], result[i+1]))
                    isAdd = false;


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
        WeightGraph weightGraph = new WeightGraph("data/graph");
        DFSPermutationGenerator dfsPermutationGenerator = new DFSPermutationGenerator(weightGraph);
        float minWeight = Float.MAX_VALUE;
        OneScheme minScheme = null;
        for (int[] w: dfsPermutationGenerator.allResults){
            OneScheme oneScheme = new OneScheme(w, weightGraph);
            if (oneScheme.getWeightValue() < minWeight){
                minWeight = oneScheme.getWeightValue();
                minScheme = oneScheme;
            }
        }
        System.out.println(Arrays.toString(minScheme.getPermutation()));
        System.out.println(Arrays.toString(minScheme.getArea()));
        System.out.println(minWeight);
    }

}