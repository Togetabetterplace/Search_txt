
import javax.swing.*;
import java.util.*;

public class Union_Set {
    /*
    并查集，用来对人物进行聚合
     */
    String[] names;

    int[] namesParents;

    int count;
    Set<Map.Entry<String, String>> Dqueue;

    Union_Set(String[] names, int[] namesParents, Set<Map.Entry<String, String>> Dqueue) {//构造函数
        this.names = names;
        this.namesParents = namesParents;
        this.Dqueue = Dqueue;
        this.count = names.length;
    }

    private int find(int i) {//找祖先
//        System.out.println(i);
        if (namesParents[i] == i) {
            return i;
        }
        return find(namesParents[i]);
    }

    public void Union(int i, int j) {//合并
        if (find(i) == find(j)) {
            return;
        } else {
            namesParents[find(i)] = find(j);
        }
        count--;
    }

    public List<List<String>> printnames(JTextArea textArea) {
        ArrayList<Unit> vec = new ArrayList<Unit>();
        for (int i = 0; i < names.length; i++) {
            if (namesParents[i] == i) {//如果这个人物为一个Union的祖先，则对它添加Unit
                ArrayList<Integer> a = new ArrayList<Integer>();
                vec.add(new Unit(i, a));//添加新的Unit，i是人物名索引，a是以i为祖先的人物
            }
        }
        for (int i = 0; i < names.length; i++) {
            int index = 0;
            for (int k = 0; k < vec.size(); k++) {
                if (vec.get(k).i == find(i)) {
                    index = k;//找到索引
                }
            }
            vec.get(index).adds(i);//根据索引添加人名
        }
        List<List<String>> resUnion = new List<List<String>>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<List<String>> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(List<String> strings) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends List<String>> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends List<String>> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public List<String> get(int index) {
                return null;
            }

            @Override
            public List<String> set(int index, List<String> element) {
                return null;
            }

            @Override
            public void add(int index, List<String> element) {

            }

            @Override
            public List<String> remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<List<String>> listIterator() {
                return null;
            }

            @Override
            public ListIterator<List<String>> listIterator(int index) {
                return null;
            }

            @Override
            public List<List<String>> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        for (int i = 0; i < vec.size(); i++) {
            textArea.append("UNION结果" + i + "：\n");
            List<String> res = new List<String>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<String> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(String s) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends String> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, Collection<? extends String> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public String get(int index) {
                    return null;
                }

                @Override
                public String set(int index, String element) {
                    return null;
                }

                @Override
                public void add(int index, String element) {

                }

                @Override
                public String remove(int index) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<String> listIterator() {
                    return null;
                }

                @Override
                public ListIterator<String> listIterator(int index) {
                    return null;
                }

                @Override
                public List<String> subList(int fromIndex, int toIndex) {
                    return null;
                }
            };
            for (int j = 0; j < vec.get(i).child.size(); j++) {
                textArea.append(names[vec.get(i).child.get(j)] + " ");
                res.add(names[vec.get(i).child.get(j)]);//找到Union内的人名并添加到res
            }
            resUnion.add(res);
            textArea.append("\n");
        }

        textArea.append("完成分析！");
        return resUnion;
    }
}
