package eaut.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.service.AccountService;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Frame;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.util.Util;

public class ChangePassword extends Frame {
    public String tentk = "";
    JButton btnLuu, btnQuayLai, btnLamLai;
    JPasswordField pwdMatKhauCu, pwdMatKhauMoi, pwdNhapLaiMKM;
    Connection connect = ConnectMySQL.connect;
    JTextField txtTaiKhoan, txtMatKhauCu, txtMatKhauMoi, txtNhapLaiMKM;
    Connection con = ConnectMySQL.connect;


    public ChangePassword(String tieude) {
        super(tieude);
        setHeader(new Header("PHẦN MỀM QUẢN LÝ THƯ VIỆN"));
        setFooter(new Footer());
        initComponents();
        addEvents();
        if (tentk.length() != 0) {
            HienThi();
        }
    }

    public void HienThi() {
        txtTaiKhoan.setText(tentk);
    }

    @Override
    public void addEvents() {
        btnQuayLai.addActionListener(e -> {
            // TODO Auto-generated method stub
            int phanquyen = 0;
            try {

                String sql = "select PhanQuyen from taikhoan where User=?";
                PreparedStatement pre = ConnectMySQL.connect.prepareStatement(sql);
                pre.setString(1, tentk);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    phanquyen = rs.getInt(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (phanquyen == 1) {
                AdminManager ql = new AdminManager("Admin");
                ql.tentk = tentk;
                ql.showWindow();
                dispose();
            }
            if (phanquyen == 2) {
                LibrarianManager ql = new LibrarianManager("Thủ thư: " + tentk);
                ql.tentk = tentk;
                ql.showWindow();
                dispose();
            }
        });

        pwdMatKhauCu.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                pwdMatKhauCu.setEchoChar('*');
                pwdMatKhauCu.setText(null);

            }
        });

