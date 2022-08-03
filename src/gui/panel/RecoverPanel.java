package gui.panel;

import gui.listner.RecordListener;
import gui.listner.RecoverListener;
import gui.util.ColorUtil;
import gui.util.GUIUtil;

import javax.swing.*;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/21
 */
public class RecoverPanel extends WorkingPanel {

    static {
        GUIUtil.useLNF();
    }

    public static RecoverPanel instance = new RecoverPanel();

    JButton bRecover = new JButton("恢复");

    public RecoverPanel() {

        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);
        addListener();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        bRecover.addActionListener(new RecoverListener());
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
    }

}
