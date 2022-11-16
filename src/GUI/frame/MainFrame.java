package GUI.frame;

import GUI.panel.MainPanel;
import util.GUIutil;

import javax.swing.*;

public class MainFrame extends JFrame {
    static {
        GUIutil.useLNF();
    }

    public static MainFrame instance = new MainFrame();

    private MainFrame() {
        this.setSize(550, 430);
        this.setTitle("Account");
        this.setContentPane(MainPanel.instance);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        instance.setVisible(true);
    }
}
