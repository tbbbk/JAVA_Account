package GUI.panel;

import GUI.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.GUIutil;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

/**
 * overview
 */
public class SpendPanel extends WorkingPanel{
    public static SpendPanel instance = new SpendPanel();

    private JLabel lMonthSpend =new JLabel("MonthSpend");
    private JLabel lTodaySpend =new JLabel("TodaySpend");
    private JLabel lAvgSpendPerDay =new JLabel("AvgSpendPerDay");
    private JLabel lMonthLeft =new JLabel("MonthAvailable");
    private JLabel lDayAvgAvailable =new JLabel("DayAvgAvailable");
    private JLabel lMonthLeftDay =new JLabel("MonthLeftDay");

    private JLabel vMonthSpend= new JLabel("$3720");
    private JLabel vTodaySpend= new JLabel("$23");
    private JLabel vAvgSpendPerDay= new JLabel("$232");
    private JLabel vMonthAvailable= new JLabel("$234");
    private JLabel vDayAvgAvailable= new JLabel("$123");
    private JLabel vMonthLeftDay= new JLabel("7days");

    private CircleProgressBar bar;

    private SpendPanel() {
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(Color.blue);
        GUIutil.setColor(Color.gray, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable, lMonthLeftDay,
                        vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        GUIutil.setColor(Color.blue, vMonthSpend, vTodaySpend);
        vMonthSpend.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("Arial Unicode MS", Font.BOLD, 23));

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.NORTH);
    }

    private JPanel center() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(), BorderLayout.WEST);
        p.add(east(), BorderLayout.CENTER);
        return p;
    }

    private JPanel south() {
        JPanel p =new JPanel();
        p.setLayout(new GridLayout(2, 4));
        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthAvailable);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);
        return p;
    }

    private JPanel west() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1));
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
    }

    private JPanel east() {
        return bar;
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        GUIutil.showPanel(SpendPanel.instance);
    }

    @Override
    public void updateData() {
        SpendPage page = new SpendService().getSpendPage();
        vMonthSpend.setText(page.MonthSpend);
        vTodaySpend.setText(page.TodaySpend);
        vAvgSpendPerDay.setText(page.AvgSpendPerDay);
        vDayAvgAvailable.setText(page.DayAvgAvailable);
        vMonthAvailable.setText(page.MonthAvailable);
        vMonthLeftDay.setText(page.MonthLeftDay);
        bar.setProgress(page.usagePercentage);
        if (page.isOverSpend) {
            vMonthAvailable.setForeground(Color.decode("#FF3333"));
            vMonthSpend.setForeground(Color.decode("#FF3333"));
            vTodaySpend.setForeground(Color.decode("#FF3333"));
        } else {
            vMonthAvailable.setForeground(Color.gray);
            vMonthSpend.setForeground(Color.blue);
            vTodaySpend.setForeground(Color.blue);
        }
    }

    @Override
    public void addListener() {

    }
}
