package eaut.edu.vn.ui.dialog.loan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.util.Util;


public class UpdateBorrowedBookStatus extends Dialog {
    public String MaPM = "";
    public String user = "";
    JTextField txtMaPM;
    JComboBox cbMaSach;
    JButton btnXoa;

    public UpdateBorrowedBookStatus(String title) {
        super(title, "QUẢN LÝ PHIẾU MƯỢN");
    }

    public void loadInfo() {
        try {
            String sql0 = "Select MaSach from ctpm where MaPM=?";
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement pre0 = connection.prepareStatement(sql0);
            pre0.setString(1, MaPM);
            ResultSet rs0 = pre0.executeQuery();
            while (rs0.next()) {
                cbMaSach.addItem(rs0.getObject(1));

            }
            rs0.close();
            pre0.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtMaPM.setText(MaPM);
    }

    @Override
    public void initComponents() {
        JPanel pnHienThiThemPM = new JPanel();
        pnHienThiThemPM.setLayout(new BoxLayout(pnHienThiThemPM, BoxLayout.Y_AXIS));
        mainPanel.add(pnHienThiThemPM, BorderLayout.CENTER);

        JPanel pnMaPM = new JPanel();
        pnMaPM.setLayout(new FlowLayout());
        JLabel lblMaPM = new JLabel("Mã phiếu: ");
        txtMaPM = new JTextField();
        txtMaPM.setEnabled(false);
        txtMaPM.setPreferredSize(new Dimension(340, 30));
        pnMaPM.add(lblMaPM);
        pnMaPM.add(txtMaPM);

        JPanel pnMaSach = new JPanel();
        pnMaSach.setLayout(new FlowLayout());
        JLabel lblMaSach = new JLabel("Sách: ");
        cbMaSach = new JComboBox();
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

        btnXoa.setBackground(new Color(4, 191, 138));
        btnXoa.setForeground(Color.white);
        btnXoa.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));

        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);

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
        btnXoa.addActionListener(e -> {

            try {
                String sql = "delete from ctpm where MaPM=? and MaSach=?";
                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1, txtMaPM.getText());
                pre.setString(2, String.valueOf(cbMaSach.getSelectedItem()));
                int x = pre.executeUpdate();
                pre.close();
                connection.close();
                if (x > 0) {
                    try {
                        String sqlss1 = "UPDATE sach SET SoLuong = SoLuong + ? WHERE MaSach=?";
                        connection = DbManager.getInstance().getConnection();
                        PreparedStatement presach1 = connection.prepareStatement(sqlss1);
                        presach1.setInt(1, 1);
                        presach1.setString(2, String.valueOf(cbMaSach.getSelectedItem()));
                        int c = presach1.executeUpdate();
                        presach1.close();
                        connection.close();
                        if (c > 0) {
                            JOptionPane.showMessageDialog(null, "Cập nhật số lượng sách thành công");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Xóa sách thành công");
                    dispose();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
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
