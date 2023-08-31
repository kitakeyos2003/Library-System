package eaut.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import eaut.edu.vn.service.AccountService;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.CustomFrame;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.util.Util;

public class Login extends CustomFrame {
    JButton btnLogin, btnExit;
    JTextField txtUsername;
    JPasswordField pwdPassword;


    public Login(String title) {
        super(title);
        this.setSize(540, 350);
        setHeader(new Header("QUẢN LÝ NGƯỜI DÙNG"));
        setFooter(new Footer());
    }

    @Override
    public void addEvents() {

        btnExit.addActionListener(e -> System.exit(0));
        txtUsername.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                txtUsername.setText(null);

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
                txtUsername.setText(null);

            }
        });
        pwdPassword.addMouseListener(new MouseListener() {


            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                pwdPassword.setEchoChar('*');
                pwdPassword.setText(null);

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
                pwdPassword.setEchoChar('*');
                pwdPassword.setText(null);

            }
        });
        btnLogin.addActionListener(e -> {
            AccountService tksv = new AccountService();
            ArrayList<Account> dstk = tksv.layTaiKhoan();
            if (txtUsername.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Tài khoản không được để trống");
                return;
            }
            if (pwdPassword.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống");
                return;
            }
            for (Account tk : dstk) {
                if (txtUsername.getText().equals(tk.getUser()) && pwdPassword.getText().equals(tk.getPass()) && tk.getPhanQuyen() == 1) {
                    AdminManager qlad = new AdminManager("Trang Chủ Phần Mềm Quản Lý Thư Viện");
                    qlad.tentk = txtUsername.getText();
                    qlad.showWindow();
                    dispose();
                    return;
                }
                if (txtUsername.getText().equals(tk.getUser()) && pwdPassword.getText().equals(tk.getPass()) && tk.getPhanQuyen() == 2) {
                    LibrarianManager ql = new LibrarianManager("Thủ thư");
                    ql.tentk = txtUsername.getText();
                    ql.showWindow();
                    dispose();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu. Vui lòng nhập lại");
            txtUsername.setText(null);
            pwdPassword.setText(null);

        });
        txtUsername.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    pwdPassword.setEchoChar('*');
                    pwdPassword.setText(null);
                }
                // TODO Auto-generated method stub
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AccountService tksv = new AccountService();
                    ArrayList<Account> dstk = new ArrayList<Account>();
                    dstk = tksv.layTaiKhoan();
                    if (txtUsername.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Tài khoản không được để trống");
                        return;
                    }
                    if (pwdPassword.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống");
                        return;
                    }
                    for (Account ac : dstk) {
                        if (txtUsername.getText().equals(ac.getUser()) && pwdPassword.getText().equals(ac.getPass()) && ac.getPhanQuyen() == 1) {
                            AdminManager ql = new AdminManager("Trang Chủ Phần Mềm Quản Lý Thư Viện");
                            ql.tentk = txtUsername.getText();
                            ql.showWindow();
                            dispose();
                            return;
                        }
                        if (txtUsername.getText().equals(ac.getUser()) && pwdPassword.getText().equals(ac.getPass()) && ac.getPhanQuyen() == 2) {
                            LibrarianManager ql = new LibrarianManager("Thủ thư");
                            ql.tentk = txtUsername.getText();
                            ql.showWindow();
                            dispose();
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu. Vui lòng Nhập lại");
                    txtUsername.setText(null);
                    pwdPassword.setText(null);
                }
            }
        });
        pwdPassword.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AccountService tksv = new AccountService();
                    ArrayList<Account> dstk = new ArrayList<Account>();
                    dstk = tksv.layTaiKhoan();
                    if (txtUsername.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Tài khoản không được để trống");
                        return;
                    }
                    if (pwdPassword.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống");
                        return;
                    }
                    for (Account ac : dstk) {
                        if (txtUsername.getText().equals(ac.getUser()) && pwdPassword.getText().equals(ac.getPass()) && ac.getPhanQuyen() == 1) {
                            AdminManager ql = new AdminManager("Trang Chủ Phần Mềm Quản Lý Thư Viện");
                            ql.tentk = txtUsername.getText();
                            ql.showWindow();
                            dispose();
                            return;
                        }
                        if (txtUsername.getText().equals(ac.getUser()) && pwdPassword.getText().equals(ac.getPass()) && ac.getPhanQuyen() == 2) {
                            LibrarianManager ql = new LibrarianManager("Thủ thư");
                            ql.tentk = txtUsername.getText();
                            ql.showWindow();
                            dispose();
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu. Vui lòng Nhập lại");
                    txtUsername.setText(null);
                    pwdPassword.setText(null);
                }
            }
        });
    }

    @Override
    public void initComponents() {
        JPanel pnLogin = new JPanel();
        pnLogin.setLayout(new BoxLayout(pnLogin, BoxLayout.Y_AXIS));

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblLogin = new JLabel("ĐĂNG NHẬP");
        pnTitle.add(lblLogin);

        JPanel pnTaiKhoan = new JPanel();
        pnTaiKhoan.setLayout(new FlowLayout());
        //JLabel lblTaiKhoan = new JLabel("TÀI KHOẢN  ");
        txtUsername = new JTextField("User name");
        txtUsername.setPreferredSize(new Dimension(350, 30));
        //pnTaiKhoan.add(lblTaiKhoan);
        pnTaiKhoan.add(txtUsername);

        JPanel pnMatKhau = new JPanel();
        pnMatKhau.setLayout(new FlowLayout());
        //JLabel lblMatKhau = new JLabel("MẬT KHẨU  ");
        pwdPassword = new JPasswordField("Password");
        pwdPassword.setEchoChar((char) 0);
        pwdPassword.setPreferredSize(new Dimension(350, 30));
        //pnMatKhau.add(lblMatKhau);
        pnMatKhau.add(pwdPassword);

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        btnLogin = new JButton("Đăng nhập");
        btnExit = new JButton("Thoát");
        btnLogin.setPreferredSize(new Dimension(130, 35));
        pnThaoTac.add(btnLogin);
        pnThaoTac.add(btnExit);

        pnLogin.add(pnTitle);
        pnLogin.add(pnTaiKhoan);
        pnLogin.add(pnMatKhau);
        pnLogin.add(pnThaoTac);
        mainPanel.add(pnLogin, BorderLayout.CENTER);

        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 10);
        lblLogin.setFont(font1);
        //lblTaiKhoan.setFont(font3);
        //lblMatKhau.setFont(font3);
        txtUsername.setFont(font4);
        pwdPassword.setFont(font4);
        btnExit.setFont(font5);
        btnLogin.setFont(font5);

        btnLogin.setIcon(Util.loadImage("lock.png"));
        btnExit.setIcon(Util.loadImage("close.png"));

        pnTitle.setBackground(new Color(241, 242, 246));
        lblLogin.setForeground(new Color(48, 51, 107));
        pnTaiKhoan.setBackground(new Color(241, 242, 246));
        pnMatKhau.setBackground(new Color(241, 242, 246));
        pnThaoTac.setBackground(new Color(241, 242, 246));

        btnLogin.setBackground(new Color(48, 51, 107));
        btnLogin.setForeground(Color.white);
        btnExit.setBackground(new Color(48, 51, 107));
        btnExit.setForeground(Color.white);


        btnExit.setPreferredSize(btnLogin.getPreferredSize());
        //lblMatKhau.setPreferredSize(lblTaiKhoan.getPreferredSize() );

        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnLogin.setBorder(titleLogin);

    }

}
