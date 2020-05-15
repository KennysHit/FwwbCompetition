//import java.util.ArrayList;
//
///**
// * 熵值法
// */
//public class Entropy {
//
//    private ArrayList<float[]> data;
//    private float T;
//    private float O;
//    private float K;
//    private float U;
//    private float L;
//    private float k;
//
//    public Entropy(){
//        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
//        PermutationGenerator permutationGenerator = new PermutationGenerator(weightGraph);
//        data = new ArrayList<float[]>();
//        T = 0;
//        O = 0;
//        K = 0;
//        U = 0;
//        L = 0;
//        for (int[] w: permutationGenerator.getResult()){
//            float[] result = new float[5];
//            OneScheme oneScheme = new OneScheme(w, weightGraph);
//            result[0] = oneScheme.getK();
//            result[1] = oneScheme.getL();
//            result[2] = oneScheme.getO();
//            result[3] = oneScheme.getT();
//            result[4] = oneScheme.getU();
//            data.add(result);
//            K = K + result[0];
//            L = L + result[1];
//            O = O + result[2];
//            T = T + result[3];
//            U = U + result[4];
//            System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3] + " " + result[4] );
//        }
//        System.out.println(K + " " + L + " " + O + " " + T + " " + U);
//        System.out.println();
//        k = 1 / (float) Math.log(data.size());
//        first();
//        second();
//        third();
//    }
//
//    private void first(){
//        for (float[] w: data){
//            w[0] = w[0] / K;
//            w[1] = w[1] / L;
//            w[2] = w[2] / O;
//            w[3] = w[3] / T;
//            w[4] = w[4] / U;
//            System.out.println(w[0] + " " + w[1] + " " + w[2] + " " + w[3] + " " + w[4]);
//        }
//        System.out.println();
//    }
//
//    private void second(){
//        for (float[] w: data){
//            w[0] = -(float) Math.log(w[0]) * w[0];
//            w[1] = -(float) Math.log(w[1]) * w[1];
//            w[2] = -(float) Math.log(w[2]) * w[2];
//            w[3] = -(float) Math.log(w[3]) * w[3];
//            w[4] = -(float) Math.log(w[4]) * w[4];
//            System.out.println(w[0] + " " + w[1] + " " + w[2] + " " + w[3] + " " + w[4]);
//        }
//        System.out.println();
//    }
//
//    private void third(){
//        float w1 = 0, w2 = 0, w3 = 0, w4 = 0, w5 = 0;
//        for (float[] w: data){
//            w1 = w1 + w[0];
//            w2 = w2 + w[1];
//            w3 = w3 + w[2];
//            w4 = w4 + w[3];
//            w5 = w5 + w[4];
//        }
//        System.out.println(w1 + " " + w2 + " " + w3 + " " + w4 + " " + w5 );
//        System.out.println("k: " + k);
//        w1 = k * w1;
//        w2 = k * w2;
//        w3 = k * w3;
//        w4 = k * w4;
//        w5 = k * w5;
//
//        System.out.println("熵值：" + w1 + " " + w2 + " " + w3 + " " + w4 + " " + w5 );
//
//        w1 = 1 - w1;
//        w2 = 1 - w2;
//        w3 = 1 - w3;
//        w4 = 1 - w4;
//        w5 = 1 - w5;
//
//        float all = w1 + w2 + w3 + w4 + w5;
//        T = w1 / all;
//        O = w2 / all;
//        K = w3 / all;
//        U = w4 / all;
//        L = w5 / all;
//    }
//
//    @Override
//    public String toString() {
//        return "Entropy{" +
//                "K=" + K +
//                ", L=" + L +
//                ", O=" + O +
//                ", T=" + T +
//                ", U=" + U +
//                ", sum=" + (T + O + K + U + L) +
//                '}';
//    }
//
//    public static void main(String[] args) {
//        Entropy entropy = new Entropy();
//        System.out.println(entropy);
//    }
//}
