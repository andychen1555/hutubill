package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/20
 */
public class CategoryComboBoxModel implements ComboBoxModel {

    public List<Category> cs = new CategoryService().list();

    Category c;

    public CategoryComboBoxModel() {
        if (!cs.isEmpty()) {
            c = cs.get(0);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if (!cs.isEmpty()) {
            return c;
        } else {
            return null;
        }
    }

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public Category getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        //TODO
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        //TODO
    }

}
