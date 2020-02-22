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

    /**
     * 深度优先遍历的方式找出所有点排列组合并筛选
     * @param level
     */
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
        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
        DFSPermutationGenerator dfsPermutationGenerator = new DFSPermutationGenerator(weightGraph);
        for (int[] w: dfsPermutationGenerator.allResults)
            System.out.println(Arrays.toString(w));
    }

}