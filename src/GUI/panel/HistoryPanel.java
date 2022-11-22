package GUI.panel;

import util.GUIutil;

import java.awt.*;

public class HistoryPanel extends WorkingPanel{
    public static HistoryPanel instance = new HistoryPanel();

    private HistoryPanel() {
        this.setLayout(new BorderLayout());
        this.add(MonthPickerPanel.instance, BorderLayout.NORTH);
        this.add(HistoryListPanel.instance, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        GUIutil.showPanel(HistoryPanel.instance);
    }

    @Override
    public void updateData() {
        HistoryListPanel.instance.updateData();
    }

    @Override
    public void addListener() {

    }
}
