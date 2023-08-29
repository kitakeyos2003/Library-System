package eaut.edu.vn.ui.controls;

import eaut.edu.vn.util.Util;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {

    public Header(String title) {
        setBackground(new Color(48, 51, 107));
        JLabel lblTitle = new JLabel(title);
        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        lblTitle.setFont(font1);
        lblTitle.setForeground(Color.WHITE);
        add(lblTitle);
    }
}
