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
    private float ucce; //useCarCostEfficiency: 车辆成本效率
    private float alf; //averageLoadFactor: 平均装载率
    private float[] goods; //每个分区的货物量
    private int dt; //DistributionTimes: 配送次数
    private int[] area; //保存分区方式
    private boolean[] visited;

    public OneScheme(int[] permutation, WeightGraph weightGraph) {
        this.permutation = permutation;
        this.weightGraph = weightGraph;
        floyed = new Floyed(weightGraph);
        ct = 0;
        ate = 0;
        ucce = 0;
        dt = 1;
        alf = 0;
        goods = weightGraph.getGoods();
        area = new int[weightGraph.getV()-1];
        visited = new boolean[weightGraph.getV()-1];
        cutArea(0, 0, 0, 0);
        reserveTwoDecimalFractions();
    }

    /**
     * 根据最大负载和最大行驶路径分区
     */
    private boolean cutArea(int km, float gs, int i, int a){

        if (i==permutation.length){
            km = km + weightGraph.getWeight(0, permutation[i-1]);
            ct = ct + ((float) km / 10);

            ate = (ate + ((float) km / 10) / 4) / dt;
            if (gs < 2) {
                ucce = ucce + (float) 0.2;
                alf = (alf + gs / 2) / dt;
            } else {
                ucce = ucce + (float) 0.5;
                alf = (alf + gs / 5) / dt;
            }
            System.out.println(km);
            System.out.println(gs);
            System.out.println(dt);
            return true;
        }
        area[i] = a;
        if (gs == 0)
            //System.out.println(km + floyed.distanceTo(0, permutation[i]));
            km = km + floyed.distanceTo(0, permutation[i]);
        else
            km = km + weightGraph.getWeight(permutation[i], permutation[i-1]);
        float rkm = km + floyed.distanceTo(0, permutation[i]);
        gs = gs + goods[permutation[i]];

        if (rkm<=35 && gs<=5)
            if (cutArea(km, gs, i+1, a))
                return true;
            else {
                System.out.println(rkm);
                System.out.println(gs);
                System.out.println(dt);
                System.out.println();
                dt++;
                ct = ct + rkm / 10;
                ate = ate + ((rkm / 10) / 4);
                if (gs < 2) {
                    ucce = ucce + (float) 0.2;
                    alf = alf + gs / 2;
                } else {
                    ucce = ucce + (float) 0.5;
                    alf = alf + gs / 5;
                }

                cutArea(0, 0, i+1, a+1);
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
     * ct: 0.442220
     * ucce: 0.422549
     * ate: 0.135231
     */
    private float getWeightValue(){
        return (float)( getCt() * 0.442220 + getUcce() * 0.422549 + getAte() * 0.135231 );
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

    @Override
    public String toString() {
        return "OneScheme{" +
                "countTime=" + ct +
                "; averageTimeEfficiency=" + ate +
                "; averageLoadFactor=" + alf +
                "; useCarCostEfficiency=" + ucce +
                "; DistributionTimest=" + dt +
                "; area=" + Arrays.toString(area) +
                '}';
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
        DFSPermutationGenerator dfsPermutationGenerator = new DFSPermutationGenerator(weightGraph);

//        for(int[] w: dfsPermutationGenerator.getAllResult()){
//            OneScheme oneScheme = new OneScheme(w, weightGraph);
//            System.out.println(Arrays.toString(w));
//            System.out.println(oneScheme);
//        }
        int[] w = dfsPermutationGenerator.getAllResult().iterator().next();
        OneScheme oneScheme = new OneScheme(w, weightGraph);
        System.out.println(Arrays.toString(w));
        System.out.println(oneScheme);

    }
}
