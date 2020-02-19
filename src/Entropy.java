import java.util.ArrayList;

/**
 * 熵值法
 */
public class Entropy {

    private ArrayList<float[]> data;
    private float cCt;
    private float cAte;
    private float cAlf;
    private float cCoce;
    private float k;

    public Entropy(){
        WeightGraph weightGraph = new WeightGraph("data/graph.txt");
        DFSPermutationGenerator dfsPermutationGenerator = new DFSPermutationGenerator(weightGraph);
        data = new ArrayList<float[]>();
        cCt = 0;
        cAte = 0;
        cAlf = 0;
        cCoce = 0;
        for (int[] w:dfsPermutationGenerator.getAllResult()){
            float[] result = new float[4];
            OneScheme oneScheme = new OneScheme(w, weightGraph);
            result[0] = oneScheme.getCt();
            result[1] = oneScheme.getAte();
            result[2] = oneScheme.getAlf();
            result[3] = oneScheme.getCoce();
            data.add(result);
            cCt = cCt + result[0];
            cAte = cAte + result[1];
            cAlf = cAlf + result[2];
            cCoce = cCoce + result[3];
            System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
        }
        System.out.println(cCt+ " " + cAte + " " + cAlf + " " + cCoce);
        System.out.println();
        k = 1 / (float) Math.log(data.size());
        first();
        second();
        third();
    }

    private void first(){
        for (float[] w: data){
            w[0] = w[0] / cCt;
            w[1] = w[1] / cAte;
            w[2] = w[2] / cAlf;
            w[3] = w[3] / cCoce;
            System.out.println(w[0] + " " + w[1] + " " + w[2] + " " + w[3]);
        }
        System.out.println();
    }

    private void second(){
        for (float[] w: data){
            w[0] = -(float) Math.log(w[0]) * w[0];
            w[1] = -(float) Math.log(w[1]) * w[1];
            w[2] = -(float) Math.log(w[2]) * w[2];
            w[3] = -(float) Math.log(w[3]) * w[3];
            System.out.println(w[0] + " " + w[1] + " " + w[2] + " " + w[3]);
        }
        System.out.println();
    }

    private void third(){
        float w1 = 0, w2 = 0, w3 = 0, w4 = 0;
        for (float[] w: data){
            w1 = w1 + w[0];
            w2 = w2 + w[1];
            w3 = w3 + w[2];
            w4 = w4 + w[3];
        }
        System.out.println(w1 + " " + w2 + " " + w3 + " " + w4);
        System.out.println("k: " + k);
        w1 = 1 - k * w1;
        w2 = 1 - k * w2;
        w3 = 1 - k * w3;
        w4 = 1 - k * w4;

        System.out.println(w1 + " " + w2 + " " + w3 + " " + w4);
        float all = w1 + w2 + w3 + w4;
        cCt = w1 / all;
        cAte = w2 / all;
        cAlf = w3 / all;
        cCoce = w4 / all;
    }

    @Override
    public String toString() {
        return "Entropy{" +
                "cCt=" + cCt +
                ", cAte=" + cAte +
                ", cAlf=" + cAlf +
                ", cCoce=" + cCoce +
                '}';
    }

    public static void main(String[] args) {
        Entropy entropy = new Entropy();
        System.out.println(entropy);
    }
}
