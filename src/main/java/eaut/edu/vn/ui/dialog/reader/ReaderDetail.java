package eaut.edu.vn.ui.dialog.reader;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.util.Util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReaderDetail extends Dialog {
    public String ma = "";
    DefaultTableModel dtmCTPM;
    JTable tblCTPM;

    public ReaderDetail(String title) {
        super(title);
        setHeader(new Header("BẢN THỐNG KÊ CHI TIẾT PHIẾU MƯỢN"));
        setFooter(new Footer());
        if (ma.length() != 0) {
            hienThi();
        }
    }

    public void hienThi() {
        try {
            String sql = "select * from ctpm where mapm=?";
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, ma);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String mapm = rs.getString(1);
                String masach = rs.getString(2);
                String ngaytra = rs.getString(3);
                String tinhtrangsach = rs.getString(4);
                String tinhtrangtra = rs.getString(5);
                String ghichu = rs.getString(7);

                Vector<String> vec = new Vector<String>();
                vec.add(mapm);
                vec.add(masach);
                vec.add(ngaytra);
                vec.add(tinhtrangsach);
                vec.add(tinhtrangtra);
                vec.add(ghichu);

                dtmCTPM.addRow(vec);
            }
            rs.close();
            pre.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addEvents() {
        // TODO Auto-generated method stub

    }

    @Override
    public void initComponents() {
        dtmCTPM = new DefaultTableModel();
        dtmCTPM.addColumn("Mã phiếu");
        dtmCTPM.addColumn("Mã sách");
        dtmCTPM.addColumn("Ngày trả");
        dtmCTPM.addColumn("Tình trạng sách");
        dtmCTPM.addColumn("Tình trạng trả");
        dtmCTPM.addColumn("Ghi chú");

        tblCTPM = new JTable(dtmCTPM);
        JScrollPane scCTPM = new JScrollPane(tblCTPM, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scCTPM, BorderLayout.CENTER);

    }

    public void showWindow() {
        this.setSize(1200, 750);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }
}
