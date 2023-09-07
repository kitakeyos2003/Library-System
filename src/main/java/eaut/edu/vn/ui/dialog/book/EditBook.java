package eaut.edu.vn.ui.dialog.book;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.util.Util;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class EditBook extends Dialog {
    public String ma = "";
    JTextField txtMaSach, txtTenSach, txtTenTG, txtNhaXB, txtTheLoai, txtSoLuong, txtGia;
    JButton btnSua;
    Connection conn = ConnectMySQL.connect;

    public EditBook(String title) {
        super(title);
        setHeader(new Header("QUẢN LÝ SÁCH"));
        setFooter(new Footer());
        hienThi();
    }

    public void hienThi() {
        try {
            String sql = "select * from sach where masach=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ma);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String tg = rs.getString(3);
                String nxb = rs.getString(4);
                String tl = rs.getString(5);
                String sl = String.valueOf(rs.getInt(6));
                String gia = String.valueOf(rs.getInt(7));

                txtMaSach.setText(ma);
                txtTenSach.setText(ten);
                txtTenTG.setText(tg);
                txtNhaXB.setText(nxb);
                txtTheLoai.setText(tl);
                txtSoLuong.setText(sl);
                txtGia.setText(gia);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addEvents() {
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ma = txtMaSach.getText();
                try {

                    String sql = "update sach set masach=?, tensach=?, tentg=?, nhaxb=?, theloai=?, soluong=?, giatien=? where masach=?";
                    PreparedStatement pre = conn.prepareStatement(sql);
                    pre.setString(1, txtMaSach.getText());
                    pre.setString(2, txtTenSach.getText());
                    pre.setString(3, txtTenTG.getText());
                    pre.setString(4, txtNhaXB.getText());
                    pre.setString(5, txtTheLoai.getText());
                    pre.setInt(6, Integer.parseInt(txtSoLuong.getText()));
                    pre.setInt(7, Integer.parseInt(txtGia.getText()));
                    pre.setString(8, txtMaSach.getText());
                    int x = pre.executeUpdate();

                    if (x > 0) {
                        JOptionPane.showMessageDialog(null, "Sửa thành công");
                        dispose();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    @Override
    public void initComponents() {JPanel pnHienThiSuaSach = new JPanel();
        pnHienThiSuaSach.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiSuaSach, BorderLayout.CENTER);

        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("like.png"));
        pnHinhAnh.add(lblHinhAnh);
        pnHienThiSuaSach.add(pnHinhAnh, BorderLayout.WEST);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnHienThiSuaSach.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblSuaSach = new JLabel("SỬA THÔNG TIN SÁCH");
        pnTitle.add(lblSuaSach);

        JPanel pnMaSach = new JPanel();
        pnMaSach.setLayout(new FlowLayout());
        JLabel lblMaSach = new JLabel("Mã sách: ");
        txtMaSach = new JTextField();
        txtMaSach.setPreferredSize(new Dimension(340, 30));
        pnMaSach.add(lblMaSach);
        pnMaSach.add(txtMaSach);

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
        pnHienThiChiTiet.add(pnMaSach);
        pnHienThiChiTiet.add(pnTenSach);
        pnHienThiChiTiet.add(pnTenTG);
        pnHienThiChiTiet.add(pnSoLuong);
        pnHienThiChiTiet.add(pnTheLoai);
        pnHienThiChiTiet.add(pnNhaXB);
        pnHienThiChiTiet.add(pnGia);

        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 30);
        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.TRUETYPE_FONT, 15);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblSuaSach.setFont(font2);
        lblGia.setFont(font4);
        lblMaSach.setFont(font4);
        lblTenSach.setFont(font4);
        lblTenTG.setFont(font4);
        lblNhaXB.setFont(font4);
        lblSoLuong.setFont(font4);
        lblTheLoai.setFont(font4);

        txtMaSach.setFont(font4);
        txtTenSach.setFont(font4);
        txtTenTG.setFont(font4);
        txtNhaXB.setFont(font4);
        txtSoLuong.setFont(font4);
        txtTheLoai.setFont(font4);
        txtGia.setFont(font4);
        txtMaSach.setEditable(false);

        pnTitle.setBackground(new Color(241, 242, 246));
        lblSuaSach.setForeground(new Color(48, 51, 107));
        pnMaSach.setBackground(new Color(241, 242, 246));
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
        pnHienThiSuaSach.setBorder(titleLogin);

        lblMaSach.setPreferredSize(lblNhaXB.getPreferredSize());
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
