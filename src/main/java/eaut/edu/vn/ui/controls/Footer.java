package eaut.edu.vn.ui.controls;

import eaut.edu.vn.util.Util;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {

    public Footer() {
        setBackground(new Color(48, 51, 107));
        JLabel lblContact = new JLabel("THÔNG TIN TRỢ GIÚP - LIÊN HỆ: 0312345678");
        lblContact.setForeground(Color.WHITE);
        Font font = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblContact.setFont(font);
        add(lblContact);
    }
}
