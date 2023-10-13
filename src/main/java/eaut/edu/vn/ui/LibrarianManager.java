package eaut.edu.vn.ui;

import eaut.edu.vn.main.Application;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.CustomFrame;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.util.Util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LibrarianManager extends CustomFrame {
    JButton btnQLDG, btnQLPM, btnQLPT, btnQLS, btnDoiMK, btnDangXuat;

    public LibrarianManager(String title) {
        super(title);
        this.setSize(888, 600);
        setHeader(new Header("TRANG CHỦ: QUẢN LÝ THƯ VIỆN"));
        setFooter(new Footer());
    }

    @Override
    public void addEvents() {
        btnDangXuat.addActionListener(e -> {
            Application.account = null;
            Login login = Application.SINGLETON.LOGIN;
            login.showWindow();
            dispose();
        });
        btnQLPM.addActionListener(e -> {
            LoanManager qlpm = Application.SINGLETON.LOAN_MANAGER;
            qlpm.showWindow();
            dispose();
        });
        btnQLPT.addActionListener(e -> {
            ReturnManager qlpt = Application.SINGLETON.RETURN_MANAGER;
            qlpt.showWindow();
            dispose();

        });
        btnQLDG.addActionListener(e -> {
            ReaderManager qldg = Application.SINGLETON.READER_MANAGER;
            qldg.showWindow();
            dispose();

        });

        btnQLS.addActionListener(e -> {
            BookManager qls = Application.SINGLETON.BOOK_MANAGER;
            qls.showWindow();
            dispose();

        });
        btnDoiMK.addActionListener(e -> {
            ChangePassword dmk = new ChangePassword("Đổi mật khẩu");
            dmk.showWindow();
            dispose();

        });

    }

    @Override
    public void initComponents() {
        JPanel pnQuanLy = new JPanel();
        pnQuanLy.setLayout(new BoxLayout(pnQuanLy, BoxLayout.Y_AXIS));

        JPanel pnHang1 = new JPanel();
        pnHang1.setLayout(new FlowLayout());
        pnHang1.setBackground(Color.WHITE);
        btnQLS = new JButton();
        btnQLDG = new JButton();

        JPanel pnHang2 = new JPanel();
        pnHang2.setLayout(new FlowLayout());
        pnHang2.setBackground(Color.WHITE);
        btnQLPM = new JButton();
        btnQLPT = new JButton();

        JPanel pnHang3 = new JPanel();
        pnHang3.setLayout(new FlowLayout());
        pnHang3.setBackground(Color.WHITE);
        JLabel lblHinhAnh1 = new JLabel();
        lblHinhAnh1.setIcon(Util.loadImage("anhnen.png"));
        pnHang3.add(lblHinhAnh1);

        btnQLS.setPreferredSize(new Dimension(270, 150));
        btnQLPM.setPreferredSize(btnQLS.getPreferredSize());
        btnQLDG.setPreferredSize(btnQLS.getPreferredSize());
        btnQLPT.setPreferredSize(btnQLS.getPreferredSize());

//        btnQLS.setBackground(new Color(51, 217, 178));
//        btnQLPM.setBackground(new Color(37, 204, 247));
//        btnQLPT.setBackground(new Color(237, 76, 103));
//        btnQLDG.setBackground(new Color(204, 142, 53));


        btnQLS.setIcon(Util.loadImage("sach.png"));
        btnQLPM.setIcon(Util.loadImage("phieumuon.png"));
        btnQLDG.setIcon(Util.loadImage("docgia.png"));
        btnQLPT.setIcon(Util.loadImage("phieutra.png"));

        pnHang1.add(btnQLS);
        pnHang1.add(btnQLDG);
        pnHang2.add(btnQLPM);
        pnHang2.add(btnQLPT);

        pnQuanLy.add(pnHang1);
        pnQuanLy.add(pnHang2);
        pnQuanLy.add(pnHang3);

        mainPanel.add(pnQuanLy, BorderLayout.EAST);


        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new BoxLayout(pnChucNang, BoxLayout.Y_AXIS));

        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("gd.png"));
        pnHinhAnh.add(lblHinhAnh);

        JPanel pnDoiMatKhau = new JPanel();
        pnDoiMatKhau.setLayout(new FlowLayout());
        btnDoiMK = new JButton("Đổi mật khẩu");
        btnDoiMK.setFocusPainted(false);
        pnDoiMatKhau.add(btnDoiMK);
        btnDoiMK.setPreferredSize(new Dimension(300, 60));
        pnDoiMatKhau.setBackground(Color.WHITE);
        btnDoiMK.setBackground(new Color(4, 191, 138));
        btnDoiMK.setForeground(Color.WHITE);

        JPanel pnDangXuat = new JPanel();
        pnDangXuat.setLayout(new FlowLayout());
        btnDangXuat = new JButton("Đăng xuất");
        btnDangXuat.setFocusPainted(false);
        pnDangXuat.add(btnDangXuat);
        btnDangXuat.setPreferredSize(new Dimension(300, 60));
        pnDangXuat.setBackground(Color.WHITE);
        btnDangXuat.setBackground(new Color(4, 191, 138));
        btnDangXuat.setForeground(Color.WHITE);


        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 25);
        btnDoiMK.setFont(font2);
        btnDangXuat.setFont(font2);


        pnChucNang.add(pnHinhAnh);
        pnChucNang.add(pnDoiMatKhau);
        pnChucNang.add(pnDangXuat);

        mainPanel.add(pnChucNang, BorderLayout.WEST);

        btnQLDG.setBorder(null);
        btnQLPM.setBorder(null);
        btnQLPT.setBorder(null);
        btnQLS.setBorder(null);

    }
}
