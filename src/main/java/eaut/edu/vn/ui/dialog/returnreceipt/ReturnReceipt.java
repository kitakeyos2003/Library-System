package eaut.edu.vn.ui.dialog.returnreceipt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.util.Util;


public class ReturnReceipt extends Dialog {
    public String MaPM = "", MaSach = "", NgayHenTra = "", TinhTrangSach = "", tentk = "", MaDG = "";
    JTextField txtMaPhieu, txtMaDG, txtMaSach, txtNgayHenTra, txtNgayTra, txtTTSachMuon, txtTTSachTra, txtThuThuNhanSach, txtGhiChu;
    JButton btnTraSach;
    JDateChooser choosedate;

    public ReturnReceipt(String title) {
        super(title, "QUẢN LÝ PHIẾU TRẢ");
    }

    public void loadInfo() {
        txtMaPhieu.setText(MaPM);
        txtMaSach.setText(MaSach);
        txtMaDG.setText(MaDG);
        txtNgayHenTra.setText(NgayHenTra);
        txtTTSachMuon.setText(TinhTrangSach);
        txtThuThuNhanSach.setText(tentk);
    }

    @Override
    public void addEvents() {
        btnTraSach.addActionListener(e -> {
            // TODO Auto-generated method stub
            Date returnDate = choosedate.getDate();
            if (returnDate == null) {
                JOptionPane.showMessageDialog(null, "Hãy chọn ngày trả");
                return;
            }
            if (returnDate.after(new Date())) {
                JOptionPane.showMessageDialog(null, "Không được chọn ngày trả sau ngày hôm nay");
                return;
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String datetra = df.format(returnDate);

            try {
                String sql = "Update ctpm set NgayTra=?, TinhTrangTra=?, GhiChu=?,User=? where MaPM=? and MaSach=?";

                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1, datetra);
                pre.setString(2, txtTTSachTra.getText());
                pre.setString(3, txtGhiChu.getText());
                pre.setString(4, tentk);
                pre.setString(5, txtMaPhieu.getText());
                pre.setString(6, txtMaSach.getText());

                int x = pre.executeUpdate();
                pre.close();
                connection.close();
                if (x > 0) {
                    // tinh phan tram hư sách
                    int muon = Integer.parseInt(txtTTSachMuon.getText());
                    int tra = Integer.parseInt(txtTTSachTra.getText());
                    int hieu = muon - tra;

                    double SoTien = 0;
                    if (tra == 0) {
                        try {
                            String sqlsach = "Select GiaTien from sach where MaSach=?";
                            connection = DbManager.getInstance().getConnection();
                            PreparedStatement presach = connection.prepareStatement(sqlsach);
                            presach.setString(1, txtMaSach.getText());
                            ResultSet rs1 = presach.executeQuery();
                            if (rs1.next()) {
                                SoTien = rs1.getDouble(1);
                            }
                            rs1.close();
                            presach.close();
                            connection.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "Bạn làm mất sách.Tiền Sách: " + SoTien);
                        try {
                            String sqldocgia = "update docgia set MatSach = MatSach + ? where MaDG=?";
                            connection = DbManager.getInstance().getConnection();
                            PreparedStatement pre1 = connection.prepareStatement(sqldocgia);
                            pre1.setInt(1, 1);
                            pre1.setString(2, MaDG);
                            int a = pre1.executeUpdate();
                            pre1.close();
                            connection.close();
                            if (a > 0) {
                                JOptionPane.showMessageDialog(null, "Đã cập nhật số lần mất sách của độc giả");
                                dispose();
                                return;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }


                    JOptionPane.showMessageDialog(null, "Trả sách thành công");

                    try {
                        String sqlss1 = "update sach set SoLuong = SoLuong + ? where MaSach=?";
                        connection = DbManager.getInstance().getConnection();
                        PreparedStatement presach1 = connection.prepareStatement(sqlss1);
                        presach1.setInt(1, 1);
                        presach1.setString(2, MaSach);
                        int c = presach1.executeUpdate();
                        presach1.close();
                        connection.close();
                        if (c > 0) {
                            JOptionPane.showMessageDialog(null, "Cập nhật số lượng sách thành công");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    // tru date
                    String date1 = NgayHenTra;
                    String date2 = datetra;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = null;
                    Date d2 = null;
                    long diffDays = 0;
                    try {
                        d1 = format.parse(date1);
                        d2 = format.parse(date2);
                        //in milliseconds
                        long diff = d2.getTime() - d1.getTime();
                        diffDays = diff / (24 * 60 * 60 * 1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //
                    if (hieu > 0 && diffDays <= 0) {
                        JOptionPane.showMessageDialog(null, "Bạn làm hao tổn sách: " + hieu + "%. Bạn bị phạt: " + hieu * 1000 + " VND");
                    }
                    if (hieu > 0 && diffDays > 0) {
                        JOptionPane.showMessageDialog(null, "Bạn làm hao tổn sách: " + hieu + "%. Và bạn trễ hạn: " + diffDays + " ngày. Bạn bị phạt: " + (hieu * 1000L + diffDays * 10000));
                    }
                    if (hieu == 0 && diffDays > 0) {
                        JOptionPane.showMessageDialog(null, "Bạn trễ hạn " + diffDays + " ngày. Bạn bị phạt: " + diffDays * 10000);
                    }

                    dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });

    }

    @Override
    public void initComponents() {
        JPanel pnHienThiTraSach = new JPanel();
        pnHienThiTraSach.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiTraSach, BorderLayout.CENTER);

        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("sachmn.png"));
        pnHinhAnh.add(lblHinhAnh);
        pnHienThiTraSach.add(pnHinhAnh, BorderLayout.WEST);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnHienThiTraSach.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblTraSach = new JLabel("TRẢ SÁCH");
        pnTitle.add(lblTraSach);
        pnHienThiChiTiet.add(pnTitle);

        JPanel pnMaPhieu = new JPanel();
        pnMaPhieu.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnMaPhieu);
        JLabel lblMaPhieu = new JLabel("Mã phiếu: ");
        txtMaPhieu = new JTextField();
        txtMaPhieu.setPreferredSize(new Dimension(340, 30));
        pnMaPhieu.add(lblMaPhieu);
        pnMaPhieu.add(txtMaPhieu);

        JPanel pnMaDG = new JPanel();
        pnMaDG.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnMaDG);
        JLabel lblMaDG = new JLabel("Mã độc giả: ");
        pnMaDG.add(lblMaDG);
        txtMaDG = new JTextField();
        txtMaDG.setPreferredSize(new Dimension(340, 30));
        pnMaDG.add(txtMaDG);

        JPanel pnMaSach = new JPanel();
        pnMaSach.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnMaSach);
        JLabel lblMaSach = new JLabel("Mã sách: ");
        pnMaSach.add(lblMaSach);
        txtMaSach = new JTextField();
        txtMaSach.setPreferredSize(new Dimension(340, 30));
        pnMaSach.add(txtMaSach);

        JPanel pnNgayHenTra = new JPanel();
        pnNgayHenTra.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnNgayHenTra);
        JLabel lblNgayHenTra = new JLabel("Ngày hẹn trả: ");
        txtNgayHenTra = new JTextField();
        txtNgayHenTra.setPreferredSize(new Dimension(340, 30));
        pnNgayHenTra.add(lblNgayHenTra);
        pnNgayHenTra.add(txtNgayHenTra);

        JPanel pnNgayTra = new JPanel();
        pnNgayTra.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnNgayTra);
        JLabel lblNgayTra = new JLabel("Ngày trả: ");
        choosedate = new JDateChooser();
        choosedate.setPreferredSize(new Dimension(340, 30));
        choosedate.setDateFormatString("yyyy-MM-dd");
        choosedate.setDate(new Date());
        pnNgayTra.add(lblNgayTra);
        pnNgayTra.add(choosedate);

        JPanel pnTinhTrangSach = new JPanel();
        pnTinhTrangSach.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTinhTrangSach);
        JLabel lblTinhTrangSach = new JLabel("TT sách mượn: ");
        txtTTSachMuon = new JTextField();
        txtTTSachMuon.setPreferredSize(new Dimension(340, 30));
        pnTinhTrangSach.add(lblTinhTrangSach);
        pnTinhTrangSach.add(txtTTSachMuon);

        JPanel pnTinhTrangTra = new JPanel();
        pnTinhTrangTra.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTinhTrangTra);
        JLabel lblTinhTrangTra = new JLabel("TT sách trả: ");
        txtTTSachTra = new JTextField();
        txtTTSachTra.setPreferredSize(new Dimension(340, 30));
        pnTinhTrangTra.add(lblTinhTrangTra);
        pnTinhTrangTra.add(txtTTSachTra);

