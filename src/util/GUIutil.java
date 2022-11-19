package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIutil {
    // Image Folder
    private static final String imgFolder = "resources/img/";

    /**
     * customize skin
     */
//    public static void useLNF() {
//        try {
//            javax.swing.UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * set icon and text of a button
     *
     * @param b         button
     * @param filename  icon-path
     * @param tip       text
     */
    public static void setImageIcon(JButton b, String filename, String tip) {
        ImageIcon i = new ImageIcon((new File(imgFolder, filename)).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    /**
     * For test, put in a panel and start a window
     *
     * @param p         the panel to be tested
     * @param stretch   Scaling
     */
    public static void showPanel(JPanel p, double stretch) {
        //GUIutil.useLNF();
        JFrame f = new JFrame();
        f.setLocationRelativeTo(null);
        f.setSize(550, 500);
        CenterPanel cp = new CenterPanel(stretch);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }

    public static void showPanel(JPanel p) {
        showPanel(p, 0.85);
    }

    /**
     * set specify color for components
     * @param color colour
     * @param cs    components
     */
    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c: cs) {
            c.setForeground(color);
        }
    }

}
