package GUI.panel;

import javax.swing.*;

/**
 * Abstract interface class
 * Centre the subPanel and update the data by calling the this.updateData() method.
 */
public abstract class WorkingPanel extends JPanel {
    public abstract void updateData();
    public abstract void addListener();
}
