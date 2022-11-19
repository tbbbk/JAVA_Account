package util;

import GUI.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Tools class, implement a panel which child panel centered
 */

public class CenterPanel extends JPanel{
    private double rate;
    private JComponent c;
    private boolean stretch;

    /**
     * @param rate Tensile ratios
     * @param stretch Stretched or not
     */
    public CenterPanel(double rate, boolean stretch) {
        this.setLayout(null);
        this.rate = rate;
        this.stretch =stretch;
    }

    public CenterPanel(double rate) {
        this(rate, true);
    }

    @Override
    /**
     * Only be called when swing update the interface
     */
    public void repaint() {
        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();
            if (stretch) {
                c.setSize((int)(containerSize.width * rate), (int)(containerSize.height * rate));
            }
            else {
                c.setSize(componentSize);
            }
            c.setLocation(containerSize.width / 2 - c.getSize().width / 2,  containerSize.height / 2 - c.getSize().height / 2);
        }
        super.repaint();
    }

    /**
     * Centered display a child panel
     *
     * @param p the child panel
     */
    public void show(JComponent p){
        this.c = p;
        Component[] cs = getComponents();
        for (Component c: cs) {
            remove(c);
        }
        add(p);
        if ((p instanceof WorkingPanel)) {
            ((WorkingPanel)p).updateData();
        }
        this.updateUI();
    }
}
