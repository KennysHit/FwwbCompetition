import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GetResult {

    private WeightGraph weightGraph;
    private OneScheme maxScheme;
    private ArrayList<Integer>[] road;
    private ArrayList<Integer>[] point;

    public GetResult(WeightGraph weightGraph){

        long startTime = System.currentTimeMillis(); // 获取开始时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

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
        road = new ArrayList[maxScheme.getN()];
        for (int i = 0; i<maxScheme.getN(); i++) {
            road[i] = new ArrayList<Integer>();
        }
        findDistributionScheme();

        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("程序总运行时间： " + (endTime - startTime) + "ms");
    }

    public void findDistributionScheme(){
        ArrayList<Integer>[] arrayLists = new ArrayList[maxScheme.getN()];
        for (int i = 0; i<maxScheme.getN(); i++) {
            arrayLists[i] = new ArrayList<Integer>();
            for (int w : maxScheme.getPermutation()) {
                if (maxScheme.getArea()[w - 1] == i) {
                    arrayLists[i].add(w);
                }
            }
        }
        point = arrayLists;
        for (ArrayList<Integer> a: arrayLists){
            for (int w: a){
                if (a.indexOf(w) == 0) {
                    Dijkstra dijkstra = new Dijkstra(weightGraph, 0);
                    for (int v : dijkstra.findPath(w))
                        if(v != w)
                            road[maxScheme.getArea()[w - 1]].add(v);
                }
                road[maxScheme.getArea()[w-1]].add(w);
                if (a.indexOf(w) == a.size()-1){
                    Dijkstra dijkstra = new Dijkstra(weightGraph, w);
                    for (int v: dijkstra.findPath(0))
                        if (v != w)
                            road[maxScheme.getArea()[w-1]].add(v);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("最佳配送方案: \n"));
        for (int i = 0; i<maxScheme.getN(); i++){
            stringBuilder.append(String.format("第%d次配送 { ( 配送路径：", i+1));
            for (int w: road[i]) {
                stringBuilder.append(String.format("-> %d ", w));
            }
            stringBuilder.append(" ) , ( 配送点：" + point[i] + " ) } ");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("data/testGraph2.txt");
        GetResult getResult = new GetResult(weightGraph);
        System.out.println(getResult);
    }

}
