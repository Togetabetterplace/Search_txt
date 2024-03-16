import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Analyzer implements ActionListener {
    /*
     * 小说分析器
     */
    JTextArea textArea;

    public Analyzer(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            NovelAnalyzer.Analyze(textArea);//开始分析
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