        JPanel pnThuThuNhanSach = new JPanel();
        pnThuThuNhanSach.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnThuThuNhanSach);
        JLabel lblThuThuNhanSach = new JLabel("Thủ thư nhận sách: ");
        txtThuThuNhanSach = new JTextField();
        txtThuThuNhanSach.setPreferredSize(new Dimension(340, 30));
        pnThuThuNhanSach.add(lblThuThuNhanSach);
        pnThuThuNhanSach.add(txtThuThuNhanSach);

        JPanel pnGhiChu = new JPanel();
        pnGhiChu.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnGhiChu);
        JLabel lblGhiChu = new JLabel("Ghi chú: ");
        txtGhiChu = new JTextField();
        txtGhiChu.setPreferredSize(new Dimension(340, 30));
        pnGhiChu.add(lblGhiChu);
        pnGhiChu.add(txtGhiChu);


        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 30);
        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.TRUETYPE_FONT, 15);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);

        lblMaDG.setFont(font4);
        lblMaPhieu.setFont(font4);
        lblMaSach.setFont(font4);
        lblNgayTra.setFont(font4);
        lblNgayHenTra.setFont(font4);
        lblTinhTrangSach.setFont(font4);
        lblTinhTrangTra.setFont(font4);
        lblThuThuNhanSach.setFont(font4);
        lblGhiChu.setFont(font4);
        lblTraSach.setFont(font2);

        txtMaDG.setFont(font4);
        txtMaPhieu.setFont(font4);
        txtMaSach.setFont(font4);
        txtNgayHenTra.setFont(font4);
        choosedate.setFont(font4);
        txtTTSachMuon.setFont(font4);
        txtTTSachTra.setFont(font4);
        txtThuThuNhanSach.setFont(font4);
        txtGhiChu.setFont(font4);
        txtMaPhieu.setEditable(false);

        pnTitle.setBackground(new Color(241, 242, 246));
        lblTraSach.setForeground(new Color(48, 51, 107));
        pnMaDG.setBackground(new Color(241, 242, 246));
        pnMaPhieu.setBackground(new Color(241, 242, 246));
        pnMaSach.setBackground(new Color(241, 242, 246));
        pnNgayTra.setBackground(new Color(241, 242, 246));
        pnNgayHenTra.setBackground(new Color(241, 242, 246));
        pnTinhTrangSach.setBackground(new Color(241, 242, 246));
        pnTinhTrangTra.setBackground(new Color(241, 242, 246));
        pnThuThuNhanSach.setBackground(new Color(241, 242, 246));
        pnGhiChu.setBackground(new Color(241, 242, 246));
        pnHinhAnh.setBackground(new Color(241, 242, 246));

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnThaoTac);
        btnTraSach = new JButton("TRẢ SÁCH");
        btnTraSach.setPreferredSize(new Dimension(110, 35));
        pnThaoTac.add(btnTraSach);
        pnThaoTac.setBackground(new Color(241, 242, 246));

        btnTraSach.setFont(font5);

        btnTraSach.setBackground(new Color(4, 191, 138));
        btnTraSach.setForeground(Color.white);
        btnTraSach.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));


        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnHienThiTraSach.setBorder(titleLogin);

        lblMaDG.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblMaPhieu.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblMaSach.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblNgayHenTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblNgayTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblGhiChu.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblTinhTrangSach.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblTinhTrangTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());

        txtMaDG.setEditable(false);
        txtMaPhieu.setEditable(false);
        txtMaSach.setEditable(false);
        txtNgayHenTra.setEnabled(false);
        txtThuThuNhanSach.setEditable(false);
        txtTTSachMuon.setEditable(false);

    }

    public void showWindow() {
        this.setSize(940, 580);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

}
