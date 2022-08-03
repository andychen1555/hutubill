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
public class BackupListener implements ActionListener {

    /**
     * BackupListener 备份监听器
     * 1. 首先判断MYSQL安装路径是否配置
     * <p>
     * 2. 打开文件选择器，指定要保存的文件
     * 文件名默认设置为hutubill.sql
     * 以后缀名.sql过滤文件
     * <p>
     * 3. 调用MysqlUtil 进行备份
     * <p>
     * 4. 提示备份成功
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if (mysqlPath.isEmpty()) {
            JOptionPane.showMessageDialog(p, "Mysql路径不存在，请配置！");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
        }

        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("hutubill.sql"));
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });

        int returnVal = fc.showSaveDialog(p);
        File file = fc.getSelectedFile();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //如果保存的文件名没有以.sql结尾，自动加上.sql
            System.out.println(file);
            if (!file.getName().toLowerCase().endsWith(".sql"))
                file = new File(file.getParent(), file.getName() + ".sql");
            System.out.println(file);

            try {
                MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "备份成功,备份文件位于:\r\n" + file.getAbsolutePath());
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "备份失败\r\n,错误:\r\n" + e1.getMessage());
            }
        }

    }
}
