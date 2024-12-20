package eaut.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import eaut.edu.vn.interfaces.ITable;
import eaut.edu.vn.main.Application;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.service.AccountService;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.CustomFrame;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.account.EditAccount;
import eaut.edu.vn.ui.dialog.account.AddAccount;
import eaut.edu.vn.ui.dialog.account.DeleteAccount;
import eaut.edu.vn.util.Util;

public class AccountManager extends CustomFrame implements ITable {
    
    public int ThongKe = 0;
    JButton btnThem, btnXoa, btnSua, btnQuayLai, btnIcon;
    JTable tblNguoiDung;
    DefaultTableModel dtmNguoiDung;
    JTextField txtTaiKhoạn, txtHoVaTen, txtPhanQuyen, txtCMND, txtSoDienThoai;
    JPasswordField pwdPass;
    List<Account> accounts;
    int dem = 2;

    public AccountManager(String title) {
        super(title);
        this.setSize(865, 780);
        setHeader(new Header("QUẢN LÝ NGƯỜI DÙNG"));
        setFooter(new Footer());
        fillTable();
    }

    @Override
    public void fillTable() {
        accounts = AccountService.getInstance().getAll();
        dtmNguoiDung.setRowCount(0);
        for (Account tk : accounts) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(tk.getUsername());
            vec.add(tk.getName());
            vec.add(tk.getPhoneNumber());
            vec.add(tk.getRole());
            dtmNguoiDung.addRow(vec);
        }

    }

    @Override
    public void initComponents() {
        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        mainPanel.add(pnThongTin, BorderLayout.CENTER);

        JPanel pnChiTietNguoiDung = new JPanel();
        pnChiTietNguoiDung.setLayout(new BorderLayout());

        JPanel pnTieuDe1 = new JPanel();
        JLabel lblTieuDe1 = new JLabel("THÔNG TIN CHI TIẾT");
        pnTieuDe1.add(lblTieuDe1);
        pnChiTietNguoiDung.add(pnTieuDe1, BorderLayout.NORTH);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 18);
        lblTieuDe1.setFont(font2);
        pnTieuDe1.setBackground(new Color(2, 115, 83));
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
        btnQuayLai.setBackground(new Color(2, 115, 83));
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        btnQuayLai.setFont(font4);
        pnQuayLai.add(btnQuayLai);
        btnQuayLai.setForeground(Color.WHITE);
        pnQuayLai.setBackground(new Color(2, 115, 83));
        pnThaoTac.add(pnQuayLai, BorderLayout.NORTH);

        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new BoxLayout(pnChucNang, BoxLayout.Y_AXIS));

        JPanel pnThem = new JPanel();
        pnThem.setLayout(new FlowLayout());
        btnThem = new JButton();
        pnThem.add(btnThem);
        btnThem.setBackground(new Color(241, 242, 246));
        pnThem.setBackground(new Color(241, 242, 246));

        JPanel pnXoa = new JPanel();
        pnXoa.setLayout(new FlowLayout());
        btnXoa = new JButton();
        btnXoa.setBackground(new Color(241, 242, 246));
        pnXoa.add(btnXoa);
        pnXoa.setBackground(new Color(241, 242, 246));

        JPanel pnSua = new JPanel();
        pnSua.setLayout(new FlowLayout());
        btnSua = new JButton();
        btnSua.setBackground(new Color(241, 242, 246));
        pnSua.add(btnSua);
        pnSua.setBackground(new Color(241, 242, 246));

        pnChucNang.add(pnThem);
        pnChucNang.add(pnXoa);
        pnChucNang.add(pnSua);

        pnThaoTac.add(pnChucNang, BorderLayout.CENTER);

        Border borderHienThi = BorderFactory.createLineBorder(new Color(241, 242, 246));
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
        pnTieuDe2.setBackground(new Color(2, 115, 83));
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

    @Override
    public void addEvents() {
        btnQuayLai.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (ThongKe == 1) {
                StatisticAnalyzer ui =   new StatisticAnalyzer("Thống kê");
                ui.showWindow();
                dispose();
                ThongKe = 0;
                return;
            }
            AdminManager ql = new AdminManager("Trang chủ");;
            ql.showWindow();
            dispose();
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
                int index = tblNguoiDung.getSelectedRow();
                Account account = accounts.get(index);
                txtTaiKhoạn.setText(account.getUsername());
                txtCMND.setText(account.getIdentityNumber());
                txtHoVaTen.setText(account.getName());
                int role = account.getRole();
                if (role == 1) {
                    txtPhanQuyen.setText("Admin");
                } else {
                    txtPhanQuyen.setText("Thủ thư");
                }
                txtSoDienThoai.setText(account.getPhoneNumber());
                pwdPass.setText(account.getPassword());

            }
        });
        btnIcon.addActionListener(e -> {
            if (dem % 2 == 0) {
                pwdPass.setEchoChar((char) 0);
            }
            if (dem % 2 != 0) {
                pwdPass.setEchoChar('*');
            }
            dem++;
        });
        btnThem.addActionListener(e -> {
            // TODO Auto-generated method stub
            AddAccount addAccount = new AddAccount("Thêm người dùng");
            addAccount.showWindow();
            fillTable();
        });
        btnXoa.addActionListener(e -> {
            String username = txtTaiKhoạn.getText();
            if (username.isEmpty()) {
                return;
            }
            DeleteAccount deleteAccount = new DeleteAccount("Xóa người dùng");
            deleteAccount.machon = username;
            deleteAccount.loadInfo();
            deleteAccount.showWindow();
            fillTable();
            txtCMND.setText(null);
            txtSoDienThoai.setText(null);
            txtHoVaTen.setText(null);
            txtTaiKhoạn.setText(null);
            txtPhanQuyen.setText(null);
            pwdPass.setText(null);
        });
        btnSua.addActionListener(e -> {
            EditAccount editAccount = new EditAccount("Sửa người dùng");
            editAccount.machon = txtTaiKhoạn.getText();
            editAccount.fillInfo();
            editAccount.showWindow();
            fillTable();
            txtCMND.setText(null);
            txtSoDienThoai.setText(null);
            txtHoVaTen.setText(null);
            txtTaiKhoạn.setText(null);
            txtPhanQuyen.setText(null);
            pwdPass.setText(null);
        });
    }

}
