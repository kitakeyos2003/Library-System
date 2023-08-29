package eaut.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import eaut.edu.vn.service.AccountService;
import eaut.edu.vn.ui.dialog.account.Sua;
import eaut.edu.vn.ui.dialog.account.Them;
import eaut.edu.vn.ui.dialog.account.Xoa;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.util.Util;

public class AccountManager extends JFrame {
    public String tentk = "";
    public int ThongKe = 0;
    JButton btnThem, btnXoa, btnSua, btnQuayLai, btnIcon;
    JTable tblNguoiDung;
    DefaultTableModel dtmNguoiDung;
    JTextField txtTaiKhoạn, txtHoVaTen, txtPhanQuyen, txtCMND, txtSoDienThoai;
    JPasswordField pwdPass;
    ArrayList<Account> dstk;
    int dem = 2;

    public AccountManager(String title) {
        this.setTitle(title);
        addControls();
        addEvents();
        hienThiQLND();
    }

    public void hienThiQLND() {
        AccountService tksv = new AccountService();
        dstk = tksv.layTaiKhoan();
        dtmNguoiDung.setRowCount(0);
        for (Account tk : dstk) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getUser());
            vec.add(tk.getTenND());
            vec.add(tk.getSoDienThoai());
            vec.add(tk.getPhanQuyen());
            dtmNguoiDung.addRow(vec);
        }

    }

    public void addControls() {
        Container con = getContentPane();

        JPanel pnNguoiDung = new JPanel();
        pnNguoiDung.setLayout(new BorderLayout());
        con.add(pnNguoiDung);

        JPanel pnTieuDe = new JPanel();
        JLabel lblTieuDe = new JLabel("QUẢN LÝ NGƯỜI DÙNG");
        pnTieuDe.add(lblTieuDe);
        pnNguoiDung.add(pnTieuDe, BorderLayout.NORTH);
        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        lblTieuDe.setFont(font1);
        pnTieuDe.setBackground(new Color(48, 51, 107));
        lblTieuDe.setForeground(Color.WHITE);


        JPanel pnLienHe = new JPanel();
        JLabel lblLienHe = new JLabel("THÔNG TIN TRỢ GIÚP - LIÊN HỆ: 0342565857");
        pnLienHe.add(lblLienHe);
        pnNguoiDung.add(pnLienHe, BorderLayout.SOUTH);
        pnLienHe.setBackground(new Color(48, 51, 107));
        lblLienHe.setForeground(Color.WHITE);
        Font fontx = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblLienHe.setFont(fontx);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        pnNguoiDung.add(pnThongTin, BorderLayout.CENTER);

        JPanel pnChiTietNguoiDung = new JPanel();
        pnChiTietNguoiDung.setLayout(new BorderLayout());

        JPanel pnTieuDe1 = new JPanel();
        JLabel lblTieuDe1 = new JLabel("THÔNG TIN CHI TIẾT");
        pnTieuDe1.add(lblTieuDe1);
        pnChiTietNguoiDung.add(pnTieuDe1, BorderLayout.NORTH);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 18);
        lblTieuDe1.setFont(font2);
        pnTieuDe1.setBackground(new Color(255, 177, 66));
        lblTieuDe1.setForeground(Color.WHITE);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnChiTietNguoiDung.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTaiKhoan = new JPanel();
        pnTaiKhoan.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTaiKhoan);
        JLabel lblTaiKhoan = new JLabel("Tài khoản: ");
        txtTaiKhoạn = new JTextField();
        txtTaiKhoạn.setPreferredSize(new Dimension(240, 22));
        pnTaiKhoan.add(lblTaiKhoan);
        pnTaiKhoan.add(txtTaiKhoạn);

        JPanel pnMatKhau = new JPanel();
        pnMatKhau.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnMatKhau);
        JLabel lblMatKhau = new JLabel("Mật khẩu: ");
        pnMatKhau.add(lblMatKhau);
        pwdPass = new JPasswordField();
        pwdPass.setPreferredSize(new Dimension(208, 22));
        pnMatKhau.add(pwdPass);
        btnIcon = new JButton("");
        btnIcon.setIcon(Util.loadImage("eye.png"));
        btnIcon.setPreferredSize(new Dimension(29, 20));
        btnIcon.setBackground(Color.WHITE);
        btnIcon.setBorder(null);
        pnMatKhau.add(btnIcon);

        JPanel pnHoVaTen = new JPanel();
        pnHoVaTen.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnHoVaTen);
        JLabel lblHoVaTen = new JLabel("Họ và tên: ");
        txtHoVaTen = new JTextField();
        txtHoVaTen.setPreferredSize(new Dimension(240, 22));
        pnHoVaTen.add(lblHoVaTen);
        pnHoVaTen.add(txtHoVaTen);

        JPanel pnSoDienThoai = new JPanel();
        pnSoDienThoai.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnSoDienThoai);
        JLabel lblSoDienThoai = new JLabel("Số điện thoại: ");
        txtSoDienThoai = new JTextField();
        txtSoDienThoai.setPreferredSize(new Dimension(240, 22));
        pnSoDienThoai.add(lblSoDienThoai);
        pnSoDienThoai.add(txtSoDienThoai);

        JPanel pnCMND = new JPanel();
        pnCMND.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnCMND);
        JLabel lblCMND = new JLabel("CMND: ");
        txtCMND = new JTextField();
        txtCMND.setPreferredSize(new Dimension(240, 22));
        pnCMND.add(lblCMND);
        pnCMND.add(txtCMND);

        JPanel pnPhanQuyen = new JPanel();
        pnPhanQuyen.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnPhanQuyen);
        JLabel lblPhanQuyen = new JLabel("Phân quyền: ");
        txtPhanQuyen = new JTextField();
        txtPhanQuyen.setPreferredSize(new Dimension(240, 22));
        pnPhanQuyen.add(lblPhanQuyen);
        pnPhanQuyen.add(txtPhanQuyen);

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new BorderLayout());

        JPanel pnQuayLai = new JPanel();
        pnQuayLai.setLayout(new FlowLayout());
        btnQuayLai = new JButton("QUAY LẠI");
        btnQuayLai.setBackground(new Color(255, 177, 66));
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        btnQuayLai.setFont(font4);
        pnQuayLai.add(btnQuayLai);
        btnQuayLai.setForeground(Color.WHITE);
        pnQuayLai.setBackground(new Color(255, 177, 66));
        pnThaoTac.add(pnQuayLai, BorderLayout.NORTH);

        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new BoxLayout(pnChucNang, BoxLayout.Y_AXIS));

        JPanel pnThem = new JPanel();
        pnThem.setLayout(new FlowLayout());
        btnThem = new JButton();
        pnThem.add(btnThem);
        btnThem.setBackground(new Color(255, 177, 66));
        pnThem.setBackground(new Color(255, 177, 66));

        JPanel pnXoa = new JPanel();
        pnXoa.setLayout(new FlowLayout());
        btnXoa = new JButton();
        btnXoa.setBackground(new Color(255, 177, 66));
        pnXoa.add(btnXoa);
        pnXoa.setBackground(new Color(255, 177, 66));

        JPanel pnSua = new JPanel();
        pnSua.setLayout(new FlowLayout());
        btnSua = new JButton();
        btnSua.setBackground(new Color(255, 177, 66));
        pnSua.add(btnSua);
        pnSua.setBackground(new Color(255, 177, 66));

        pnChucNang.add(pnThem);
        pnChucNang.add(pnXoa);
        pnChucNang.add(pnSua);

        pnThaoTac.add(pnChucNang, BorderLayout.CENTER);

        Border borderHienThi = BorderFactory.createLineBorder(new Color(255, 177, 66));
        TitledBorder titleBorderHienThi = new TitledBorder(borderHienThi);
        titleBorderHienThi.setTitleJustification(TitledBorder.LEFT);
        pnHienThiChiTiet.setBorder(titleBorderHienThi);

        JPanel pnBangThongKe = new JPanel();
        pnBangThongKe.setLayout(new BorderLayout());

        dtmNguoiDung = new DefaultTableModel();
        dtmNguoiDung.addColumn("Tài khoản");
        dtmNguoiDung.addColumn("Họ và tên");
        dtmNguoiDung.addColumn("Số điện thoại");
        dtmNguoiDung.addColumn("Phân quyền");
        tblNguoiDung = new JTable(dtmNguoiDung);
        JScrollPane sc = new JScrollPane(tblNguoiDung, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc.setPreferredSize(new Dimension(400, 380));
        pnBangThongKe.add(sc, BorderLayout.CENTER);

        JPanel pnTieuDe2 = new JPanel();
        JLabel lblTieuDe2 = new JLabel("DANH SÁCH NGƯỜI DÙNG");
        pnTieuDe2.add(lblTieuDe2);
        pnBangThongKe.add(pnTieuDe2, BorderLayout.NORTH);
        lblTieuDe2.setFont(font2);
        pnTieuDe2.setBackground(new Color(48, 51, 107));
        lblTieuDe2.setForeground(Color.WHITE);

        btnQuayLai.setPreferredSize(new Dimension(220, 30));
        btnThem.setPreferredSize(new Dimension(210, 60));
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        btnSua.setPreferredSize(btnThem.getPreferredSize());

        pnThongTin.add(pnChiTietNguoiDung, BorderLayout.CENTER);
        pnThongTin.add(pnBangThongKe, BorderLayout.SOUTH);
        pnThongTin.add(pnThaoTac, BorderLayout.EAST);

        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        lblTaiKhoan.setFont(font3);
        lblHoVaTen.setFont(font3);
        lblPhanQuyen.setFont(font3);
        lblSoDienThoai.setFont(font3);
        lblCMND.setFont(font3);
        lblMatKhau.setFont(font3);

        lblCMND.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblMatKhau.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblPhanQuyen.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblHoVaTen.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblTaiKhoan.setPreferredSize(lblSoDienThoai.getPreferredSize());

        pnMatKhau.setBackground(new Color(255, 255, 255));
        pnHoVaTen.setBackground(new Color(255, 255, 255));
        pnPhanQuyen.setBackground(new Color(255, 255, 255));
        pnSoDienThoai.setBackground(new Color(255, 255, 255));
        pnTaiKhoan.setBackground(new Color(255, 255, 255));
        pnCMND.setBackground(new Color(255, 255, 255));

        txtSoDienThoai.setEditable(false);
        txtTaiKhoạn.setEditable(false);
        txtHoVaTen.setEditable(false);
        txtPhanQuyen.setEditable(false);
        pwdPass.setEditable(false);
        txtCMND.setEditable(false);

        btnThem.setIcon(Util.loadImage("themmoi.png"));
        btnXoa.setIcon(Util.loadImage("xoa.png"));
        btnSua.setIcon(Util.loadImage("sua.png"));
        btnQuayLai.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnThem.setBorder(null);
        btnXoa.setBorder(null);
        btnSua.setBorder(null);

    }

    public void addEvents() {
        btnQuayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (ThongKe == 1) {
                    StatisticAnalyzer ui = new StatisticAnalyzer("Thống kê");
                    ui.tenTk = tentk;
                    ui.showWindow();
                    dispose();
                    ThongKe = 0;
                    return;
                }
                AdminManager ql = new AdminManager("Trang Chủ Phần Mềm Quản Lý Thư Viện");
                ql.tentk = tentk;
                ql.showWindow();
                dispose();
            }
        });
        tblNguoiDung.addMouseListener(new MouseListener() {

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
                pwdPass.setEchoChar('*');
                int n = tblNguoiDung.getSelectedRow();
                String user = String.valueOf(dtmNguoiDung.getValueAt(n, 0));
                AccountService tksv = new AccountService();
                dstk = tksv.layTaiKhoanTheoUser(user);
                for (Account tk : dstk) {
                    txtTaiKhoạn.setText(tk.getUser());
                    txtCMND.setText(tk.getCMND());
                    txtHoVaTen.setText(tk.getTenND());
                    int phanquyen = tk.getPhanQuyen();
                    if (phanquyen == 1) {
                        txtPhanQuyen.setText("Admin");
                    } else
                        txtPhanQuyen.setText("Thủ thư");
                    txtSoDienThoai.setText(tk.getSoDienThoai());
                    pwdPass.setText(tk.getPass());
                }


            }
        });
        btnIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (dem % 2 == 0) {
                    pwdPass.setEchoChar((char) 0);
                }
                if (dem % 2 != 0) {
                    pwdPass.setEchoChar('*');
                }
                dem++;
            }
        });
        btnThem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Them themqlnd = new Them("Thêm người dùng");
                themqlnd.showWindow();
                hienThiQLND();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Xoa xoand = new Xoa("Xóa người dùng");
                xoand.machon = txtTaiKhoạn.getText();
                xoand.hienThi();
                xoand.showWindow();
                hienThiQLND();
                txtCMND.setText(null);
                txtSoDienThoai.setText(null);
                txtHoVaTen.setText(null);
                txtTaiKhoạn.setText(null);
                txtPhanQuyen.setText(null);
                pwdPass.setText(null);
            }
        });
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sua suand = new Sua("Sửa người dùng");
                suand.machon = txtTaiKhoạn.getText();
                suand.hienThi();
                suand.showWindow();
                hienThiQLND();
                txtCMND.setText(null);
                txtSoDienThoai.setText(null);
                txtHoVaTen.setText(null);
                txtTaiKhoạn.setText(null);
                txtPhanQuyen.setText(null);
                pwdPass.setText(null);
            }
        });
    }

    public void showWindow() {
        this.setSize(865, 780);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

}
