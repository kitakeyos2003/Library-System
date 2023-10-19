package eaut.edu.vn.ui.dialog.loan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.main.Application;
import eaut.edu.vn.model.Reader;
import eaut.edu.vn.service.ReaderService;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.util.Util;


public class AddLoan extends Dialog {

    JTextField txtTenDG, txtSachMuon, txtThuThu;
    JComboBox<Reader> cbDocGia;
    JButton btnThem;
    JDateChooser choosedate, choosedate1;

    public AddLoan(String title) {
        super(title);
        setHeader(new Header("QUẢN LÝ PHIẾU MƯỢN"));
        setFooter(new Footer());
        fill();
    }

    public void fill() {
        List<Reader> readers = ReaderService.getInstance().getAll();
        for (Reader reader : readers) {
            cbDocGia.addItem(reader);
        }
        txtThuThu.setText(Application.account.getUsername());
    }

    @Override
    public void addEvents() {
        btnThem.addActionListener(e -> {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (choosedate.getDate() == null || choosedate1.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Không được để trống");
                return;
            }
            String datemuon = df.format(choosedate.getDate());
            String datehentra = df.format(choosedate1.getDate());
            Reader reader = (Reader) cbDocGia.getSelectedItem();
            if (reader == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn độc giả");
                return;
            }
            if (txtSachMuon.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Không được để trống");
                return;
            }
            int soluong2 = 0;
            try {

                Connection connection = DbManager.getInstance().getConnection();
                String sqldocgia1 = "Select MatSach from docgia where MaDG=?";
                PreparedStatement prex = connection.prepareStatement(sqldocgia1);
                prex.setInt(1, reader.getId());
                ResultSet b = prex.executeQuery();
                if (b.next()) {
                    soluong2 = b.getInt(1);
                }
                b.close();
                prex.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (soluong2 == 3) {
                JOptionPane.showMessageDialog(null, "Bạn đã làm mất sách 3 lần. Bạn không được mượn sách nữa.Thanks!");
                dispose();
                return;
            }
            try {
                String sql = "INSERT INTO `phieumuon`(`MaDG`, `NgayMuon`, `NgayHenTra`, `SoLuongMuon`, `User`) VALUES (?,?,?,?,?)";
                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pre.setInt(1, reader.getId());
                pre.setString(2, datemuon);
                pre.setString(3, datehentra);
                pre.setString(4, txtSachMuon.getText());
                pre.setString(5, txtThuThu.getText());
                int affectedRows = pre.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet generatedKeys = pre.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công");
                        dispose();
                        int soluong1 = Integer.parseInt(txtSachMuon.getText());
                        for (int i = 0; i < soluong1; i++) {
                            BookBorrowStatus qlts = new BookBorrowStatus("Thêm Sách");
                            qlts.MaPM = id;
                            qlts.user = Application.account.getUsername();
                            qlts.fill();
                            qlts.showWindow();
                        }
                    }
                    generatedKeys.close();
                }
                pre.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
    }


    @Override
    public void initComponents() {
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

        JPanel pnMaDG = new JPanel();
        pnMaDG.setLayout(new FlowLayout());
        JLabel lblMaDG = new JLabel("Mã độc giả: ");
        cbDocGia = new JComboBox<>();
        cbDocGia.setPreferredSize(new Dimension(340, 30));
        pnMaDG.add(lblMaDG);
        pnMaDG.add(cbDocGia);

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
        lblMaDG.setFont(font4);
        lblTenDG.setFont(font4);
        lblNgayMuon.setFont(font4);
        lblNgayHenTra.setFont(font4);
        lblSoSachCM.setFont(font4);
        lblThuThu.setFont(font4);

        cbDocGia.setFont(font4);
        txtTenDG.setFont(font4);
        choosedate.setFont(font4);
        choosedate1.setFont(font4);
        txtSachMuon.setFont(font4);
        txtThuThu.setFont(font4);
        txtThuThu.setEditable(false);

        pnTitle.setBackground(new Color(241, 242, 246));
        lblThemPM.setForeground(new Color(48, 51, 107));
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
