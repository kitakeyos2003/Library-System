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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.main.Application;
import eaut.edu.vn.service.AccountService;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.CustomFrame;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.util.Util;

public class ChangePassword extends CustomFrame {

    JButton btnLuu, btnQuayLai, btnLamLai;
    JPasswordField pwdMatKhauCu, pwdMatKhauMoi, pwdNhapLaiMKM;


    public ChangePassword(String tieude) {
        super(tieude);
        this.setSize(780, 430);
        setHeader(new Header("PHẦN MỀM QUẢN LÝ THƯ VIỆN"));
        setFooter(new Footer());
    }

    @Override
    public void addEvents() {
        btnQuayLai.addActionListener(e -> {
            // TODO Auto-generated method stub
            int phanquyen = Application.account.getRole();
            if (phanquyen == 1) {
                AdminManager ql = Application.SINGLETON.ADMIN_MANAGER;
                ql.showWindow();
                dispose();
            }
            if (phanquyen == 2) {
                LibrarianManager ql = Application.SINGLETON.LIBRARIAN_MANAGER;
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
        btnLamLai.addActionListener(e -> {
            pwdMatKhauCu.setText(null);
            pwdMatKhauMoi.setText(null);
            pwdNhapLaiMKM.setText(null);

        });

        btnLuu.addActionListener(e -> {
            // TODO Auto-generated method stub
            Account account = Application.account;
            if (account == null || !account.getPassword().equals(pwdMatKhauCu.getText())) {
                JOptionPane.showMessageDialog(null, "Mật khẩu cũ chưa đúng. Mời nhập lại!");
                pwdMatKhauCu.setText(null);
                pwdMatKhauMoi.setText(null);
                pwdNhapLaiMKM.setText(null);
                return;
            }
            if (!pwdMatKhauMoi.getText().equals(pwdNhapLaiMKM.getText())) {
                JOptionPane.showMessageDialog(null, "Mật khẩu mới chưa trùng khớp");
                pwdMatKhauMoi.setText(null);
                pwdNhapLaiMKM.setText(null);
                return;
            }
            String username = account.getUsername();
            int x = DbManager.getInstance().update("Update taikhoan set password=? where user=?", pwdNhapLaiMKM.getText(), username);
            if (x > 0) {
                account.setPassword(pwdMatKhauMoi.getText());
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                pwdMatKhauCu.setText(null);
                pwdMatKhauMoi.setText(null);
                pwdNhapLaiMKM.setText(null);
            }
        });
    }

    @Override
    public void initComponents() {
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
        pnMatKhauCu.setBackground(new Color(241, 242, 246));
        pnMatKhauMoi.setBackground(new Color(241, 242, 246));
        pnNhapLaiMKM.setBackground(new Color(241, 242, 246));
        pnThaoTac.setBackground(new Color(241, 242, 246));
        pnHinhAnh.setBackground(new Color(241, 242, 246));

        btnLamLai.setBackground(new Color(4, 191, 138));
        btnLamLai.setForeground(Color.white);
        btnLamLai.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));
        btnQuayLai.setBackground(new Color(4, 191, 138));
        btnQuayLai.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));
        btnQuayLai.setForeground(Color.white);
        btnLuu.setBackground(new Color(4, 191, 138));
        btnLuu.setForeground(Color.white);
        btnLuu.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));


        btnLuu.setPreferredSize(btnQuayLai.getPreferredSize());
        btnLamLai.setPreferredSize(btnQuayLai.getPreferredSize());
        //lblMatKhau.setPreferredSize(lblTaiKhoan.getPreferredSize() ); */

        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnHienThiDoiMatKhau.setBorder(titleLogin);

    }

}
