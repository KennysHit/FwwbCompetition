import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Ztest {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        int[] i1 = new int[]{1,2,3};
        int[] i2 = new int[]{1,3,3};
        int[] i3 = new int[]{1,2,3};
        ArrayList<int[]> arrayList = new ArrayList<int[]>();
        arrayList.add(i1);
        arrayList.add(i2);
        arrayList.add(i3);
        for(int[] w: arrayList){
            System.out.println(Arrays.toString(w));
        }
    }
}
