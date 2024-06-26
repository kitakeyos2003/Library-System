package eaut.edu.vn.ui.dialog.account;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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


import eaut.edu.vn.model.Account;
import eaut.edu.vn.model.Loan;
import eaut.edu.vn.service.LoanService;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.service.AccountService;
import eaut.edu.vn.util.Util;

public class DeleteAccount extends Dialog {
    public String machon = "";
    JTextField txtTaiKhoan, txtHoTen, txtSDT, txtCMND, txtPhanQuyen;
    JPasswordField pwdMatKhau;
    JButton btnXoa;

    public DeleteAccount(String title) {
        super(title, "QUẢN LÝ NGƯỜI DÙNG");
    }

    public void loadInfo() {
        Account account = AccountService.getInstance().find(machon);
        txtTaiKhoan.setText(account.getUsername());
        txtCMND.setText(account.getIdentityNumber());
        txtHoTen.setText(account.getName());
        int phanquyen = account.getRole();
        if (phanquyen == 1) {
            txtPhanQuyen.setText("Admin");
        } else {
            txtPhanQuyen.setText("Thủ thư");
        }
        txtSDT.setText(account.getPhoneNumber());
        pwdMatKhau.setText(account.getPassword());

    }

    @Override
    public void addEvents() {
        btnXoa.addActionListener(e -> {
            List<Loan> loans = LoanService.getInstance().searchByUser(txtTaiKhoan.getText());
            if (!loans.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Người này chưa thu hồi hết sách đã cho mượn.");
                return;
            }
            Account account = new Account();
            account.setUsername(txtTaiKhoan.getText());
            AccountService.getInstance().remove(account);
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            dispose();
        });

    }

    @Override
    public void initComponents() {
        JPanel pnHienThiNguoiDung = new JPanel();
        pnHienThiNguoiDung.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiNguoiDung, BorderLayout.CENTER);


        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("user-info.png"));
        pnHinhAnh.add(lblHinhAnh);
        pnHienThiNguoiDung.add(pnHinhAnh, BorderLayout.WEST);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnHienThiNguoiDung.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblXoaNguoiDung = new JLabel("XÓA NGƯỜI DÙNG");
        pnTitle.add(lblXoaNguoiDung);

        JPanel pnTaiKhoan = new JPanel();
        pnTaiKhoan.setLayout(new FlowLayout());
        JLabel lblTaiKhoan = new JLabel("Tài khoản: ");
        txtTaiKhoan = new JTextField();
        txtTaiKhoan.setEnabled(false);
        txtTaiKhoan.setPreferredSize(new Dimension(340, 30));
        pnTaiKhoan.add(lblTaiKhoan);
        pnTaiKhoan.add(txtTaiKhoan);

        JPanel pnMatKhau = new JPanel();
        pnMatKhau.setLayout(new FlowLayout());
        JLabel lblMatKhau = new JLabel("Mật khẩu: ");
        pwdMatKhau = new JPasswordField();
        pwdMatKhau.setEnabled(false);
        pwdMatKhau.setEchoChar((char) 0);
        pwdMatKhau.setPreferredSize(new Dimension(340, 30));
        pnMatKhau.add(lblMatKhau);
        pnMatKhau.add(pwdMatKhau);

        JPanel pnHoTen = new JPanel();
        pnHoTen.setLayout(new FlowLayout());
        JLabel lblHoTen = new JLabel("Họ và tên: ");
        txtHoTen = new JTextField();
        txtHoTen.setEnabled(false);
        txtHoTen.setPreferredSize(new Dimension(340, 30));
        pnHoTen.add(lblHoTen);
        pnHoTen.add(txtHoTen);

        JPanel pnSoDienThoai = new JPanel();
        pnSoDienThoai.setLayout(new FlowLayout());
        JLabel lblSoDienThoai = new JLabel("Số điện thoại: ");
        txtSDT = new JTextField();
        txtSDT.setEnabled(false);
        txtSDT.setPreferredSize(new Dimension(340, 30));
        pnSoDienThoai.add(lblSoDienThoai);
        pnSoDienThoai.add(txtSDT);

        JPanel pnCMND = new JPanel();
        pnCMND.setLayout(new FlowLayout());
        JLabel lblCMND = new JLabel("Số CMND: ");
        txtCMND = new JTextField();
        txtCMND.setEnabled(false);
        txtCMND.setPreferredSize(new Dimension(340, 30));
        pnCMND.add(lblCMND);
        pnCMND.add(txtCMND);

        JPanel pnPhanQuyen = new JPanel();
        pnPhanQuyen.setLayout(new FlowLayout());
        JLabel lblPhanQuyen = new JLabel("Phân quyền: ");
        txtPhanQuyen = new JTextField();
        txtPhanQuyen.setEnabled(false);
        txtPhanQuyen.setPreferredSize(new Dimension(340, 30));
        pnPhanQuyen.add(lblPhanQuyen);
        pnPhanQuyen.add(txtPhanQuyen);


        pnHienThiChiTiet.add(pnTitle);
        pnHienThiChiTiet.add(pnTaiKhoan);
        pnHienThiChiTiet.add(pnMatKhau);
        pnHienThiChiTiet.add(pnHoTen);
        pnHienThiChiTiet.add(pnSoDienThoai);
        pnHienThiChiTiet.add(pnCMND);
        pnHienThiChiTiet.add(pnPhanQuyen);


        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 30);
        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.TRUETYPE_FONT, 15);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblXoaNguoiDung.setFont(font2);
        lblTaiKhoan.setFont(font4);
        lblSoDienThoai.setFont(font4);
        lblCMND.setFont(font4);
        lblHoTen.setFont(font4);
        lblMatKhau.setFont(font4);
        lblPhanQuyen.setFont(font4);

        txtTaiKhoan.setFont(font4);
        txtCMND.setFont(font4);
        txtHoTen.setFont(font4);
        txtSDT.setFont(font4);
        pwdMatKhau.setFont(font4);
        txtPhanQuyen.setFont(font4);

        pnTitle.setBackground(new Color(241, 242, 246));
        lblXoaNguoiDung.setForeground(new Color(48, 51, 107));
        pnTaiKhoan.setBackground(new Color(241, 242, 246));
        pnCMND.setBackground(new Color(241, 242, 246));
        pnMatKhau.setBackground(new Color(241, 242, 246));
        pnPhanQuyen.setBackground(new Color(241, 242, 246));
        pnSoDienThoai.setBackground(new Color(241, 242, 246));
        pnHoTen.setBackground(new Color(241, 242, 246));
        pnHinhAnh.setBackground(new Color(241, 242, 246));

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnThaoTac);
        btnXoa = new JButton("XÓA");
        btnXoa.setPreferredSize(new Dimension(110, 35));
        pnThaoTac.add(btnXoa);
        pnThaoTac.setBackground(new Color(241, 242, 246));

        btnXoa.setFont(font5);

        btnXoa.setBackground(new Color(4, 191, 138));
        btnXoa.setForeground(Color.white);
        btnXoa.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));


        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnHienThiNguoiDung.setBorder(titleLogin);

        lblTaiKhoan.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblMatKhau.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblHoTen.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblCMND.setPreferredSize(lblSoDienThoai.getPreferredSize());
        lblPhanQuyen.setPreferredSize(lblSoDienThoai.getPreferredSize());

        txtCMND.setEditable(false);
        txtSDT.setEditable(false);
        txtHoTen.setEditable(false);
        txtTaiKhoan.setEditable(false);
        pwdMatKhau.setEditable(false);
        txtPhanQuyen.setEditable(false);


    }

    public void showWindow() {
        this.setSize(900, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

}
