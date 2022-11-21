package GUI.listener;

import GUI.panel.MainPanel;
import GUI.panel.SpendPanel;

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
        }
    }
}
