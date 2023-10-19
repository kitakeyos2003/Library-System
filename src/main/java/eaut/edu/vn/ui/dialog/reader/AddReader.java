package eaut.edu.vn.ui.dialog.reader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.service.ReaderService;
import eaut.edu.vn.model.Reader;
import eaut.edu.vn.util.Util;

public class AddReader extends Dialog {
    
    JTextField txtHoTen, txtSDT, txtDiaChi, txtGioiTinh;
    JRadioButton radNam, radNu;
    JButton btnThem;
    JComboBox cb;

    public AddReader(String title) {
        super(title);
        setHeader(new Header("QUẢN LÝ ĐỘC GIẢ"));
        setFooter(new Footer());
    }

    @Override
    public void addEvents() {
        btnThem.addActionListener(e -> {
            try {

                String sql = "INSERT INTO `docgia`(`TenDG`, `SDT`, `DiaChi`, `GioiTinh`, `MatSach`) VALUES (?,?,?,?,0)";
                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1, txtHoTen.getText());
                pre.setString(2, txtSDT.getText());
                pre.setString(3, txtDiaChi.getText());
                pre.setString(4, (String) cb.getSelectedItem());
                int x = pre.executeUpdate();
                pre.close();
                connection.close();
                if (x > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    //dispose();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    @Override
    public void initComponents() {
        JPanel pnHienThiThemDocGia = new JPanel();
        pnHienThiThemDocGia.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiThemDocGia, BorderLayout.CENTER);


        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("user-info.png"));
        pnHinhAnh.add(lblHinhAnh);
        pnHienThiThemDocGia.add(pnHinhAnh, BorderLayout.WEST);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnHienThiThemDocGia.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblThemDocGia = new JLabel("THÊM ĐỘC GIẢ");
        pnTitle.add(lblThemDocGia);

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
        pnHienThiChiTiet.add(pnHoTen);
        pnHienThiChiTiet.add(pnSoDienThoai);
        pnHienThiChiTiet.add(pnDiaChi);
        pnHienThiChiTiet.add(pnGioiTinh);


        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 30);
        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.TRUETYPE_FONT, 15);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblThemDocGia.setFont(font2);
        lblSoDienThoai.setFont(font4);
        lblDiaChi.setFont(font4);
        lblHoTen.setFont(font4);
        lblGioiTinh.setFont(font4);
        txtDiaChi.setFont(font4);
        txtHoTen.setFont(font4);
        txtSDT.setFont(font4);
        txtGioiTinh.setFont(font4);
        cb.setFont(font4);

        pnTitle.setBackground(new Color(241, 242, 246));
        lblThemDocGia.setForeground(new Color(48, 51, 107));
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

        btnThem.setBackground(new Color(4, 191, 138));
        btnThem.setForeground(Color.white);
        btnThem.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));
        //btnLuu.setBackground(new java.awt.Color(5, 196, 107));


        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnHienThiThemDocGia.setBorder(titleLogin);

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
