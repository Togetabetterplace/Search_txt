import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Start implements ActionListener {
    /*
     * 初始化小说分析器
     */
    JTextArea textArea;
    GUI gui;
    public Start(JTextArea textArea, GUI gui) {//构造函数
        this.textArea=textArea;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {//初始化分析器
        try {
            NovelAnalyzer N = new NovelAnalyzer(textArea,gui);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
