import java.util.ArrayList;

public class Unit {
    int i;
    ArrayList<Integer> child;

    Unit(int i,ArrayList<Integer> child){
        this.i = i;
        this.child = child;
    }
    public void adds(int a){
        this.child.add(a);
    }//添加元素方法
}
