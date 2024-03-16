import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 以树形结构打印文件结构
 * 使用方法com.xm.FileTreePrintTest
 * |----runtime
 * |    |----content-content
 * |    |    |----xxx-component.xml
 * |    |    |----entitydef
 * |    |    |    |----entitymodel.xml
 */
public class FileTreePrint {

    static JTextArea J;

    FileTreePrint(JTextArea J) {//初始化文件树
        this.J = J;
        String path = "C:\\Users\\99250\\Desktop\\Novel";
        FileTreePrint.FileTreeBean fileTreeBean = new FileTreePrint.FileTreeBean("runtime", 1, path);
        FileTreePrint.getChildrenFile(fileTreeBean);
        fileTreeBean.print();
    }

    /**
     * 获取文件路径下的所有文件
     *
     * @param fileTreeBean
     * @return
     */

    public static FileTreeBean getChildrenFile(FileTreeBean fileTreeBean) {

        String path = fileTreeBean.getPath();//把Path转成字符串
        File file = new File(path);
        if (file.isDirectory()) {//测试是否能定向
            File[] files = file.listFiles();//file转成一堆相对路径的list
            if (null == files) {
                return null;
            }
            for (File file1 : files) {//遍历并添加孩子，找到孩子后level+1
                FileTreeBean file2 = getChildrenFile(new FileTreeBean(file1.getName(),
                        fileTreeBean.getLevel() + 1, file1.getPath()));
                fileTreeBean.addChild(file2);
            }
        }

        return fileTreeBean;
    }

    /**
     * 封装文件对象，提供打印文件功能
     * |
     * |----folder1
     * |	|----file1
     * |	|----file2
     * |	|----folder2
     * |	|	|----file4
     * |	|----file3
     * |----folder3
     */
    public static class FileTreeBean {//文件对象封装器
        private String name;
        private int level;
        private String path;
        private List<FileTreeBean> child;

        public FileTreeBean(String name, int level, String path) {//构造函数
            this.name = name;
            this.level = level;
            this.path = path;
        }

        //封装属性
        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<FileTreeBean> getChild() {
            return child;
        }

        public void setChild(List<FileTreeBean> child) {
            this.child = child;
        }

        void addChild(FileTreeBean fileTreeBean) {
            if (null == this.child) child = new ArrayList<>();//初始化孩子节点
            if (null == fileTreeBean) return;//后面没有了返回
            fileTreeBean.setLevel(this.level + 1);//找高度
            child.add(fileTreeBean);//添加下面的fileTreeBean
        }

        public void print() {//递归打印
            appendString();//最开始的字符串
            if (null != this.child) {
                for (FileTreeBean fileTreeBean : child) {
                    fileTreeBean.print();
                }
            }
        }

        public void print(Pattern pattern, boolean include) {
            Matcher matcher = pattern.matcher(this.name);
            if (include) {
                if (matcher.find()) {
                    appendString();
                }
            } else {
                if (!matcher.find()) {
                    appendString();
                }
            }

            if (null != this.child) {
                for (FileTreeBean fileTreeBean : child) {
                    fileTreeBean.print(pattern, include);
                }
            }
        }

        private void appendString() {//添加字符串，未来能够打印
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.level - 1; i++) {
                sb.append("|");
                sb.append("    ");
            }
            sb.append("|----");
            sb.append(this.name + "\n");
            J.append(sb.toString());
        }
    }
}