        pwdMatKhauMoi.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                pwdMatKhauMoi.setEchoChar('*');
                pwdMatKhauMoi.setText(null);

            }
        });

        pwdNhapLaiMKM.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                pwdNhapLaiMKM.setEchoChar('*');
                pwdNhapLaiMKM.setText(null);

            }
        });
        btnLamLai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pwdMatKhauCu.setText(null);
                pwdMatKhauMoi.setText(null);
                pwdNhapLaiMKM.setText(null);

            }
        });

        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                AccountService tksv = new AccountService();
                ArrayList<Account> dstk = new ArrayList<Account>();
                dstk = tksv.layTaiKhoan();
                for (Account tk : dstk) {
                    if (txtTaiKhoan.getText().equals(tk.getUser()) && pwdMatKhauCu.getText().equals(tk.getPass())) {
                        if (pwdMatKhauMoi.getText().equals(pwdNhapLaiMKM.getText())) {
                            try {
                                String sql = "Update taikhoan set password=? where user=?";
                                PreparedStatement pre = con.prepareStatement(sql);
                                pre.setString(1, pwdNhapLaiMKM.getText());
                                pre.setString(2, txtTaiKhoan.getText());
                                int x = pre.executeUpdate();
                                if (x > 0) {
                                    JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                                    pwdMatKhauCu.setText(null);
                                    pwdMatKhauMoi.setText(null);
                                    pwdNhapLaiMKM.setText(null);
                                    return;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Mật khẩu mới chưa trùng khớp");
                            pwdMatKhauMoi.setText(null);
                            pwdNhapLaiMKM.setText(null);
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Mật khẩu cũ chưa đúng. Mời nhập lại!");
                pwdMatKhauCu.setText(null);
                pwdMatKhauMoi.setText(null);
                pwdNhapLaiMKM.setText(null);
            }

        });
    }

    @Override
    protected void initComponents() {
        JPanel pnHienThiDoiMatKhau = new JPanel();
        pnHienThiDoiMatKhau.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiDoiMatKhau, BorderLayout.CENTER);

        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("doimatkhau.png"));
        pnHinhAnh.add(lblHinhAnh);
        pnHienThiDoiMatKhau.add(pnHinhAnh, BorderLayout.WEST);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnHienThiDoiMatKhau.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblDoiMatKhau = new JLabel("ĐỔI MẬT KHẨU");
        pnTitle.add(lblDoiMatKhau);

        JPanel pnTaiKhoan = new JPanel();
        pnTaiKhoan.setLayout(new FlowLayout());
        txtTaiKhoan = new JTextField();
        txtTaiKhoan.setPreferredSize(new Dimension(340, 30));
        pnTaiKhoan.add(txtTaiKhoan);
        txtTaiKhoan.setEditable(false);

        JPanel pnMatKhauCu = new JPanel();
        pnMatKhauCu.setLayout(new FlowLayout());
        pwdMatKhauCu = new JPasswordField("Mật khẩu cũ");
        pwdMatKhauCu.setEchoChar((char) 0);
        pwdMatKhauCu.setPreferredSize(new Dimension(340, 30));
        pnMatKhauCu.add(pwdMatKhauCu);

        JPanel pnMatKhauMoi = new JPanel();
        pnMatKhauMoi.setLayout(new FlowLayout());
        pwdMatKhauMoi = new JPasswordField("Mật khẩu mới");
        pwdMatKhauMoi.setEchoChar((char) 0);
        pwdMatKhauMoi.setPreferredSize(new Dimension(340, 30));
        pnMatKhauMoi.add(pwdMatKhauMoi);

        JPanel pnNhapLaiMKM = new JPanel();
        pnNhapLaiMKM.setLayout(new FlowLayout());
        pwdNhapLaiMKM = new JPasswordField("Nhập lại mật khẩu mới");
        pwdNhapLaiMKM.setEchoChar((char) 0);
        pwdNhapLaiMKM.setPreferredSize(new Dimension(340, 30));
        pnNhapLaiMKM.add(pwdNhapLaiMKM);

        pnHienThiChiTiet.add(pnTitle);
        pnHienThiChiTiet.add(pnTaiKhoan);
        pnHienThiChiTiet.add(pnMatKhauCu);
        pnHienThiChiTiet.add(pnMatKhauMoi);
        pnHienThiChiTiet.add(pnNhapLaiMKM);

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnThaoTac);
        btnLuu = new JButton("LƯU");
        btnLamLai = new JButton("LÀM LẠI");
        btnQuayLai = new JButton("TRANG CHỦ");
        btnQuayLai.setPreferredSize(new Dimension(110, 35));
        pnThaoTac.add(btnLamLai);
        pnThaoTac.add(btnLuu);
        pnThaoTac.add(btnQuayLai);

        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 30);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblDoiMatKhau.setFont(font2);
        txtTaiKhoan.setFont(font4);
        pwdMatKhauCu.setFont(font4);
        pwdNhapLaiMKM.setFont(font4);
        pwdMatKhauMoi.setFont(font4);

        btnLamLai.setFont(font5);
        btnQuayLai.setFont(font5);
        btnLuu.setFont(font5);
		
		/*btnDangNhap.setIcon(ImageLoader.loadImage("/Hinh/lock.png"));
		btnThoat.setIcon(ImageLoader.loadImage("/Hinh/close.png"));  */

        pnTitle.setBackground(new Color(241, 242, 246));
        lblDoiMatKhau.setForeground(new Color(48, 51, 107));
        pnTaiKhoan.setBackground(new Color(241, 242, 246));
        pnMatKhauCu.setBackground(new Color(241, 242, 246));
        pnMatKhauMoi.setBackground(new Color(241, 242, 246));
        pnNhapLaiMKM.setBackground(new Color(241, 242, 246));
        pnThaoTac.setBackground(new Color(241, 242, 246));
        pnHinhAnh.setBackground(new Color(241, 242, 246));

        btnLamLai.setBackground(new Color(255, 177, 66));
        btnLamLai.setForeground(Color.white);
        btnLamLai.setBorder(BorderFactory.createLineBorder(new Color(255, 177, 66)));
        btnQuayLai.setBackground(new Color(255, 177, 66));
        btnQuayLai.setBorder(BorderFactory.createLineBorder(new Color(255, 177, 66)));
        btnQuayLai.setForeground(Color.white);
        btnLuu.setBackground(new Color(5, 196, 107));
        btnLuu.setForeground(Color.white);
        btnLuu.setBorder(BorderFactory.createLineBorder(new Color(5, 196, 107)));


        btnLuu.setPreferredSize(btnQuayLai.getPreferredSize());
        btnLamLai.setPreferredSize(btnQuayLai.getPreferredSize());
        //lblMatKhau.setPreferredSize(lblTaiKhoan.getPreferredSize() ); */

        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnHienThiDoiMatKhau.setBorder(titleLogin);

    }

    public void showWindow() {
        this.setSize(780, 430);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }


}
