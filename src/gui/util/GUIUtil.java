package gui.util;

import gui.panel.CenterPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/20
 */
public class GUIUtil {

    public static void main(String[] args) {
        GUIUtil.useLNF();
        JPanel p = new JPanel();
        p.add(new JButton("按钮1"));
        p.add(new JButton("按钮2"));
        GUIUtil.showPanel(p);
    }

    private static String imageFolder = "/Users/original/code/hutubill/img";

    /**
     * 给按钮设置图标和文本以及提示文字
     * @param b
     * @param fileName
     * @param tip
     */
    public static void setImageIcon(JButton b, String fileName, String tip) {
        ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    /**
     * 给多个组件设置前景色
     * @param color
     * @param cs
     */
    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }

    /**
     * @param p
     * @param strechRate 拉伸比例1表示满屏幕
     *  快速显示一个面板的内容，根据前面章节的学习，我们知道，本项目会规划一个一个的独立的面板Panel。 但是面板本身不能独立显示出来，所以准备这么一个
     * showPanel方法，就可以很方便的显示这些面板了。
     * 显示的时候还用到了 居中面板
     */
    public static void showPanel(JPanel p, double strechRate) {
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(strechRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }

    public static void showPanel(JPanel p) {
        showPanel(p, 0.85);
    }

    /**
     * 校验一个组件内容是否是数字格式
     * @param tf
     * @param input
     * @return
     */
    public static boolean checkNumber(JTextField tf, String input) {
        if (!checkEmpty(tf, input))
            return false;
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, input + " 需要是整数");
            tf.grabFocus();
            return false;
        }
    }

    /**
     * 校验一个组件的内容是否是零
     * @param tf
     * @param input
     * @return
     */
    public static boolean checkZero(JTextField tf, String input) {
        if (!checkNumber(tf, input))
            return false;
        String text = tf.getText().trim();

        if (0 == Integer.parseInt(text)) {
            JOptionPane.showMessageDialog(null, input + " 不能为零");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    //校验一个输入框内容是否是空
    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + " 不能为空");
            tf.grabFocus();
            return false;
        }
        return true;

    }

    public static int getInt(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }

    /**
     * 使用水晶皮肤。 Java提供很多种皮肤,但是大部分都比较难看，水晶皮肤还算是看得过去的一种
     */
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
