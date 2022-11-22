package GUI.panel;

import service.RecordService;
import service.ReportService;
import util.ChartUtil;
import util.GUIutil;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends WorkingPanel{
    public static ReportPanel instance = new ReportPanel();

    public JLabel l = new JLabel();

    public ReportPanel() {
        this.add(l);
    }

    public static void main(String[] args) {
        GUIutil.showPanel(ReportPanel.instance);
    }

    @Override
    public void updateData() {
        Image i = ChartUtil.getImage(new ReportService().listThisMonthRecords(), 400, 260);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}
