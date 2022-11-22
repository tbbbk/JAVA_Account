package GUI.listener;

import GUI.panel.CategoryPanel;
import entity.Category;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryService categoryService = new CategoryService();
        CategoryPanel p = CategoryPanel.instance;
        JButton b = (JButton) e.getSource();
        if (p.bAdd == b) {
            String name = JOptionPane.showInputDialog(null);
            if (null == name) {
                return;
            }
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(p, "Name is empty");
                return;
            }
            categoryService.add(name);
        }
        if (p.bEdit == b) {
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "Please select a row");
                return;
            }
            Category c = p.getSelectedCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog("Edit Name", c.getName());
            if (name == null) {
                return;
            }
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(p, "Name is empty");
                return;
            }
            categoryService.update(id, name);
        }
        if (p.bDelete == b) {
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "Please select a row");
                return;
            }
            Category c = p.getSelectedCategory();
            if (0 != c.getRecordNumber()) {
                JOptionPane.showMessageDialog(p, "Some data still belong to this Category");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "Sure?")) {
                return;
            }
            int id = c.getId();
            categoryService.delete(id);
        }
        p.updateData();
    }
}
