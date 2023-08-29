package eaut.edu.vn.ui.controls;

import eaut.edu.vn.util.Util;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {

    public Footer() {
        setBackground(new Color(48, 51, 107));
        JLabel lblContact = new JLabel("THÔNG TIN TRỢ GIÚP - LIÊN HỆ: 0342565857");
        lblContact.setForeground(Color.WHITE);
        Font fontx = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblContact.setFont(fontx);
        add(lblContact);
    }
}
