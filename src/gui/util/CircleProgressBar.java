package gui.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/20
 */
public class CircleProgressBar extends JPanel {

    private int minimumProgress;

    private int maximumProgress;

    private int progress;

    private String progressText;

    private Color backgroundColor;

    private Color foregroundColor;

    public CircleProgressBar() {
        minimumProgress = 0;
        maximumProgress = 100;
        progressText = "0%";
    }

    /**
     * 自己开发一个组件涉及比较多的内容，包括画笔Graphics2D，画不同的形状，对paint(Graphics g)方法的理解。 这些需要一定的基础才可以做到。
     * <p>
     * 在本项目中，这个知识不是重点，所以就不展开了。 大家主要是掌握如何使用这个环形进度条。
     * <p>
     * 提示： 有兴趣的同学，我给一些提示，环形进度条，其实就是画一个有宽度的圆弧
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2d = (Graphics2D) g;
        // 开启抗锯齿
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        int fontSize = 0;
        if (getWidth() >= getHeight()) {
            x = (getWidth() - getHeight()) / 2 + 25;
            y = 25;
            width = getHeight() - 50;
            height = getHeight() - 50;
            fontSize = getWidth() / 8;
        } else {
            x = 25;
            y = (getHeight() - getWidth()) / 2 + 25;
            width = getWidth() - 50;
            height = getWidth() - 50;
            fontSize = getHeight() / 8;
        }
        graphics2d.setStroke(new BasicStroke(20.0f));
        graphics2d.setColor(backgroundColor);
        graphics2d.drawArc(x, y, width, height, 0, 360);
        graphics2d.setColor(foregroundColor);
        graphics2d.drawArc(x, y, width, height, 90,
                -(int) (360 * ((progress * 1.0) / (maximumProgress - minimumProgress))));
        graphics2d.setFont(new Font("黑体", Font.BOLD, fontSize));
        FontMetrics fontMetrics = graphics2d.getFontMetrics();
        int digitalWidth = fontMetrics.stringWidth(progressText);
        int digitalAscent = fontMetrics.getAscent();
        graphics2d.setColor(foregroundColor);
        graphics2d.drawString(progressText, getWidth() / 2 - digitalWidth / 2, getHeight() / 2 + digitalAscent / 2);
    }

    public static void main(String[] args) {
        GUIUtil.useLNF();
        //面板
        JPanel p = new JPanel();
        //进度条组件
        CircleProgressBar cpb = new CircleProgressBar();
        cpb.setBackgroundColor(ColorUtil.blueColor);
        cpb.setProgress(0);
        //按钮
        JButton b = new JButton("点击");
        p.setLayout(new BorderLayout());
        //添加组件
        p.add(cpb, BorderLayout.CENTER);
        p.add(b, BorderLayout.SOUTH);
        //显示面板
        GUIUtil.showPanel(p);
        //给按钮加监听
        b.addActionListener(e ->
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        for (int i = 0; i < 100; i++) {
                            cpb.setProgress(i + 1);
                            cpb.setForegroundColor(ColorUtil.getByPercentage(i + 1));
                            Thread.sleep(100);
                        }
                        return null;
                    }
                }.execute());
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (progress >= minimumProgress && progress <= maximumProgress)
            this.progress = progress;
        if (progress > maximumProgress)
            this.progress = maximumProgress;

        this.progressText = progress + "%";

        this.repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.repaint();
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        this.repaint();
    }

}
