package GUI.panel;

import util.CenterPanel;
import util.GUIutil;

import javax.swing.*;
import java.awt.*;

/**
 * The main panel
 * consist of WorkingPanel & CenterPanel
 * */

public class MainPanel extends JPanel {
    static {
        //GUIutil.useLNF();
    }

    public static MainPanel instance = new MainPanel();
    private JToolBar tb = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bHistory = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public CenterPanel workingPanel;

    private MainPanel() {
        GUIutil.setImageIcon(bSpend, "home.png", "Overview");
        GUIutil.setImageIcon(bRecord, "record.png", "Bookkeeping");
        GUIutil.setImageIcon(bHistory, "history.png", "History");
        GUIutil.setImageIcon(bCategory, "report.png", "Report");
        GUIutil.setImageIcon(bReport, "category.png", "Category");
        GUIutil.setImageIcon(bConfig, "config.png", "Config");
        GUIutil.setImageIcon(bBackup, "backup.png", "Backup");
        GUIutil.setImageIcon(bRecover, "restore.png", "Restore");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bHistory);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);

        workingPanel = new CenterPanel(0.85);

        this.setLayout(new BorderLayout());
        this.add(tb, BorderLayout.NORTH);
        this.add(workingPanel, BorderLayout.CENTER);

        // TODO: 2022/11/16 add listener
    }

    public static void main(String[] args) {
        GUIutil.showPanel(MainPanel.instance, 1);
    }
}
