package eaut.edu.vn.ui.dialog.loan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

public class EditLoan extends Dialog {
    public String maPM = "";
    JTextField txtMaPhieu, txtMaDG, txtTenDG, txtNgayMuon, txtNgayHenTra, txtSachMuon, txtThuThu;
    JButton btnSua;
    int soluongtruoc = 0;
    int soluongsau = 0;
    JDateChooser choosedate, choosedate1;

    public EditLoan(String title) {
        super(title, "QUẢN LÝ PHIẾU MƯỢN");
    }

    public void fillInfo() {
        try {
            String sql = "select * from phieumuon where MaPM=?";
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, maPM);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                txtMaPhieu.setText(rs.getString(1));
                txtMaDG.setText(rs.getString(2));
                choosedate.setDate(rs.getDate(3));
                choosedate1.setDate(rs.getDate(4));
                txtSachMuon.setText(rs.getString(5));
                soluongtruoc = rs.getInt(5);
                txtThuThu.setText(rs.getString(6));
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
        btnSua.addActionListener(e -> {
            // TODO Auto-generated method stub
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String datemuon = df.format(choosedate.getDate());
            String datehantra = df.format(choosedate1.getDate());
            try {
                String sql = "update phieumuon set MaDg=?,NgayMuon=?,NgayHenTra=?,SoLuongMuon=?,User=? where MaPM=?";
                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pre.setString(1, txtMaDG.getText());
                pre.setString(2, datemuon);
                pre.setString(3, datehantra);
                pre.setString(4, txtSachMuon.getText());
                soluongsau = Integer.parseInt(txtSachMuon.getText());
                pre.setString(5, txtThuThu.getText());
                pre.setString(6, txtMaPhieu.getText());
                int x = pre.executeUpdate();
                int genId = -1;
                if (x > 0) {
                    ResultSet generatedKeys = pre.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        genId = generatedKeys.getInt(1);
                    }
                    generatedKeys.close();
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    dispose();
                }
                pre.close();
                connection.close();
                if (soluongtruoc < soluongsau) {
                    for (int i = 0; i < (soluongsau - soluongtruoc); i++) {
                        BookBorrowStatus themsach = new BookBorrowStatus("Mượn tiếp sách");
                        themsach.MaPM = genId;
                        themsach.fill();
                        themsach.showWindow();
                    }

                }
                if (soluongtruoc > soluongsau) {
                    for (int i = 0; i < (soluongtruoc - soluongsau); i++) {
                        UpdateBorrowedBookStatus xoa = new UpdateBorrowedBookStatus("Xóa bớt sách");
                        xoa.MaPM = maPM;
                        xoa.loadInfo();
                        xoa.showWindow();
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
    }

    @Override
    public void initComponents() {JPanel pnHienThiThemPM = new JPanel();
        pnHienThiThemPM.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiThemPM, BorderLayout.CENTER);

        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("qlpm.png"));
        pnHinhAnh.add(lblHinhAnh);
        pnHienThiThemPM.add(pnHinhAnh, BorderLayout.WEST);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnHienThiThemPM.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblThemPM = new JLabel("SỬA THÔNG TIN");
        pnTitle.add(lblThemPM);

        JPanel pnMaPM = new JPanel();
        pnMaPM.setLayout(new FlowLayout());
        JLabel lblMaPM = new JLabel("Mã phiếu: ");
        txtMaPhieu = new JTextField();
        txtMaPhieu.setPreferredSize(new Dimension(340, 30));
        pnMaPM.add(lblMaPM);
        pnMaPM.add(txtMaPhieu);

        JPanel pnMaDG = new JPanel();
        pnMaDG.setLayout(new FlowLayout());
        JLabel lblMaDG = new JLabel("Mã độc giả: ");
        txtMaDG = new JTextField();
        txtMaDG.setPreferredSize(new Dimension(340, 30));
        pnMaDG.add(lblMaDG);
        pnMaDG.add(txtMaDG);

        JPanel pnTenDG = new JPanel();
        pnTenDG.setLayout(new FlowLayout());
        JLabel lblTenDG = new JLabel("Tên độc giả: ");
        txtTenDG = new JTextField();
        txtTenDG.setPreferredSize(new Dimension(340, 30));
        pnTenDG.add(lblTenDG);
        pnTenDG.add(txtTenDG);

        JPanel pnNgayMuon = new JPanel();
        pnNgayMuon.setLayout(new FlowLayout());
        JLabel lblNgayMuon = new JLabel("Ngày mượn: ");
        choosedate = new JDateChooser();
        choosedate.setPreferredSize(new Dimension(340, 30));
        choosedate.setDateFormatString("yyyy-MM-dd");
        pnNgayMuon.add(lblNgayMuon);
        pnNgayMuon.add(choosedate);

        JPanel pnNgayHenTra = new JPanel();
        pnNgayHenTra.setLayout(new FlowLayout());
        JLabel lblNgayHenTra = new JLabel("Ngày hẹn trả: ");
        choosedate1 = new JDateChooser();
        choosedate1.setPreferredSize(new Dimension(340, 30));
        choosedate1.setDateFormatString("yyyy-MM-dd");
        pnNgayHenTra.add(lblNgayHenTra);
        pnNgayHenTra.add(choosedate1);

        JPanel pnSoSachCM = new JPanel();
        pnSoSachCM.setLayout(new FlowLayout());
        JLabel lblSoSachCM = new JLabel("Số sách mượn: ");
        txtSachMuon = new JTextField();
        txtSachMuon.setPreferredSize(new Dimension(340, 30));
        pnSoSachCM.add(lblSoSachCM);
        pnSoSachCM.add(txtSachMuon);

        JPanel pnThuThu = new JPanel();
        pnThuThu.setLayout(new FlowLayout());
        JLabel lblThuThu = new JLabel("Thủ thư: ");
        txtThuThu = new JTextField();
        txtThuThu.setPreferredSize(new Dimension(340, 30));
        pnThuThu.add(lblThuThu);
        pnThuThu.add(txtThuThu);


        pnHienThiChiTiet.add(pnTitle);
        pnHienThiChiTiet.add(pnMaPM);
        pnHienThiChiTiet.add(pnMaDG);
        //pnHienThiChiTiet.add(pnTenDG);
        pnHienThiChiTiet.add(pnNgayMuon);
        pnHienThiChiTiet.add(pnNgayHenTra);
        pnHienThiChiTiet.add(pnSoSachCM);
        pnHienThiChiTiet.add(pnThuThu);

        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 30);
        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.TRUETYPE_FONT, 15);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);

