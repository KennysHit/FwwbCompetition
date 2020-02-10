import java.util.LinkedList;

public class test {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.removeLast();
        linkedList.add(4);
        linkedList.add(5);
        System.out.println(linkedList);
    }
}
