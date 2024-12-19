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

public class AdminManager extends CustomFrame {
    JButton btnQLND, btnQLDG, btnQLPM, btnQLPT, btnThongKe, btnQLS, btnDoiMK, btnDangXuat;

    public AdminManager(String title) {
        super(title);
        this.setSize(888, 600);
        setHeader(new Header("TRANG CHỦ: QUẢN LÝ THƯ VIỆN"));
        setFooter(new Footer());
    }

    public void addEvents() {
        btnDangXuat.addActionListener(e -> {
            Application.account = null;
            Login login = new Login("Đăng nhập hệ thống");
            login.showWindow();
            dispose();
        });
        btnQLND.addActionListener(e -> {
            AccountManager qlnd =  new AccountManager("Quản lý người dùng");
            qlnd.showWindow();
            dispose();
        });
        btnQLPM.addActionListener(e -> {
            LoanManager qlpm = new LoanManager("Quản lý phiếu mượn");
            qlpm.showWindow();
            dispose();
        });
        btnQLPT.addActionListener(e -> {
            ReturnManager qlpt = new ReturnManager("Quản lý phiếu trả");
            qlpt.showWindow();
            dispose();

        });
        btnQLDG.addActionListener(e -> {
            ReaderManager qldg = new ReaderManager("Quản lý độc giả");
            qldg.showWindow();
            dispose();

        });

        btnQLS.addActionListener(e -> {
            BookManager qls = new BookManager("Quản lý sách");
            qls.showWindow();
            dispose();

        });
        btnDoiMK.addActionListener(e -> {
            ChangePassword dmk = new ChangePassword("Đổi mật khẩu");
            dmk.showWindow();
            dispose();

        });
        btnThongKe.addActionListener(e -> {
            // TODO Auto-generated method stub
            StatisticAnalyzer tk =   new StatisticAnalyzer("Thống kê");
            tk.showWindow();
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
        btnQLND = new JButton();
        btnQLS = new JButton();

        JPanel pnHang2 = new JPanel();
        pnHang2.setLayout(new FlowLayout());
        pnHang2.setBackground(Color.WHITE);
        btnQLPM = new JButton();
        btnQLDG = new JButton();

        JPanel pnHang3 = new JPanel();
        pnHang3.setLayout(new FlowLayout());
        pnHang3.setBackground(Color.WHITE);
        btnQLPT = new JButton();
        btnThongKe = new JButton();

        btnQLND.setPreferredSize(new Dimension(270, 150));
        btnQLS.setPreferredSize(btnQLND.getPreferredSize());
        btnQLPM.setPreferredSize(btnQLND.getPreferredSize());
        btnQLDG.setPreferredSize(btnQLND.getPreferredSize());
        btnQLPT.setPreferredSize(btnQLND.getPreferredSize());
        btnThongKe.setPreferredSize(btnQLND.getPreferredSize());

//        btnQLS.setBackground(new Color(51, 217, 178));
//        btnQLND.setBackground(new Color(4, 191, 138));
//        btnQLPM.setBackground(new Color(37, 204, 247));
//        btnQLPT.setBackground(new Color(237, 76, 103));
//        btnQLDG.setBackground(new Color(204, 142, 53));
//        btnThongKe.setBackground(new Color(162, 155, 254));

        btnQLS.setBackground(Color.WHITE);
        btnQLND.setBackground(Color.WHITE);
        btnQLPM.setBackground(Color.WHITE);
        btnQLPT.setBackground(Color.WHITE);
        btnQLDG.setBackground(Color.WHITE);
        btnThongKe.setBackground(Color.WHITE);

        btnQLND.setIcon(Util.loadImage("nguoidung.png"));
        btnQLS.setIcon(Util.loadImage("sach.png"));
        btnQLPM.setIcon(Util.loadImage("phieumuon.png"));
        btnQLDG.setIcon(Util.loadImage("docgia.png"));
        btnQLPT.setIcon(Util.loadImage("phieutra.png"));
        btnThongKe.setIcon(Util.loadImage("thongke.png"));

        pnHang1.add(btnQLND);
        pnHang1.add(btnQLS);
        pnHang2.add(btnQLPM);
        pnHang2.add(btnQLDG);
        pnHang3.add(btnQLPT);
        pnHang3.add(btnThongKe);

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
        btnQLND.setBorder(null);
        btnQLPM.setBorder(null);
        btnQLPT.setBorder(null);
        btnQLS.setBorder(null);
        btnThongKe.setBorder(null);

    }

}
