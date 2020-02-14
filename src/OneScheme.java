import java.util.ArrayList;
import java.util.Arrays;

/**
 * 每一种方案的数据结构
 */
public class OneScheme {

    private WeightGraph weightGraph;
    private int[] permutation;
    private float ct; //countTime: 总时长
    private float ate; //averageEfficiency: 总的时间利用率
    private float ucce; //useCarCostEfficiency: 车辆成本效率
    private int n; //配送次数
    private float[] goods; //每个分区的货物量
    private int[] area; //保存分区方式

    private int RD = 10; //RUNNING_SPEED: 行驶速度10(km)/1(h)
    private int MRL = 35; //MAX_ROAD_LENGTH: 单次最大行驶长度35(km)
    private int TM = 4; //IME_MONTHER: 单次行程时间效率的除数4(h)
    private float BCL= 5; //BIG_CAR_LOAD: 大车载重5(t)
    private float LCL= 2; //LITTLE_CAR_LOAD: 小车载重2(t)
    private float LCCE = (float) 0.2; //LITTLE_CAR_COST_EFFICIENCY: 小车成本率0.2
    private float BCCE = (float) 0.5; //BIG_CAR_COST_EFFICIENCY: 大车成本率0.5

    public OneScheme(int[] permutation, WeightGraph weightGraph) {
        this.permutation = permutation;
        this.weightGraph = weightGraph;
        goods = weightGraph.getGoods();
        area = new int[weightGraph.getV()-1];
        cutArea();
    }

    /**
     * 根据最大负载和最大行驶路径分区
     */
    private void cutArea(){
        int areaNum = 0;
        float roadCount = 0 ;
        float goodsCount = 0;
        for(int i=0;i<permutation.length;i++){
            if(goodsCount==0)
                roadCount = weightGraph.getWeight(0, permutation[i]);
            else
                roadCount = roadCount + weightGraph.getWeight(permutation[i], permutation[i-1]) + weightGraph.getWeight(0,permutation[i]);
            goodsCount = goodsCount + goods[permutation[i]];

            if (roadCount<35 && goodsCount<5) {
                if (roadCount != weightGraph.getWeight(0, permutation[i]))
                    roadCount = roadCount - weightGraph.getWeight(0, permutation[i]);
                area[permutation[i]-1] = areaNum;

            } else{
                System.out.println(String.format("roadCount: %f\ngoodsCount: %f\nareaNum: %d\n", roadCount, goodsCount, areaNum));
                i--;
                areaNum++;
                roadCount = 0;
                goodsCount = 0;
            }
        }
    }

    public float getCt() {
        return ct;
    }

    public float getAte() {
        return ate;
    }

    public float getUcce() {
        return ucce;
    }

    public int[] getArea() {
        return area;
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph();
        DFSPermutationGenerator dfsPermutationGenerator = new DFSPermutationGenerator(weightGraph);

        OneScheme oneScheme = new OneScheme(dfsPermutationGenerator.getAllResult().iterator().next(), weightGraph);
        System.out.println(Arrays.toString(dfsPermutationGenerator.getAllResult().iterator().next()));
        System.out.println(Arrays.toString(oneScheme.getArea()));
    }
}