        lblThemPM.setFont(font2);
        lblMaPM.setFont(font4);
        lblMaDG.setFont(font4);
        lblTenDG.setFont(font4);
        lblNgayMuon.setFont(font4);
        lblNgayHenTra.setFont(font4);
        lblSoSachCM.setFont(font4);
        lblThuThu.setFont(font4);

        txtMaDG.setFont(font4);
        txtMaPhieu.setFont(font4);
        txtTenDG.setFont(font4);
        choosedate.setFont(font4);
        choosedate1.setFont(font4);
        txtSachMuon.setFont(font4);
        txtThuThu.setFont(font4);
        txtMaPhieu.setEditable(false);
        txtThuThu.setEditable(false);

        pnTitle.setBackground(new Color(241, 242, 246));
        lblThemPM.setForeground(new Color(48, 51, 107));
        pnMaPM.setBackground(new Color(241, 242, 246));
        pnMaDG.setBackground(new Color(241, 242, 246));
        pnTenDG.setBackground(new Color(241, 242, 246));
        pnNgayMuon.setBackground(new Color(241, 242, 246));
        pnNgayHenTra.setBackground(new Color(241, 242, 246));
        pnThuThu.setBackground(new Color(241, 242, 246));
        pnSoSachCM.setBackground(new Color(241, 242, 246));
        pnHinhAnh.setBackground(new Color(241, 242, 246));

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnThaoTac);
        btnSua = new JButton("LƯU");
        btnSua.setPreferredSize(new Dimension(110, 35));
        pnThaoTac.add(btnSua);
        pnThaoTac.setBackground(new Color(241, 242, 246));

        btnSua.setFont(font5);

        btnSua.setBackground(new Color(4, 191, 138));
        btnSua.setForeground(Color.white);
        btnSua.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));


        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnHienThiThemPM.setBorder(titleLogin);

        lblMaDG.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblMaPM.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblTenDG.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblNgayHenTra.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblNgayMuon.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblThuThu.setPreferredSize(lblSoSachCM.getPreferredSize());
    }

    public void showWindow() {
        this.setSize(900, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

}
