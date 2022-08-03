package gui.frame;

import gui.panel.MainPanel;

import javax.swing.*;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/21
 */
public class MainFrame extends JFrame {

    public static MainFrame instance = new MainFrame();

    private MainFrame(){
        this.setSize(500,450);
        this.setTitle("超级账本");
        this.setContentPane(MainPanel.instance);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        instance.setVisible(true);
    }

}
