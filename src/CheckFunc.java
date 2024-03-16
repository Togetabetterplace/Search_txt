import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckFunc implements ItemListener {
    /*
     * 背景改变函数
     */
    JPanel G;
    JTextArea T;
    JTextArea T1;

    CheckFunc(JPanel G, JTextArea T, JTextArea T1) {
        this.G = G;
        this.T = T;
        this.T1 = T1;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox jCheck = (JCheckBox) e.getItem();
        if (jCheck.isSelected()) {//暗色背景
            T.setBackground(new Color(89, 85, 85));
            T1.setBackground(new Color(93, 88, 88));

            T.setForeground(Color.lightGray);
            T1.setForeground(Color.lightGray);
            G.setBackground(Color.darkGray);
        } else {//亮色背景
            T1.setBackground(new Color(252, 251, 249));
            T.setBackground(new Color(252, 251, 249));

            T1.setForeground(Color.DARK_GRAY);
            T.setForeground(Color.DARK_GRAY);
            G.setBackground(new Color(232, 223, 219));

        }
        T.append("\n");
        T1.append("\n");
    }

}
