package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/22
 */
public class CategoryService {

    CategoryDAO categoryDao = new CategoryDAO();
    RecordDAO recordDao = new RecordDAO();

    /**
     * 查询出所有的Category，然后根据每种分类，再把分类对应的消费记录总数查出来，并且设置在对应分类实例的recordNumer上。
     * 最后再根据recordNumer进行倒排序，让消费频率高的分类放在前面。
     * @return
     */
    public List<Category> list() {
        List<Category> cs = categoryDao.list();
        for (Category c : cs) {
            List<Record> rs = recordDao.list(c.id);
            c.recordNumber = rs.size();
        }
        cs.sort((c1, c2) -> c2.recordNumber - c1.recordNumber);

        return cs;
    }

    /**
     * 增加一种分类
     * @param name
     */
    public void add(String name) {
        Category c = new Category();
        c.setName(name);
        categoryDao.add(c);
    }

    /**
     * 更新分类
     * @param id
     * @param name
     */
    public void update(int id, String name) {
        Category c = new Category();
        c.setName(name);
        c.setId(id);
        categoryDao.update(c);
    }

    /**
     * 删除分类
     * @param id
     */
    public void delete(int id) {
        categoryDao.delete(id);
    }

}
