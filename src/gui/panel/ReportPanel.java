package gui.panel;

import entity.Record;
import gui.util.ChartUtil;
import gui.util.GUIUtil;
import service.ReportService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/21
 */
public class ReportPanel extends WorkingPanel {

    static {
        GUIUtil.useLNF();
    }

    public static ReportPanel instance = new ReportPanel();

    public JLabel l = new JLabel();

    private ReportPanel() {
        this.setLayout(new BorderLayout());
        Image i = ChartUtil.getImage(400, 300);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
        this.add(l);
    }

    @Override
    public void updateData() {
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 350, 250);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }
}
