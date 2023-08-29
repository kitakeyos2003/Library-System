package eaut.edu.vn.ui.dialog.loan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.util.Util;


public class DeleteBook extends Dialog {
    public String MaPM = "";
    public String user = "";
    JTextField txtMaPM;
    JComboBox cbMaSach;
    JButton btnXoa;
    Connection conn = ConnectMySQL.connect;

    public DeleteBook(String title) {
        super(title);
        if (MaPM.length() != 0) {
            hienThi();

        }
    }

    public void hienThi() {
        try {
            String sql0 = "Select MaSach from ctpm where MaPM=?";
            PreparedStatement pre0 = conn.prepareStatement(sql0);
            pre0.setString(1, MaPM);
            ResultSet rs0 = pre0.executeQuery();
            while (rs0.next()) {

                //String ms=rs0.getString(1)
                cbMaSach.addItem(rs0.getObject(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtMaPM.setText(MaPM);
    }

    @Override
    public void initComponents() {
        Container con = getContentPane();

        JPanel pnThemPhieuMuon = new JPanel();
        pnThemPhieuMuon.setLayout(new BorderLayout());
        con.add(pnThemPhieuMuon);

        JPanel pnTieuDe = new JPanel();
        JLabel lblTieuDe = new JLabel("THÊM THÔNG TIN SÁCH");
        pnTieuDe.add(lblTieuDe);
        pnThemPhieuMuon.add(pnTieuDe, BorderLayout.NORTH);

        JPanel pnLienHe = new JPanel();
        JLabel lblLienHe = new JLabel("THÔNG TIN TRỢ GIÚP - LIÊN HỆ: 0342565857");
        pnLienHe.add(lblLienHe);
        pnThemPhieuMuon.add(pnLienHe, BorderLayout.SOUTH);

        JPanel pnHienThiThemPM = new JPanel();
        pnHienThiThemPM.setLayout(new BoxLayout(pnHienThiThemPM, BoxLayout.Y_AXIS));
        pnThemPhieuMuon.add(pnHienThiThemPM, BorderLayout.CENTER);

        JPanel pnMaPM = new JPanel();
        pnMaPM.setLayout(new FlowLayout());
        JLabel lblMaPM = new JLabel("Mã phiếu: ");
        txtMaPM = new JTextField();
        txtMaPM.setPreferredSize(new Dimension(340, 30));
        pnMaPM.add(lblMaPM);
        pnMaPM.add(txtMaPM);

        JPanel pnMaSach = new JPanel();
        pnMaSach.setLayout(new FlowLayout());
        JLabel lblMaSach = new JLabel("Mã sách: ");
        cbMaSach = new JComboBox();
        //txtMaSach= new JTextField();
        cbMaSach.setPreferredSize(new Dimension(340, 30));
        pnMaSach.add(lblMaSach);
        pnMaSach.add(cbMaSach);


        pnHienThiThemPM.add(pnMaPM);
        pnHienThiThemPM.add(pnMaSach);

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        pnHienThiThemPM.add(pnThaoTac);
        btnXoa = new JButton("XÓA");
        btnXoa.setPreferredSize(new Dimension(110, 35));
        pnThaoTac.add(btnXoa);
        pnThaoTac.setBackground(new Color(241, 242, 246));
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        btnXoa.setFont(font5);

        btnXoa.setBackground(new Color(255, 177, 66));
        btnXoa.setForeground(Color.white);
        btnXoa.setBorder(BorderFactory.createLineBorder(new Color(255, 177, 66)));

        pnTieuDe.setBackground(new Color(48, 51, 107));
        lblTieuDe.setForeground(Color.WHITE);
        pnLienHe.setBackground(new Color(48, 51, 107));
        lblLienHe.setForeground(Color.WHITE);

        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);

        lblTieuDe.setFont(font1);
        lblLienHe.setFont(font4);

        lblMaPM.setFont(font4);
        lblMaSach.setFont(font4);

        txtMaPM.setFont(font4);
        cbMaSach.setFont(font4);

        pnMaPM.setBackground(new Color(241, 242, 246));
        pnMaSach.setBackground(new Color(241, 242, 246));

        lblMaSach.setPreferredSize(lblMaPM.getPreferredSize());

        txtMaPM.setEditable(false);
    }

    @Override
    public void addEvents() {
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String sql = "delete from ctpm where MaPM=? and MaSach=?";
                    PreparedStatement pre = conn.prepareStatement(sql);
                    pre.setString(1, txtMaPM.getText());
                    pre.setString(2, String.valueOf(cbMaSach.getSelectedItem()));
                    int soluongsach = 0;
                    try {
                        String sqlss = "Select SoLuong from sach where MaSach=?";
                        PreparedStatement presach = ConnectMySQL.connect.prepareStatement(sqlss);
                        presach.setString(1, String.valueOf(cbMaSach.getSelectedItem()));
                        ResultSet rssach = presach.executeQuery();
                        while (rssach.next()) {
                            soluongsach = rssach.getInt(1);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    soluongsach++;

                    try {
                        String sqlss1 = "update sach set SoLuong=? where MaSach=?";
                        PreparedStatement presach1 = ConnectMySQL.connect.prepareStatement(sqlss1);
                        presach1.setInt(1, soluongsach);
                        presach1.setString(2, String.valueOf(cbMaSach.getSelectedItem()));
                        int c = presach1.executeUpdate();
                        if (c > 0) {
                            JOptionPane.showMessageDialog(null, "Cập nhật số lượng sách thành công");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    int x = pre.executeUpdate();
                    if (x > 0) {
                        JOptionPane.showMessageDialog(null, "Xóa sách thành công");
                        dispose();
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    public void showWindow() {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }

}
