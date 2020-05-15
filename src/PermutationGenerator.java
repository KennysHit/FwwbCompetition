import java.util.ArrayList;
import java.util.Arrays;

public class PermutationGenerator {
    private WeightGraph weightgraph;
    private boolean[] used;
    private int[] result;
    private float min;
    private OneScheme oneScheme;
    private OneScheme maxScheme;
    private int mark = 0;

    public PermutationGenerator(WeightGraph weightGraph) {
        this.weightgraph = weightGraph;
        used = new boolean[weightGraph.getV()+1];
        result = new int[weightGraph.getV()-1];
        min = -999;
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
        System.out.println(Arrays.toString(result));
        if (level == weightgraph.getV() - 1) {

            boolean isAdd = true;

            for(int i=0;i<weightgraph.getV()-2;i++)
                if(!weightgraph.hasEdge(result[i], result[i+1]))
                    isAdd = false;


            if(isAdd){
                oneScheme = new OneScheme(result, weightgraph);
                if (min<oneScheme.getWeightValue()){
                    min = oneScheme.getWeightValue();
                    maxScheme = oneScheme;
                }
            }

        }
    }

    //返回所有排列组合的数据集
    public OneScheme getResult(){
        return maxScheme;
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
        PermutationGenerator permutationGenerator = new PermutationGenerator(weightGraph);
        System.out.println(Arrays.toString(permutationGenerator.getResult().getArea()));
    }
}