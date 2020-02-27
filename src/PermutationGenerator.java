import java.util.ArrayList;
import java.util.Arrays;

public class PermutationGenerator {
    private WeightGraph weightgraph;
    private boolean[] used;
    private int[] result;
    private ArrayList<int[]> allResults;

    public PermutationGenerator(WeightGraph weightGraph) {
        this.weightgraph = weightGraph;
        used = new boolean[weightGraph.getV()+1];
        result = new int[weightGraph.getV()-1];
        allResults = new ArrayList<int[]>();

        find(0);

    }

    //深度优先遍历的方式找出所有点排列组合并筛选
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

    //返回所有排列组合的数据集
    public Iterable<int[]> getAllResult(){
        return allResults;
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
        PermutationGenerator permutationGenerator = new PermutationGenerator(weightGraph);
        for (int[] w: permutationGenerator.allResults)
            System.out.println(Arrays.toString(w));
    }

}