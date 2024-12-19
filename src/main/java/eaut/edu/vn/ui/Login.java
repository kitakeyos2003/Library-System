package eaut.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eaut.edu.vn.main.Application;
import eaut.edu.vn.service.AccountService;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.ui.controls.*;
import eaut.edu.vn.util.Util;

public class Login extends CustomFrame {
    JButton btnLogin, btnExit;
    PlaceholderTextField txtUsername;
    PlaceholderPasswordField pwdPassword;


    public Login(String title) {
        super(title);
        this.setSize(650, 350);
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
            authenticate();

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
                    authenticate();
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
                    authenticate();
                }
            }
        });
    }

    @Override
    public void initComponents() {
        JPanel panelLogin = new JPanel();

        JPanel panelLogo = new JPanel();
        panelLogo.setSize(250, 250);
        JLabel logo = new JLabel();
        panelLogo.setBackground(Color.WHITE);
        logo.setIcon(Util.resizedImage("login.png", 250, 250));
        panelLogo.add(logo);

        panelLogin.setSize(400, 350);
        panelLogin.setBackground(Color.WHITE);
        JPanel panelForm = new JPanel();
        panelForm.setBackground(Color.WHITE);
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBackground(Color.WHITE);



        JPanel panelTitle = new JPanel();

        panelTitle.setBackground(Color.WHITE);
        panelTitle.setLayout(new FlowLayout());
        JLabel lblLogin = new JLabel("ĐĂNG NHẬP");
        panelTitle.add(lblLogin);

        JPanel pnTaiKhoan = new JPanel();
        pnTaiKhoan.setBackground(Color.WHITE);
        pnTaiKhoan.setLayout(new FlowLayout());
        txtUsername = new PlaceholderTextField();
        txtUsername.setPlaceholder("User name");
        txtUsername.setPreferredSize(new Dimension(300, 35));
        pnTaiKhoan.add(txtUsername);

        JPanel pnMatKhau = new JPanel();
        pnMatKhau.setBackground(Color.WHITE);
        pnMatKhau.setLayout(new FlowLayout());
        pwdPassword = new PlaceholderPasswordField();
        pwdPassword.setPlaceholder("Password");
        pwdPassword.setEchoChar((char) 0);
        pwdPassword.setPreferredSize(new Dimension(300, 35));
        pnMatKhau.add(pwdPassword);

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setBackground(Color.WHITE);
        pnThaoTac.setLayout(new FlowLayout());
        btnLogin = new JButton("Đăng nhập");
        btnExit = new JButton("Thoát");
        btnLogin.setPreferredSize(new Dimension(130, 35));
        pnThaoTac.add(btnLogin);
        pnThaoTac.add(btnExit);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(10, 20));
        panel.setBackground(Color.WHITE);

        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(10, 20));
        panel2.setBackground(Color.WHITE);
        panelForm.add(panel);
        panelForm.add(panelTitle);
        panelForm.add(pnTaiKhoan);
        panelForm.add(pnMatKhau);
        panelForm.add(panel2);
        panelForm.add(pnThaoTac);

        JPanel panelLeft = new JPanel();
        panelLeft.setBackground(Color.WHITE);
        panelLeft.setPreferredSize(new Dimension(20, 50));

        JPanel panelRight = new JPanel();
        panelRight.setBackground(Color.WHITE);
        panelRight.setPreferredSize(new Dimension(20, 50));

        panelLogin.add(panelLeft);
        panelLogin.add(panelForm);
        panelLogin.add(panelRight);

        mainPanel.add(panelLogo, BorderLayout.WEST);
        mainPanel.add(panelLogin, BorderLayout.EAST);

        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 14);
        lblLogin.setFont(font1);

        txtUsername.setFont(font4);
        pwdPassword.setFont(font4);
        btnExit.setFont(font5);
        btnLogin.setFont(font5);

        lblLogin.setForeground(new Color(48, 51, 107));

        btnLogin.setBackground(new Color(82, 196, 82));
        btnLogin.setForeground(Color.white);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);

        btnExit.setBackground(new Color(51, 51, 51));
        btnExit.setForeground(Color.white);
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);


        btnExit.setPreferredSize(btnLogin.getPreferredSize());

    }

    private void authenticate() {
        String username = txtUsername.getText();
        String password = pwdPassword.getText();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tài khoản không được để trống");
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống");
            return;
        }
        Account account = AccountService.getInstance().authenticate(username, password);
        if (account == null) {
            JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu. Vui lòng Nhập lại");
            return;
        }
        Application.account = account;
        if (account.getRole() == 1) {
            AdminManager ql = new AdminManager("Trang chủ");;
            ql.showWindow();
            dispose();
        } else if (account.getRole() == 2) {
            LibrarianManager ql = new LibrarianManager("Trang chủ");
            ql.showWindow();
            dispose();
        }
    }

}
