package GUI.panel;

import GUI.listener.MonthPickerListener;
import util.DateUtil;
import util.GUIutil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class MonthPickerPanel extends WorkingPanel{
    static {
        GUIutil.useLNF();
    }
    public static MonthPickerPanel instance = new MonthPickerPanel();
    private final int startYear = 2017;
    public Date date = DateUtil.monthBegin();
    public JComboBox<Integer> cbMonth = new JComboBox<>(makeMonths());
    public JComboBox<Integer> cbYear = new JComboBox<>(makeYears());
    private JButton bSubmit = new JButton("Query");

    private MonthPickerPanel() {
        this.setLayout(new GridLayout(1, 3, 3, 8));
        cbYear.setSelectedIndex(DateUtil.thisYear() - startYear);
        cbMonth.setSelectedIndex(DateUtil.thisMonth());
        this.add(cbYear);
        this.add(cbMonth);
        this.add(bSubmit);
        addListener();
    }


    private Integer[] makeYears() {
        int thisYear = DateUtil.thisYear();
        Integer[] result = new Integer[thisYear - startYear + 1];
        for (int i = 0; i <= thisYear -startYear; i++) {
            result[i] = startYear + i;
        }
        return result;
    }

    private Integer[] makeMonths() {
        Integer[] result = new Integer[12];
        for (int i = 0; i < 12; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        bSubmit.addActionListener(new MonthPickerListener());
    }
}
