package GUI.panel;


import GUI.listener.RecordListener;
import GUI.model.CategoryComboBoxModel;
import entity.Category;

import javax.swing.*;

import service.CategoryService;
import util.GUIutil;

import java.awt.*;
import java.util.Date;
import org.jdesktop.swingx.JXDatePicker;

/**
 * Bookkeeping Panel
 */
public class RecordPanel extends WorkingPanel {
    static {
        GUIutil.useLNF();
    }
    public static RecordPanel instance = new RecordPanel();
    public int updateId = -1;   // (>0 change || <0 add)
    private JLabel lSpend = new JLabel("Spend($)");
    private JLabel lCategory = new JLabel("Category");
    private JLabel lComment = new JLabel("Comment");
    private JLabel lDate = new JLabel("Date");

    public JTextField tSpend = new JTextField("0");
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
    public JTextField tComment = new JTextField();
    public JXDatePicker DatePick = new JXDatePicker(new Date());

    private JButton bSubmit = new JButton("Save");

    public RecordPanel() {
        GUIutil.setColor(Color.gray, lSpend, lCategory, lComment, lDate);
        GUIutil.setColor(Color.blue, bSubmit);
        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4, 2, gap, gap));

        pInput.add(lSpend);
        pInput.add(tSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tComment);
        pInput.add(lDate);
        pInput.add(DatePick);

        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit, BorderLayout.CENTER);

        addListener();
    }

    public Category getSelectedCategory() {
        return (Category) cbCategory.getSelectedItem();
    }

    public static void main(String[] args) {
        GUIutil.showPanel(RecordPanel.instance);
    }

    @Override
    public void updateData() {
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        tSpend.setText("");
        tComment.setText("");
        DatePick.setDate(new Date());
        tSpend.grabFocus();
    }

    @Override
    public void addListener() {
        bSubmit.addActionListener(new RecordListener());
    }
}
