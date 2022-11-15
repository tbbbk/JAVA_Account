package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIutil {
    // Image Folder
    private static final String imgFolder = "resources/img/";
    // Customize Skin
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

}
