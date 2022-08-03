package gui.panel;

import javax.swing.*;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/22
 */
public abstract class WorkingPanel extends JPanel {

    public abstract void updateData();

    public abstract void addListener();

}
