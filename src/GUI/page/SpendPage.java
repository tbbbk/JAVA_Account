package GUI.page;

/**
 * for spend panel, hold all the data
 */
public class SpendPage {
    public String MonthSpend;
    public String TodaySpend;
    public String AvgSpendPerDay;
    public String MonthAvailable;
    public String DayAvgAvailable;
    public String MonthLeftDay;
    public int usagePercentage;
    public boolean isOverSpend = false;

    public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, int dayAvgAvailable, int monthLeftDay, int usagePercentage) {
        this.MonthSpend = "$" + monthSpend;
        this.TodaySpend = "$" + todaySpend;
        this.AvgSpendPerDay = "$" + avgSpendPerDay;
        if (monthAvailable < 0) {
            this.isOverSpend = false;
        }
        if (!isOverSpend) {
            this.MonthAvailable = "$" + monthAvailable;
            this.DayAvgAvailable = "$" + dayAvgAvailable;
        }
        else {
            this.MonthAvailable = "over$" + -monthAvailable;
            this.DayAvgAvailable = "$0";
        }
        this.MonthLeftDay = monthLeftDay + "days";
        this.usagePercentage = usagePercentage;
    }
}
