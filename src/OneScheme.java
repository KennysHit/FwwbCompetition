import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 每一种方案的数据结构
 */
public class OneScheme {

    private WeightGraph weightGraph;
    private Floyed floyed;
    private int[] permutation;
    private float ct; //countTime: 总时长
    private float ate; //averageTimeEfficiency: 平均时间利用率
    private float coce; //costOfCarsEfficiency: 车辆利用率
    private float alf; //averageLoadFactor: 平均装载率
    private int dt; //DistributionTimes: 配送次数
    private float[] goods; //每个分区的货物量
    private int[] area; //保存分区方式
    private boolean[] visited;

    public OneScheme(int[] permutation, WeightGraph weightGraph) {
        this.permutation = permutation;
        this.weightGraph = weightGraph;
        floyed = new Floyed(weightGraph);
        ct = 0;
        ate = 0;
        coce = 0;
        dt = 1;
        alf = 0;
        goods = weightGraph.getGoods();
        area = new int[weightGraph.getV()-1];
        visited = new boolean[weightGraph.getV()-1];
        cutArea(0, 0, 0, 0, 0);
        reserveTwoDecimalFractions();
    }

    /**
     * 根据最大负载和最大行驶路径分区
     * km：单次行程记录的公里数
     * gs：单次行程记录的货物量
     * i：记录permutation数组下标
     * a：分区号
     * r：记录单次行程卸货花费的时长
     */
    private boolean cutArea(float km, float gs, int i, int a, float r){
        if (i==permutation.length){

            km = km + floyed.distanceTo(0, permutation[i-1]);
            ct = ct + (km / 10) + r;

            ate = (float) ((ate + ((km / 10) + r) / 3.5) / dt);
            if (gs < 2) {
                coce = coce + (float) 0.2;
                alf = (alf + gs / 2) / dt;
            } else {
                coce = coce + (float) 0.5;
                alf = (alf + gs / 5) / dt;
            }
            return true;
        }
        area[permutation[i]-1] = a;
        r = r + (float) (1 / 12);
        if (gs == 0)
            km = km + floyed.distanceTo(0, permutation[i]);
        else
            km = km + weightGraph.getWeight(permutation[i], permutation[i-1]);
        float rkm = km + floyed.distanceTo(0, permutation[i]);
        gs = gs + goods[permutation[i]];

        if (rkm<=35 && gs<=5)
            if (cutArea(km, gs, i+1, a, r))
                return true;
            else {
                dt++;
                ct = ct + rkm / 10 + r;
                ate = (float) (ate + (((rkm / 10) + r) / 3.5));
                if (gs < 2) {
                    coce = coce + (float) 0.2;
                    alf = alf + gs / 2;
                } else {
                    coce = coce + (float) 0.5;
                    alf = alf + gs / 5;
                }

                cutArea(0, 0, i+1, a+1, 0);
                return true;
            }
        else
            return false;

    }

    /**
     * reserve two decimal fractions：保留两位小数
     */
    public void reserveTwoDecimalFractions(){
        ct = new BigDecimal(ct).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
        ate = new BigDecimal(ate).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
        alf = new BigDecimal(alf).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }

    /**
     * 计算权值
     * 权重：
     * ct: 0.28470325
     * ate: 0.13310118
     * alf: 0.306273
     * cCoce: 0.27592254
     */
    public float getWeightValue(){
        return (float)( -getCt() * 0.28 + getAte() * 0.14 + getAlf() * 0.3 - getCoce() * 0.28 );
    }

    public float getCt() {
        return ct;
    }

    public float getAte() {
        return ate;
    }

    public float getCoce() {
        return coce;
    }

    public float getAlf() {
        return alf;
    }

    public int[] getPermutation() {
        return permutation;
    }

    public int[] getArea() {
        return area;
    }

    public int getDt() {
        return dt;
    }

    @Override
    public String toString() {
        return "OneScheme{" +
                "countTime=" + ct +
                "; averageTimeEfficiency=" + ate +
                "; averageLoadFactor=" + alf +
                "; useCarCostEfficiency=" + coce +
                "; DistributionTimest=" + dt +
                "; area=" + Arrays.toString(area) +
                '}';
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
        DFSPermutationGenerator dfsPermutationGenerator = new DFSPermutationGenerator(weightGraph);
    }
}
