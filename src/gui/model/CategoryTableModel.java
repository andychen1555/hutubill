package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/21
 */
public class CategoryTableModel implements TableModel {

    String[] columnNames = new String[]{"分类名称", "消费次数"};

    public List<Category> cs = new CategoryService().list();

    @Override
    public int getRowCount() {
        return cs.size();
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

    /**
     * 先通过cs.get(rowIndex)获取行对应的Category对象
     * 然后根据columnIndex返回对应的属性
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category c = cs.get(rowIndex);
        if (0 == columnIndex)
            return c.name;
        if (1 == columnIndex)
            return c.recordNumber;
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //TODO
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        //TODO
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        //TODO
    }
}
