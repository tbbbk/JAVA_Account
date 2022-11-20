package service;

import DAO.RecordDAO;
import GUI.page.SpendPage;
import entity.Record;
import util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * service class
 * Wrapping of multiple database operations for use in SpendPanel
 */
public class SpendService {
    public SpendPage getSpendPage() {
        RecordDAO dao = new RecordDAO();
        List<Record> thisMonthRecords = dao.listThisMonth();
        List<Record> todayRecords = dao.listToday();
        int thisMonthTotalday = DateUtil.thisMonthTotalDay();

        int MonthSpend = 0;
        int TodaySpend = 0;
        int AvgSpendPerDay = 0;
        int MonthAvailable = 0;
        int DayAvgAvailable = 0;
        int MonthLeftDay = 0;
        int usagePercentage = 0;

//        int monthBudget = new Congi

    }
}
