package eaut.edu.vn.ui.dialog.reader;

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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.service.ReaderService;
import eaut.edu.vn.model.Reader;
import eaut.edu.vn.util.Util;

public class Them extends Dialog {
    public String tentk = "";
    JTextField txtMaDocGia, txtHoTen, txtSDT, txtDiaChi, txtGioiTinh;
    JRadioButton radNam, radNu;
    JButton btnThem;
    JComboBox cb;
    Connection connect = ConnectMySQL.connect;

    public Them(String title) {
        super(title);
    }

    public int DemDocGia() {
        int SoLuongDG = 0;
        ReaderService dgsv = new ReaderService();
        ArrayList<Reader> ds = dgsv.layToanBoDocGia();
        for (Reader dg : ds) {
            SoLuongDG++;
        }
        return SoLuongDG;
    }

    @Override
    protected void addEvents() {
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int flag = 1;
                try {

                    String sql = "select * from docgia where madg=?";
                    PreparedStatement pre = connect.prepareStatement(sql);
                    pre.setString(1, txtMaDocGia.getText());
                    ResultSet rs = pre.executeQuery();

                    if (rs.next()) {
                        flag = 0;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (flag == 0) {
                    JOptionPane.showMessageDialog(null, "Mã đọc giả trùng");
                    //dispose();
                    return;
                }

                if (txtMaDocGia.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Mã đọc giả không được để trống");
                    //dispose();
                    return;
                }

                try {

                    String sql = "insert into docgia values (?,?,?,?,?,0)";
                    PreparedStatement pre = connect.prepareStatement(sql);
                    pre.setString(1, txtMaDocGia.getText());
                    pre.setString(2, txtHoTen.getText());
                    pre.setString(3, txtSDT.getText());
                    pre.setString(4, txtDiaChi.getText());
                    pre.setString(5, (String) cb.getSelectedItem());
                    int x = pre.executeUpdate();

                    if (x > 0) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
                        //dispose();
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void initComponents() {
        int kqdg = DemDocGia() + 1;
        Container con = getContentPane();

        JPanel pnThemDocGia = new JPanel();
        pnThemDocGia.setLayout(new BorderLayout());
        con.add(pnThemDocGia);

        JPanel pnTieuDe = new JPanel();
        JLabel lblTieuDe = new JLabel("QUẢN LÝ ĐỘC GIẢ");
        pnTieuDe.add(lblTieuDe);
        pnThemDocGia.add(pnTieuDe, BorderLayout.NORTH);

        JPanel pnLienHe = new JPanel();
        JLabel lblLienHe = new JLabel("THÔNG TIN TRỢ GIÚP - LIÊN HỆ: 0342565857");
        pnLienHe.add(lblLienHe);
        pnThemDocGia.add(pnLienHe, BorderLayout.SOUTH);

        JPanel pnHienThiThemDocGia = new JPanel();
        pnHienThiThemDocGia.setLayout(new BorderLayout());
        pnThemDocGia.add(pnHienThiThemDocGia, BorderLayout.CENTER);


        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("themnd.png"));
        pnHinhAnh.add(lblHinhAnh);
        pnHienThiThemDocGia.add(pnHinhAnh, BorderLayout.WEST);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnHienThiThemDocGia.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblThemDocGia = new JLabel("THÊM ĐỘC GIẢ");
        pnTitle.add(lblThemDocGia);

        JPanel pnMaDG = new JPanel();
        pnMaDG.setLayout(new FlowLayout());
        JLabel lblMaDG = new JLabel("Mã độc giả: ");
        txtMaDocGia = new JTextField("DG" + kqdg);
        txtMaDocGia.setPreferredSize(new Dimension(340, 30));
        pnMaDG.add(lblMaDG);
        pnMaDG.add(txtMaDocGia);
        txtMaDocGia.setEditable(false);

        JPanel pnHoTen = new JPanel();
        pnHoTen.setLayout(new FlowLayout());
        JLabel lblHoTen = new JLabel("Tên độc giả: ");
        txtHoTen = new JTextField();
        txtHoTen.setPreferredSize(new Dimension(340, 30));
        pnHoTen.add(lblHoTen);
        pnHoTen.add(txtHoTen);

        JPanel pnSoDienThoai = new JPanel();
        pnSoDienThoai.setLayout(new FlowLayout());
        JLabel lblSoDienThoai = new JLabel("Số điện thoại: ");
        txtSDT = new JTextField();
        txtSDT.setPreferredSize(new Dimension(340, 30));
        pnSoDienThoai.add(lblSoDienThoai);
        pnSoDienThoai.add(txtSDT);

        JPanel pnDiaChi = new JPanel();
        pnDiaChi.setLayout(new FlowLayout());
        JLabel lblDiaChi = new JLabel("Địa chỉ: ");
        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(new Dimension(340, 30));
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);

        JPanel pnGioiTinh = new JPanel();
        pnGioiTinh.setLayout(new FlowLayout());
        JLabel lblGioiTinh = new JLabel("Giới tính: ");
        txtGioiTinh = new JTextField();
        txtGioiTinh.setPreferredSize(new Dimension(340, 30));
        cb = new JComboBox();
        cb.addItem("Nam");
        cb.addItem("Nữ");
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(cb);


        pnHienThiChiTiet.add(pnTitle);
        pnHienThiChiTiet.add(pnMaDG);
        pnHienThiChiTiet.add(pnHoTen);
        pnHienThiChiTiet.add(pnSoDienThoai);
        pnHienThiChiTiet.add(pnDiaChi);
        pnHienThiChiTiet.add(pnGioiTinh);


        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 30);
        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.TRUETYPE_FONT, 15);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblTieuDe.setFont(font1);
        lblThemDocGia.setFont(font2);
        lblMaDG.setFont(font4);
        lblSoDienThoai.setFont(font4);
        lblDiaChi.setFont(font4);
        lblHoTen.setFont(font4);
        lblGioiTinh.setFont(font4);
        lblLienHe.setFont(font4);
        txtMaDocGia.setFont(font4);
        txtDiaChi.setFont(font4);
        txtHoTen.setFont(font4);
        txtSDT.setFont(font4);
        txtGioiTinh.setFont(font4);
        cb.setFont(font4);

        pnTieuDe.setBackground(new Color(48, 51, 107));
        lblTieuDe.setForeground(Color.WHITE);
        pnLienHe.setBackground(new Color(48, 51, 107));
        lblLienHe.setForeground(Color.WHITE);

        pnTitle.setBackground(new Color(241, 242, 246));
        lblThemDocGia.setForeground(new Color(48, 51, 107));
        pnMaDG.setBackground(new Color(241, 242, 246));
        pnDiaChi.setBackground(new Color(241, 242, 246));
        pnGioiTinh.setBackground(new Color(241, 242, 246));
        pnSoDienThoai.setBackground(new Color(241, 242, 246));
        pnHoTen.setBackground(new Color(241, 242, 246));
        pnHinhAnh.setBackground(new Color(241, 242, 246));
        cb.setBackground(new Color(241, 242, 246));
        cb.setForeground(Color.BLACK);


        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnThaoTac);
        btnThem = new JButton("THÊM");
        btnThem.setPreferredSize(new Dimension(110, 35));
        pnThaoTac.add(btnThem);
        pnThaoTac.setBackground(new Color(241, 242, 246));

        btnThem.setFont(font5);

        btnThem.setBackground(new Color(255, 177, 66));
        btnThem.setForeground(Color.white);
        btnThem.setBorder(BorderFactory.createLineBorder(new Color(255, 177, 66)));
        //btnLuu.setBackground(new java.awt.Color(5, 196, 107));


        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnHienThiThemDocGia.setBorder(titleLogin);

        lblMaDG.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblHoTen.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblDiaChi.setPreferredSize(lblSoDienThoai.getPreferredSize());
        cb.setPreferredSize(txtDiaChi.getPreferredSize());


    }

    public void showWindow() {
        this.setSize(900, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

}
