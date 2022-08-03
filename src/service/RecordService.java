package service;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Date;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/22
 */
public class RecordService {

    RecordDAO recordDao = new RecordDAO();

    /**
     * RecordService消费记录业务类只有一个方法add
     * 根据消费金额，消费分类，备注和日期添加一条消费记录
     * @param spend
     * @param c
     * @param comment
     * @param date
     */
    public void add(int spend, Category c, String comment, Date date){
        Record r = new Record();
        r.spend = spend;
        r.cid = c.id;
        r.comment = comment;
        r.date = date;
        recordDao.add(r);
    }
}
