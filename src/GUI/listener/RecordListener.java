package GUI.listener;

import GUI.panel.RecordPanel;
import entity.Category;
import util.GUIutil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p =RecordPanel.instance;
        if (p.cbModel.cs.size() == 0) {
            JOptionPane.showMessageDialog(p, "Please add Category first");
        }
        String spend = p.tSpend.getText();
        if (!GUIutil.checkNumber(p.tSpend, "Input money")) {
            return;
        }
        if (spend.equals("0")) {
            JOptionPane.showMessageDialog(p, "Wrong input num");
            return;
        }

        Category c =p.getSelectedCategory();
        String comment = p.tComment.getText();
        Date d = p.DatePick.getDate();

        if(p.updateId < 0) {
            new RecordService
        }
    }
}
