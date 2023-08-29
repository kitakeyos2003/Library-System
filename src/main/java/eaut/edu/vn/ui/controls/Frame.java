package eaut.edu.vn.ui.controls;

import javax.swing.*;
import java.awt.*;

public abstract class Frame extends JFrame {

    protected JPanel mainPanel;
    private Header header;
    private Footer footer;
    public Frame(String title) {
        super(title);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Container container = getContentPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        container.add(mainPanel);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
        mainPanel.add(header, BorderLayout.NORTH);
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
        mainPanel.add(footer, BorderLayout.SOUTH);
    }

    protected void showWindow() {
        this.setVisible(true);
    }
    protected abstract void initComponents();
    protected abstract void addEvents();

}
