package GUI.panel;

import GUI.listener.CategoryListener;
import GUI.model.CategoryTableModel;
import entity.Category;
import service.CategoryService;
import util.GUIutil;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

public class CategoryPanel extends WorkingPanel{
    public static CategoryPanel instance = new CategoryPanel();
    public JButton bAdd = new JButton("New");
    public JButton bEdit = new JButton("Edit");
    public JButton bDelete = new JButton("Delete");

    private CategoryTableModel ctm = new CategoryTableModel();
    private JTable t = new JTable(ctm);

    public CategoryPanel() {
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

    public Category getSelectedCategory() {
        int index = t.getSelectedRow();
        return ctm.cs.get(Math.max(index, 0));
    }

    public static void main(String[] args) {
        GUIutil.showPanel(CategoryPanel.instance);
    }

    @Override
    public void updateData() {
        ctm.cs = new CategoryService().list();
        t.updateUI();
        if (ctm.cs.size() == 0) {
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        } else {
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    @Override
    public void addListener() {
        CategoryListener categoryListener = new CategoryListener();
        bAdd.addActionListener(categoryListener);
        bEdit.addActionListener(categoryListener);
        bDelete.addActionListener(categoryListener);
    }
}
