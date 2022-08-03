package service;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/27
 */
public class ReportService {

    /**
     * 获取某一天的消费金额
     *
     * @param d
     * @param monthRawData
     * @return
     */
    public int getDaySpend(Date d, List<Record> monthRawData) {
        int daySpend = 0;
        for (Record record : monthRawData) {
            if (record.date.equals(d))
                daySpend += record.spend;
        }
        return daySpend;
    }

    /**
     * 获取一个月的消费记录集合
     * 返回一个消费记录集合，假设本月有30天，那么这个集合就有30条Record。每个Record对应一天的消费总金额，如果那天没有消费，则消费金额为0.
     * @return
     */
    public List<Record> listThisMonthRecords() {
        RecordDAO dao= new RecordDAO();
        List<Record> monthRawData= dao.listThisMonth();
        List<Record> result= new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime() ;
            r.spend= getDaySpend(eachDayOfThisMonth,monthRawData);
            result.add(r);
        }
        return result;

    }

}
