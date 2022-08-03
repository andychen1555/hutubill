package startup;

import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import gui.util.GUIUtil;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/21
 */
public class Bootstrap {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        GUIUtil.useLNF();

        /**
         * 使用SwingUtilities.invokeAndWait的方式启动图形界面
         */
        SwingUtilities.invokeAndWait(() -> {
            /**
             * 显示主窗体，并让主面板下方的workingPanel显示消费一览Panel
             */
            MainFrame.instance.setVisible(true);
            MainPanel.instance.workingPanel.show(SpendPanel.instance);
        });
    }

}
