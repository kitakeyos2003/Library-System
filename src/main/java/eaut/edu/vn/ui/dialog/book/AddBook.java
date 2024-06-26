package eaut.edu.vn.ui.dialog.book;

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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.service.BookService;
import eaut.edu.vn.model.Book;
import eaut.edu.vn.util.Util;

public class AddBook extends Dialog {
    public String ma = "";
    JTextField txtTenSach, txtTenTG, txtNhaXB, txtTheLoai, txtSoLuong, txtGia;
    JButton btnThem;

    public AddBook(String title) {
        super(title, "QUẢN LÝ SÁCH");
    }

    @Override
    public void addEvents() {
        btnThem.addActionListener(e -> {

            try {

                String sql = "INSERT INTO `sach`(`TenSach`, `TenTG`, `NhaXB`, `TheLoai`, `SoLuong`, `GiaTien`) VALUES (?,?,?,?,?,?)";
                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1, txtTenSach.getText());
                pre.setString(2, txtTenTG.getText());
                pre.setString(3, txtNhaXB.getText());
                pre.setString(4, txtTheLoai.getText());
                pre.setInt(5, Integer.parseInt(txtSoLuong.getText()));
                pre.setInt(6, Integer.parseInt(txtGia.getText()));
                int x = pre.executeUpdate();
                pre.close();
                connection.close();
                if (x > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    @Override
    public void initComponents() {
        JPanel pnHienThiThemSach = new JPanel();
        pnHienThiThemSach.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiThemSach, BorderLayout.CENTER);

        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("book.png"));
        pnHinhAnh.add(lblHinhAnh);
        pnHienThiThemSach.add(pnHinhAnh, BorderLayout.WEST);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnHienThiThemSach.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblThemSach = new JLabel("THÊM SÁCH");
        pnTitle.add(lblThemSach);



        JPanel pnTenSach = new JPanel();
        pnTenSach.setLayout(new FlowLayout());
        JLabel lblTenSach = new JLabel("Tên sách: ");
        txtTenSach = new JTextField();
        txtTenSach.setPreferredSize(new Dimension(340, 30));
        pnTenSach.add(lblTenSach);
        pnTenSach.add(txtTenSach);

        JPanel pnTenTG = new JPanel();
        pnTenTG.setLayout(new FlowLayout());
        JLabel lblTenTG = new JLabel("Tên tác giả: ");
        txtTenTG = new JTextField();
        txtTenTG.setPreferredSize(new Dimension(340, 30));
        pnTenTG.add(lblTenTG);
        pnTenTG.add(txtTenTG);

        JPanel pnNhaXB = new JPanel();
        pnNhaXB.setLayout(new FlowLayout());
        JLabel lblNhaXB = new JLabel("Nhà xuất bản: ");
        txtNhaXB = new JTextField();
        txtNhaXB.setPreferredSize(new Dimension(340, 30));
        pnNhaXB.add(lblNhaXB);
        pnNhaXB.add(txtNhaXB);

        JPanel pnTheLoai = new JPanel();
        pnTheLoai.setLayout(new FlowLayout());
        JLabel lblTheLoai = new JLabel("Thể loại: ");
        txtTheLoai = new JTextField();
        txtTheLoai.setPreferredSize(new Dimension(340, 30));
        pnTheLoai.add(lblTheLoai);
        pnTheLoai.add(txtTheLoai);

        JPanel pnSoLuong = new JPanel();
        pnSoLuong.setLayout(new FlowLayout());
        JLabel lblSoLuong = new JLabel("Số lượng: ");
        txtSoLuong = new JTextField();
        txtSoLuong.setPreferredSize(new Dimension(340, 30));
        pnSoLuong.add(lblSoLuong);
        pnSoLuong.add(txtSoLuong);

        JPanel pnGia = new JPanel();
        pnGia.setLayout(new FlowLayout());
        JLabel lblGia = new JLabel("Giá tiền: ");
        txtGia = new JTextField();
        txtGia.setPreferredSize(new Dimension(340, 30));
        pnGia.add(lblGia);
        pnGia.add(txtGia);


        pnHienThiChiTiet.add(pnTitle);
        pnHienThiChiTiet.add(pnTenSach);
        pnHienThiChiTiet.add(pnTenTG);
        pnHienThiChiTiet.add(pnNhaXB);
        pnHienThiChiTiet.add(pnTheLoai);
        pnHienThiChiTiet.add(pnSoLuong);
        pnHienThiChiTiet.add(pnGia);

        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 30);
        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.TRUETYPE_FONT, 15);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblThemSach.setFont(font2);
        lblGia.setFont(font4);
        lblTenSach.setFont(font4);
        lblTenTG.setFont(font4);
        lblNhaXB.setFont(font4);
        lblSoLuong.setFont(font4);
        lblTheLoai.setFont(font4);

        txtTenSach.setFont(font4);
        txtTenTG.setFont(font4);
        txtNhaXB.setFont(font4);
        txtSoLuong.setFont(font4);
        txtTheLoai.setFont(font4);
        txtGia.setFont(font4);

        pnTitle.setBackground(new Color(241, 242, 246));
        lblThemSach.setForeground(new Color(48, 51, 107));
        pnTenSach.setBackground(new Color(241, 242, 246));
        pnTenTG.setBackground(new Color(241, 242, 246));
        pnNhaXB.setBackground(new Color(241, 242, 246));
        pnSoLuong.setBackground(new Color(241, 242, 246));
        pnTheLoai.setBackground(new Color(241, 242, 246));
        pnGia.setBackground(new Color(241, 242, 246));
        pnHinhAnh.setBackground(new Color(241, 242, 246));

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


        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnHienThiThemSach.setBorder(titleLogin);

        lblTenSach.setPreferredSize(lblNhaXB.getPreferredSize());
        lblTenTG.setPreferredSize(lblNhaXB.getPreferredSize());
        lblSoLuong.setPreferredSize(lblNhaXB.getPreferredSize());
        lblTheLoai.setPreferredSize(lblNhaXB.getPreferredSize());
        lblGia.setPreferredSize(lblNhaXB.getPreferredSize());
    }

    public void showWindow() {
        this.setSize(900, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

}
