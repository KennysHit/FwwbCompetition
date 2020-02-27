import java.util.ArrayList;

public class GetResult {

    private WeightGraph weightGraph;
    private OneScheme maxScheme;
    private ArrayList<Integer>[] result;

    public GetResult(WeightGraph weightGraph){
        this.weightGraph = weightGraph;
        PermutationGenerator permutationGenerator = new PermutationGenerator(weightGraph);
        float minWeight = -999;
        for (int[] w: permutationGenerator.getAllResult()){
            OneScheme oneScheme = new OneScheme(w, weightGraph);
            if (oneScheme.getWeightValue() > minWeight){
                minWeight = oneScheme.getWeightValue();
                maxScheme = oneScheme;
            }
        }
        result = new ArrayList[maxScheme.getN()];
        for (int i = 0; i<maxScheme.getN(); i++)
            result[i] = new ArrayList<Integer>();
        findDistributionScheme();
    }

    public void findDistributionScheme(){
        int j = 0;
        ArrayList<Integer>[] arrayLists = new ArrayList[maxScheme.getN()];
        for (int i = 0; i<maxScheme.getN(); i++) {
            arrayLists[i] = new ArrayList<Integer>();
            for (int w : maxScheme.getPermutation())
                if (maxScheme.getArea()[w - 1] == i)
                    arrayLists[i].add(w);
        }
        for (ArrayList<Integer> a: arrayLists){
            for (int w: a){
                if (a.indexOf(w) == 0) {
                    Dijkstra dijkstra = new Dijkstra(weightGraph, 0);
                    for (int v : dijkstra.findPath(w))
                        if(v != w)
                            result[maxScheme.getArea()[w - 1]].add(v);
                }
                result[maxScheme.getArea()[w-1]].add(w);
                if (a.indexOf(w) == a.size()-1){
                    Dijkstra dijkstra = new Dijkstra(weightGraph, w);
                    for (int v: dijkstra.findPath(0))
                        if (v != w)
                            result[maxScheme.getArea()[w-1]].add(v);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("最佳配送方案: \n"));
        for (int i = 0; i<maxScheme.getN(); i++){
            stringBuilder.append(String.format("第%d次配送: ", i));
            for (int w: result[i]) {
                stringBuilder.append(String.format("%d ", w));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
        GetResult getResult = new GetResult(weightGraph);
        System.out.println(getResult);
    }

}
