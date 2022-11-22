package GUI.listener;

import GUI.panel.HistoryListPanel;
import GUI.panel.MainPanel;
import GUI.panel.MonthPickerPanel;
import GUI.panel.RecordPanel;
import entity.Category;
import entity.Record;
import service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryListListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordService service = new RecordService();
        HistoryListPanel p = HistoryListPanel.instance;
        JButton b = (JButton) e.getSource();

        if (b == p.bAdd) {
            MainPanel.instance.workingPanel.show(RecordPanel.instance);
            RecordPanel.instance.DatePick.setDate(MonthPickerPanel.instance.date);
        }
        if (b == p.bEdit) {
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "Please select a line");
            } else {
                Record r = p.getSelectedRecord();
                RecordPanel.instance.updateId = r.getId();
                MainPanel.instance.workingPanel.show(RecordPanel.instance);
                RecordPanel.instance.tSpend.setText(String.valueOf(r.getSpend()));
                RecordPanel.instance.tComment.setText(String.valueOf(r.getComment()));
                RecordPanel.instance.cbCategory.setSelectedIndex(getModelID(r.getCid()));
                RecordPanel.instance.DatePick.setDate(r.getDate());
            }
        }
        if (b == p.bDelete) {
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "Please select a line");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "Sure?")) {
                return;
            }
            service.delete(p.getSelectedRecord().getId());
        }
        p.updateData();
    }

    private int getModelID(int cid) {
        List<Category> categories = RecordPanel.instance.cbModel.cs;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == cid)
                return i;
        }
        return 0;
    }
}
