package GUI.listener;

import GUI.panel.MonthPickerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MonthPickerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MonthPickerPanel p = MonthPickerPanel.instance;
        Integer year = (Integer) p.cbYear.getSelectedItem();
        Integer month = (Integer) p.cbMonth.getSelectedItem();
        try {
            p.date = new SimpleDateFormat("yyyy-MM").parse(String.format("%4d-%02d", year, month));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}
