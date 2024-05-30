package eaut.edu.vn.ui.dialog;

import eaut.edu.vn.interfaces.UIComposer;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;

import javax.swing.*;
import java.awt.*;

public abstract class Dialog extends JDialog implements UIComposer {
    protected JPanel mainPanel;
    private Header header;
    private Footer footer;
    public Dialog(String title, String header) {
        setTitle(title);
        Container container = getContentPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        container.add(mainPanel);
        setHeader(new Header(header));
        setFooter(new Footer());
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
        setVisible(true);
    }
}
