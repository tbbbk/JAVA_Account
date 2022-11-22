package GUI.listener;

import GUI.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listeners of MainPanel
 */
public class ToolBarListeners implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel p = MainPanel.instance;
        JButton b = (JButton) e.getSource();
        if (b == p.bSpend) {
            System.out.println("it's spend");
            p.workingPanel.show(SpendPanel.instance);
        } else if (b == p.bRecord) {
            System.out.println("it's record");
            p.workingPanel.show(RecordPanel.instance);
        } else if (b == p.bCategory) {
            System.out.println("It's category");
            p.workingPanel.show(CategoryPanel.instance);
        } else if (b == p.bHistory) {
            System.out.println("It's history");
            p.workingPanel.show(HistoryPanel.instance);
        } else if (b == p.bReport) {
            System.out.println("It's report");
            p.workingPanel.show(ReportPanel.instance);
        }
    }
}
