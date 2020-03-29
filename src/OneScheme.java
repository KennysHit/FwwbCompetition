import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 每一种方案的数据结构
 */
public class OneScheme {

    private WeightGraph weightGraph;
    private Floyed floyed;
    private int[] permutation;
    private float T; // 总时长
    private float O; // 平均时效
    private float U; // 车辆成本指标
    private float K; // 平均装载率
    private float L; //平均配送订单量
    private int n; // 配送次数
    private float[] goods; //每个分区的货物量
    private int[] area; //保存分区方式 假设数组下标从1开始为各个顶点。数组各元素值为分区号，分区号相同的为同一分区
    private boolean[] visited;

    public OneScheme(int[] permutation, WeightGraph weightGraph) {
        this.permutation = permutation;
        this.weightGraph = weightGraph;
        floyed = new Floyed(weightGraph);
        T = 0;
        O = 0;
        U = 0;
        n = 1;
        K = 0;
        goods = weightGraph.getGoods();
        area = new int[weightGraph.getV()-1];
        visited = new boolean[weightGraph.getV()-1];
        cutArea(0, 0, 0, 0, 0, 0);
        reserveTwoDecimalFractions();
    }

    /**
     * 根据最大负载和最大行驶路径分区
     * km：单次行程记录的公里数
     * g：单次行程记录的货物量
     * i：记录permutation数组下标
     * j：分区号
     * t：记录单次行程卸货花费的时长
     * p:记录单次行程配送订单量
     */
    private boolean cutArea(float km, float g, int i, int j, float t, float p){
        if (i==permutation.length){

            km = km + floyed.distanceTo(0, permutation[i-1]);
            T = T + (km / 10) + t;
            L = L + p;
            O = (float) ((O + ((km / 10) + t) / 4) / n);
            L = L / n;
            if (g < 2) {
                U = U + (float) 0.2;
                K = (K + g / 2) / n;
            } else {
                U = U + (float) 0.5;
                K = (K + g / 5) / n;
            }
            return true;
        }
        area[permutation[i]-1] = j;
        t = t + (float) (1 / 12);
        p = p + 1;
        if (g == 0)
            km = km + floyed.distanceTo(0, permutation[i]);
        else
            km = km + weightGraph.getWeight(permutation[i], permutation[i-1]);
        float rkm = km + floyed.distanceTo(0, permutation[i]);
        g = g + goods[permutation[i]];

        if (rkm<=35 && g<=5)
            if (cutArea(km, g, i+1, j, t, p))
                return true;
            else {
                n++;
                T = T + rkm / 10 + t;
                L = L + p;
                O = (float) (O + (((rkm / 10) + t) / 4));
                if (g < 2) {
                    U = U + (float) 0.2;
                    K = K + g / 2;
                } else {
                    U = U + (float) 0.5;
                    K = K + g / 5;
                }

                cutArea(0, 0, i+1, j+1, 0, 0);
                return true;
            }
        else
            return false;

    }

    /**
     * reserve two decimal fractions：保留两位小数
     */
    public void reserveTwoDecimalFractions(){
        T = new BigDecimal(T).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
        O = new BigDecimal(O).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
        K = new BigDecimal(K).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }

    /**
     * 计算权值
     * 权重：
     * K: 0.089
     * L: 0.160
     * O: 0.408
     * T: 0.178
     * U: 0.165
     */
    public float getWeightValue(){
        return (float)( getK()*0.089 + getL()*0.160 + getO()*0.408 - getT()*0.178 - getU()*0.165);
    }

    public float getT() {
        return T;
    }

    public float getO() {
        return O;
    }

    public float getU() {
        return U;
    }

    public float getK() {
        return K;
    }

    public int[] getPermutation() {
        return permutation;
    }

    public int[] getArea() {
        return area;
    }

    public int getN() {
        return n;
    }

    public float getL() {
        return L;
    }

    @Override
    public String toString() {
        return "OneScheme{" +
                "K=" + K +
                "; L=" + L +
                "; O=" + O +
                "; T=" + T +
                "; U=" + U +
                "; all= " + (K + L + O + T + U) +
                '}';
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
        PermutationGenerator permutationGenerator = new PermutationGenerator(weightGraph);
        ArrayList<int[]> arrayList = (ArrayList<int[]>) permutationGenerator.getAllResult();
        for (int[] w: arrayList){
            OneScheme oneScheme = new OneScheme(w, weightGraph);
            System.out.println(Arrays.toString(oneScheme.getArea()) + "～" + oneScheme.getWeightValue());
        }
        System.out.println("count: " + arrayList.size());
    }
}