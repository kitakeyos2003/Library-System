package eaut.edu.vn.ui.dialog;

import javax.swing.*;

public abstract class Dialog extends JDialog {
    public Dialog(String title) {
        setTitle(title);
        initComponents();
        addEvents();
    }
    protected abstract void initComponents();
    protected abstract void addEvents();
    protected abstract void showWindow();
}
