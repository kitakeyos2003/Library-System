package eaut.edu.vn.ui.controls;

import eaut.edu.vn.interfaces.UIComposer;

import javax.swing.*;
import java.awt.*;

public abstract class CustomFrame extends JFrame implements UIComposer {

    protected JPanel mainPanel;
    private Header header;
    private Footer footer;
    public CustomFrame(String title) {
        super(title);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container container = getContentPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        container.add(mainPanel);
        initComponents();
        addEvents();
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

    @Override
    public void showWindow() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
