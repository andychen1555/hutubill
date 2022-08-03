package gui.listner;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.util.MysqlUtil;
import service.ConfigService;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/8/2
 */
public class RecoverListener implements ActionListener {

    /**
     * RecoverListener 恢复监听器
     * 1. 首先判断MYSQL安装路径是否配置
     * <p>
     * 2. 打开文件选择器，指定要打开的文件
     * 文件名默认设置为hutubill.sql
     * 根据后缀名.sql过滤文件
     * <p>
     * 3. 调用MysqlUtil 进行恢复
     * <p>
     * 4. 提示恢复成功
     *
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if (0 == mysqlPath.length()) {
            JOptionPane.showMessageDialog(p, "恢复前请事先配置mysql的路径");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
            return;
        }
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("hutubill.sql"));
        fc.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return ".sql";
            }

            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".sql");
            }
        });

        int returnVal = fc.showOpenDialog(p);
        File file = fc.getSelectedFile();
        System.out.println(file);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                MysqlUtil.recover(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "恢复成功");
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "恢复失败\r\n,错误:\r\n" + e1.getMessage());
            }
        }
    }
}
