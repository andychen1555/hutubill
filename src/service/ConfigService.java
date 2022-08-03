package service;

import dao.ConfigDAO;
import entity.Config;

import java.util.Objects;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/22
 */
public class ConfigService {

    /**
     * 预算
     */
    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "500";

    static ConfigDAO dao = new ConfigDAO();

    static {
        init();
    }

    /**
     * 因为设置信息里有两个数据，一个是预算，一个是Mysql路径。 这两个信息，无论如何都应该是存在数据库中的。 所以会调用init把他们俩初始化
     */
    public static void init() {
        init(budget, default_budget);
        init(mysqlPath, "");
    }

    /**
     * 首先根据key去查找，如果不存在，就使用value的值插入一条数据。
     *
     * @param key
     * @param value
     */
    private static void init(String key, String value) {
        Config config = dao.getByKey(key);
        if (Objects.isNull(config)) {
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            dao.add(c);
        }
    }

    public String get(String key) {
        Config conf = dao.getByKey(key);
        return conf.getValue();
    }

    public void update(String key, String value) {
        Config config = dao.getByKey(key);
        config.setValue(value);
        dao.update(config);
    }

    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }


}
