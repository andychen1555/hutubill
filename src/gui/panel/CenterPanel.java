package gui.panel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/20
 */
public class CenterPanel extends JPanel {

    //表示拉伸比例，1就是填满，0.5就是填一半
    private double rate;

    //显示的组件
    private JComponent c;

    //是否拉伸
    private boolean strech;

    public CenterPanel(double rate, boolean strech) {
        this.setLayout(null);
        this.rate = rate;
        this.strech = strech;
    }

    public CenterPanel(double rate) {
        this(rate, true);
    }

    /**
     * 使用show方法显示组件,show方法中的思路是：
     * 然后把先把这个容器中的组件都移出，新的组件加进来，并且调用updateUI进行界面渲染。
     * updateUI会导致swing去调用repaint()方法。
     *
     *
     *
     * workingPanel是CenterPanel类型的，那么只需要在show方法中判断，如果是WorkingPanel类型，那么就可以调用其updateData来做到把数据更新到界面上了。
     */
    public void show(JComponent p) {
        this.c = p;
        Arrays.asList(getComponents())
                .forEach(this::remove);
        add(p);

        if (p instanceof WorkingPanel)
           ((WorkingPanel) p).updateData();

        this.updateUI();
    }

    /**
     * 在repaint方法中，就会使用绝对定位的方式把组件放在中间位置。
     * 如果strech是true，就会根据整个容器的大小，设置组件的大小，达到拉伸的效果
     * 如果strech是false, 就使用组件的preferredSize，即非拉伸效果。
     */
    @Override
    public void repaint() {
        if (Objects.nonNull(c)) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();
            if (strech)
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            else
                c.setSize(componentSize);
            c.setLocation(containerSize.width / 2 - c.getSize().width / 2, containerSize.height / 2 - c.getSize().height / 2);
        }
        super.repaint();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(200,200);
        f.setLocationRelativeTo(null);
//        CenterPanel cp = new CenterPanel(0.5, true);
        CenterPanel cp = new CenterPanel(0.5, true);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JButton b = new JButton("abc");
        cp.show(b);
    }

}
