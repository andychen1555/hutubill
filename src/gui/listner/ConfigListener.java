package gui.listner;

import gui.panel.ConfigPanel;
import gui.util.GUIUtil;
import service.ConfigService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/22
 */
public class ConfigListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        //首先判断输入的预算值是否是整数
        if (!GUIUtil.checkNumber(p.tfBudget, "本月预算"))
            return;
        //接着判断输入的MYSQL安装路径是否正确。 判断办法是看路径对应的bin子目录下是否有mysql.exe文件存在
        String mysqlPath = p.tfMysqlPath.getText();
        if (mysqlPath.length() != 0) {
            File commandFile = new File(mysqlPath, "bin/mysql");
            if (!commandFile.exists()) {
                JOptionPane.showMessageDialog(p, "Mysql路径不正确");
                p.tfMysqlPath.grabFocus();
                return;
            }
        }
        //如果上述两个判断都通过了，那么就调用业务类ConfigService进行数据更新
        ConfigService cs = new ConfigService();
        cs.update(ConfigService.budget, p.tfBudget.getText());
        cs.update(ConfigService.mysqlPath, mysqlPath);

        //最后提示设置修改成功
        JOptionPane.showMessageDialog(p, "设置修改成功");
    }
}
