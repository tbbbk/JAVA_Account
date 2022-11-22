package GUI.model;

import DAO.CategoryDAO;
import GUI.panel.MonthPickerPanel;
import entity.Record;
import service.RecordService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class RecordTableModel implements TableModel {
    private String[] columnNames = new String[]{"ID", "spend", "category", "comment", "date"};
    public List<Record> rs = new RecordService().listMonth(MonthPickerPanel.instance.date);

    @Override
    public int getRowCount() {
        return rs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return rs.get(rowIndex).getId();
        }
        if (columnIndex == 1) {
            return rs.get(rowIndex).getSpend();
        }
        if (columnIndex == 2) {
            int cid = rs.get(rowIndex).getCid();
            return new CategoryDAO().get(cid).getName();
        }
        if (columnIndex == 3) {
            return rs.get(rowIndex).getComment();
        }
        if (columnIndex == 4) {
            return rs.get(rowIndex).getDate();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
