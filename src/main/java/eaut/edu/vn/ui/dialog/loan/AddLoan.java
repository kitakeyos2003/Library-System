package eaut.edu.vn.ui.dialog.loan;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.service.LoanService;
import eaut.edu.vn.model.Loan;
import eaut.edu.vn.util.Util;


public class AddLoan extends Dialog {
    public String ma = "";
    public String tentk = "";
    JTextField txtMaPhieu, txtMaDG, txtTenDG, txtNgayMuon, txtNgayHenTra, txtSachMuon, txtThuThu;
    JButton btnThem;
    Connection conn = ConnectMySQL.connect;
    JDateChooser choosedate, choosedate1;

    public AddLoan(String title) {
        super(title);
        setHeader(new Header("QUẢN LÝ PHIẾU MƯỢN"));
        setFooter(new Footer());
        if (tentk.length() != 0) {
            hienThi();
        }
    }

    public void hienThi() {
        txtThuThu.setText(tentk);
    }

    @Override
    public void addEvents() {
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    String sql = "select * from phieumuon where mapm=?";
                    PreparedStatement pre = conn.prepareStatement(sql);
                    pre.setString(1, txtMaPhieu.getText());
                    ResultSet rs = pre.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Mã phiếu mượn đã tồn tại!");
                        return;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                int soluong2 = 0;
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                if (choosedate.getDate() == null || choosedate1.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Không được để trống");
                    return;
                }
                String datemuon = df.format(choosedate.getDate());
                String datehentra = df.format(choosedate1.getDate());

                int flag = 1;
                try {
                    String sql = "insert into phieumuon values(?,?,?,?,?,?)";
                    PreparedStatement pre = conn.prepareStatement(sql);
                    pre.setString(1, txtMaPhieu.getText());
                    pre.setString(2, txtMaDG.getText());
                    pre.setString(3, datemuon);
                    pre.setString(4, datehentra);
                    pre.setString(5, txtSachMuon.getText());
                    pre.setString(6, txtThuThu.getText());
                    if (txtMaPhieu.getText().length() == 0 || txtMaDG.getText().length() == 0 || txtSachMuon.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Không được để trống");
                        return;
                    }


                    try {
                        String sqldocgia1 = "Select MatSach from docgia where MaDG=?";
                        PreparedStatement prex = ConnectMySQL.connect.prepareStatement(sqldocgia1);
                        prex.setString(1, txtMaDG.getText());
                        ResultSet b = prex.executeQuery();
                        while (b.next()) {
                            soluong2 = b.getInt(1);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (soluong2 == 3) {
                        JOptionPane.showMessageDialog(null, "Bạn đã làm mất sách 3 lần. Bạn không được mượn sách nữa.Thanks!");
                        dispose();
                        return;
                    }
                    int x = pre.executeUpdate();
                    if (x > 0) {
                        JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công");
                        dispose();
                        String soluong = txtSachMuon.getText();
                        int soluong1 = Integer.parseInt(soluong);
                        for (int i = 0; i < soluong1; i++) {
                            BookBorrowStatus qlts = new BookBorrowStatus("Thêm Sách");
                            qlts.MaPM = txtMaPhieu.getText();
                            qlts.user = tentk;
                            qlts.hienThi();
                            qlts.showWindow();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    public int DemPhieuMuon() {
        int SoLuongPM = 0;
        LoanService pmsv = new LoanService();
        ArrayList<Loan> ds = pmsv.layThongTinPhieuMuon();
        for (Loan pm : ds) {
            SoLuongPM++;
        }
        return SoLuongPM;
    }

    @Override
    public void initComponents() {
        int kqpm = DemPhieuMuon() + 1;
        JPanel pnHienThiThemPM = new JPanel();
        pnHienThiThemPM.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiThemPM, BorderLayout.CENTER);

        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("qlpm.png"));
        pnHinhAnh.add(lblHinhAnh);
        pnHienThiThemPM.add(pnHinhAnh, BorderLayout.WEST);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnHienThiThemPM.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblThemPM = new JLabel("THÊM PHIẾU MƯỢN");
        pnTitle.add(lblThemPM);

        JPanel pnMaPM = new JPanel();
        pnMaPM.setLayout(new FlowLayout());
        JLabel lblMaPM = new JLabel("Mã phiếu: ");
        txtMaPhieu = new JTextField("PM" + kqpm);
        txtMaPhieu.setPreferredSize(new Dimension(340, 30));
        pnMaPM.add(lblMaPM);
        pnMaPM.add(txtMaPhieu);

        JPanel pnMaDG = new JPanel();
        pnMaDG.setLayout(new FlowLayout());
        JLabel lblMaDG = new JLabel("Mã độc giả: ");
        txtMaDG = new JTextField("DG");
        txtMaDG.setPreferredSize(new Dimension(340, 30));
        pnMaDG.add(lblMaDG);
        pnMaDG.add(txtMaDG);

        JPanel pnTenDG = new JPanel();
        pnTenDG.setLayout(new FlowLayout());
        JLabel lblTenDG = new JLabel("Tên độc giả: ");
        txtTenDG = new JTextField();
        txtTenDG.setPreferredSize(new Dimension(340, 30));
        pnTenDG.add(lblTenDG);
        pnTenDG.add(txtTenDG);

        JPanel pnNgayMuon = new JPanel();
        pnNgayMuon.setLayout(new FlowLayout());
        JLabel lblNgayMuon = new JLabel("Ngày mượn: ");
        choosedate = new JDateChooser();
        choosedate.setPreferredSize(new Dimension(340, 30));
        choosedate.setDateFormatString("yyyy-MM-dd");
        pnNgayMuon.add(lblNgayMuon);
        pnNgayMuon.add(choosedate);

        JPanel pnNgayHenTra = new JPanel();
        pnNgayHenTra.setLayout(new FlowLayout());
        JLabel lblNgayHenTra = new JLabel("Ngày hẹn trả: ");
        choosedate1 = new JDateChooser();
        choosedate1.setPreferredSize(new Dimension(340, 30));
        choosedate1.setDateFormatString("yyyy-MM-dd");
        pnNgayHenTra.add(lblNgayHenTra);
        pnNgayHenTra.add(choosedate1);

        JPanel pnSoSachCM = new JPanel();
        pnSoSachCM.setLayout(new FlowLayout());
        JLabel lblSoSachCM = new JLabel("Số sách mượn: ");
        txtSachMuon = new JTextField();
        txtSachMuon.setPreferredSize(new Dimension(340, 30));
        pnSoSachCM.add(lblSoSachCM);
        pnSoSachCM.add(txtSachMuon);

        JPanel pnThuThu = new JPanel();
        pnThuThu.setLayout(new FlowLayout());
        JLabel lblThuThu = new JLabel("Thủ thư: ");
        txtThuThu = new JTextField();
        txtThuThu.setPreferredSize(new Dimension(340, 30));
        pnThuThu.add(lblThuThu);
        pnThuThu.add(txtThuThu);


        pnHienThiChiTiet.add(pnTitle);
        pnHienThiChiTiet.add(pnMaPM);
        pnHienThiChiTiet.add(pnMaDG);
        //pnHienThiChiTiet.add(pnTenDG);
        pnHienThiChiTiet.add(pnNgayMuon);
        pnHienThiChiTiet.add(pnNgayHenTra);
        pnHienThiChiTiet.add(pnSoSachCM);
        pnHienThiChiTiet.add(pnThuThu);

        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 30);
        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.TRUETYPE_FONT, 15);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);

        lblThemPM.setFont(font2);
        lblMaPM.setFont(font4);
        lblMaDG.setFont(font4);
        lblTenDG.setFont(font4);
        lblNgayMuon.setFont(font4);
        lblNgayHenTra.setFont(font4);
        lblSoSachCM.setFont(font4);
        lblThuThu.setFont(font4);

        txtMaDG.setFont(font4);
        txtMaPhieu.setFont(font4);
        txtTenDG.setFont(font4);
        choosedate.setFont(font4);
        choosedate1.setFont(font4);
        txtSachMuon.setFont(font4);
        txtThuThu.setFont(font4);
        txtThuThu.setEditable(false);
        txtMaPhieu.setEditable(false);

        pnTitle.setBackground(new Color(241, 242, 246));
        lblThemPM.setForeground(new Color(48, 51, 107));
        pnMaPM.setBackground(new Color(241, 242, 246));
        pnMaDG.setBackground(new Color(241, 242, 246));
        pnTenDG.setBackground(new Color(241, 242, 246));
        pnNgayMuon.setBackground(new Color(241, 242, 246));
        pnNgayHenTra.setBackground(new Color(241, 242, 246));
        pnThuThu.setBackground(new Color(241, 242, 246));
        pnSoSachCM.setBackground(new Color(241, 242, 246));
        pnHinhAnh.setBackground(new Color(241, 242, 246));

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnThaoTac);
        btnThem = new JButton("THÊM");
        btnThem.setPreferredSize(new Dimension(110, 35));
        pnThaoTac.add(btnThem);
        pnThaoTac.setBackground(new Color(241, 242, 246));

        btnThem.setFont(font5);
        btnThem.setBackground(new Color(4, 191, 138));
        btnThem.setForeground(Color.white);
        btnThem.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));


        Border borderLogin = BorderFactory.createLineBorder(new Color(48, 51, 107));
        TitledBorder titleLogin = new TitledBorder(borderLogin, "");
        titleLogin.setTitleJustification(TitledBorder.LEFT);
        titleLogin.setTitleColor(Color.BLUE);
        pnHienThiThemPM.setBorder(titleLogin);

        lblMaDG.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblMaPM.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblTenDG.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblNgayHenTra.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblNgayMuon.setPreferredSize(lblSoSachCM.getPreferredSize());
        lblThuThu.setPreferredSize(lblSoSachCM.getPreferredSize());
    }

    public void showWindow() {
        this.setSize(900, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

}
