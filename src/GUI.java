import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame {
    /*
    可视化界面
     */
    private JPanel jpl = new JPanel();
    private JButton jbt1 = new JButton("Input Your Data");
    private JButton jbt2 = new JButton("Start Analyzing");
    private JButton jbt3 = new JButton("Exit");

    private JTextArea textArea = new JTextArea();
    private JTextArea textArea1 = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(textArea);

    private JScrollPane scrollPane1 = new JScrollPane(textArea1);

    private JCheckBox transparencyCheckBox = new JCheckBox("Dark theme");

    public GUI() {

        FileTreePrint F = new FileTreePrint(textArea1);//初始化打印文件树
        Start S = new Start(textArea, this);//初始化小说参数
        Analyzer A = new Analyzer(textArea);//初始化小说分析器

        this.setTitle("NoveAnalyser Version 1.0.0");
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(scrollPane1);
        this.setSize(800, 600);
        this.add(jpl);
        this.setLocation(500, 100);

        scrollPane.setBounds(200, 10, 570, 460);
        scrollPane.setBackground(new Color(252, 241, 219));

        scrollPane1.setBounds(20, 10, 170, 460);

        // 添加设置背景按钮
        transparencyCheckBox.setBounds(20, 480, 100, 30);
        transparencyCheckBox.addItemListener(new CheckFunc(jpl, textArea, textArea1));
        jpl.add(transparencyCheckBox);

        jbt1.setSize(150, 50);
        jbt2.setSize(150, 50);
        jbt3.setSize(150, 50);

        jbt1.setLocation(260, 480);
        jbt2.setLocation(420, 480);
        jbt3.setLocation(580, 480);

        jbt1.addActionListener(S);
        jbt2.addActionListener(A);
        jbt3.addActionListener(e -> dispose());

        jpl.add(jbt1);
        jpl.add(jbt2);
        jpl.add(jbt3);
        jpl.setLayout(null);
        jpl.setBackground(new Color(236, 229, 226));

//        textArea.(img1);
        textArea.append("  +------------------------------------------------+\n");
        textArea.append("  |    NoveAnalyzer                                    |\n");
        textArea.append("  |                                       Version1.0.0   |\n");
        textArea.append("  +------------------------------------------------+\n");

        textArea.append("\n");
        textArea.append("  Welcome to use NVAZ Version 1.0.0\n");
        textArea.append("  Programmed by Zichen Tian\n");
        textArea.append("  Enjoy your novel!\n");
    }

    private boolean validateImagePath(String path) {
        return new File(path).exists();
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
    }

    public void errorfunc() {//出错处理方法，输入为空则报错
        JOptionPane.showMessageDialog(GUI.this, "Input data cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);

    }


}
