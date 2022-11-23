package GUI.panel;

import GUI.listener.HistoryListListener;
import GUI.model.RecordTableModel;
import entity.Record;
import service.RecordService;
import util.GUIutil;

import javax.swing.*;
import java.awt.*;

public class HistoryListPanel extends WorkingPanel{
    static {
        GUIutil.useLNF();
    }
    public static HistoryListPanel instance = new HistoryListPanel();
    public JButton bAdd = new JButton("New");
    public JButton bEdit = new JButton("Edit");
    public JButton bDelete = new JButton("Delete");

    private RecordTableModel rtm = new RecordTableModel();
    private JTable t = new JTable(rtm);

    private HistoryListPanel() {
        GUIutil.setColor(Color.blue, bAdd, bEdit, bDelete);
        JScrollPane sp = new JScrollPane(t);
        JPanel pSubmit = new JPanel();
        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(sp, BorderLayout.CENTER);
        this.add(pSubmit, BorderLayout.NORTH);

        this.addListener();
    }


    public boolean checkSelected(){
        return t.getSelectedRow()>=0;
    }

    public Record getSelectedRecord() {
        int index = t.getSelectedRow();
        return rtm.rs.get(Math.max(index, 0));
    }

    @Override
    public void updateData() {
        rtm.rs = new RecordService().listMonth(MonthPickerPanel.instance.date);
        t.updateUI();
    }

    @Override
    public void addListener() {
        HistoryListListener historyListListener = new HistoryListListener();
        bAdd.addActionListener(historyListListener);
        bEdit.addActionListener(historyListListener);
        bDelete.addActionListener(historyListListener);
    }
}
