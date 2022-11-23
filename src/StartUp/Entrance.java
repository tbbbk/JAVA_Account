package StartUp;

import GUI.frame.MainFrame;
import GUI.panel.MainPanel;
import GUI.panel.SpendPanel;
import util.GUIutil;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Entrance {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        GUIutil.useLNF();

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}
