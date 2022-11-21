package service;

import DAO.ConfigDAO;
import entity.Config;


public class ConfigService {
    public static final String budget = "budget";
    private static final String default_budget = "500";

    private static ConfigDAO dao = new ConfigDAO();

    static {
        init();
    }

    /**
     * initialize the database
     */
    public static void init() {
        init(budget, default_budget);
    }

    public static void init(String key, String value) {
        Config config = dao.get(key);
        if (config == null) {
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            dao.add(c);
        }
    }

    public String get(String key) {
        return dao.get(key).getValue();
    }

    public void update(String key, String value) {
        Config c = dao.get(key);
        c.setValue(value);
        dao.update(c);
    }

    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }
}
