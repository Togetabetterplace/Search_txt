import java.util.Stack;


public class sort {
    /*
     * 栈排序，用来比较人物相关程度
     */
    public static Stack<table> sortStk(Stack<Integer> s, Stack<Integer> s1, Stack<Integer> s2) {
        Stack<table> res = new Stack<table>();
        while (!s.isEmpty()) {//待排序栈不空则入栈
            int temp = s.pop();
            int temp1 = s1.pop();
            int temp2 = s2.pop();
            while (!res.isEmpty() && res.peek().elementA > temp) {//res不空且栈顶元素大于临时元素，pop一个
                table T = res.pop();
                s.push(T.elementA);
                s1.push(T.elementB);
                s2.push(T.elementC);
            }
            table T1 = new table(temp, temp1, temp2);//pop出来的加进res
            res.push(T1);
        }
        return res;
    }
}


