import java.util.ArrayList;

public class TestList {
    public static void main(String[] args) {
        TestList t = new TestList();
        t.test();
    }

    private void test(){
        ArrayList<Integer> list = new ArrayList<>(2);
        System.out.println("list size: " + list.size());
        list.set(0, 0);
     }
}
