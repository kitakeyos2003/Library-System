package eaut.edu.vn.ui.dialog.returnreceipt;

import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.main.Application;
import eaut.edu.vn.ui.controls.PlaceholderTextField;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Search extends Dialog {

    JButton btnTimKiem, btnTraSach;
    PlaceholderTextField txtTimKiem;
    JTextField txtMaPhieu, txtMaDG, txtMaSach, txtNgayHenTra, txtTTSachMuon, txtThuThu;
    DefaultTableModel dtmPhieuMuon;
    JTable tblPhieuMuon;

    public Search(String title) {
        super(title, "QUẢN LÝ PHIẾU TRẢ");
    }

    @Override
    public void addEvents() {
        btnTimKiem.addActionListener(e -> search(txtTimKiem.getText()));

        tblPhieuMuon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblPhieuMuon.rowAtPoint(e.getPoint());
                int numcols = tblPhieuMuon.getColumnCount();

                for (int i = 0; i < numcols; i++) {
                    String str = (String) dtmPhieuMuon.getValueAt(row, i);
                    if (i == 0)
                        txtMaPhieu.setText(str);
                    if (i == 1)
                        txtMaDG.setText(str);
                    if (i == 2)
                        txtMaSach.setText(str);
                    if (i == 3)
                        txtNgayHenTra.setText(str);
                    if (i == 4)
                        txtTTSachMuon.setText(str);
                    if (i == 5)
                        txtThuThu.setText(str);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        txtTimKiem.addKeyListener(new KeyListener() {

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
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    search(txtTimKiem.getText());
                }
            }
        });
        txtTimKiem.addMouseListener(new MouseListener() {

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
                txtTimKiem.setText(null);

            }
        });
        btnTraSach.addActionListener(e -> {
            ReturnReceipt ts = new ReturnReceipt("Trả sách");
            ts.tentk = Application.account.getUsername();
            ts.MaDG = txtMaDG.getText();
            ts.MaPM = txtMaPhieu.getText();
            ts.MaSach = txtMaSach.getText();
            ts.NgayHenTra = txtNgayHenTra.getText();
            ts.TinhTrangSach = txtTTSachMuon.getText();
            ts.loadInfo();
            ts.showWindow();
        });
    }

    private void search(String text) {
        try {
            dtmPhieuMuon.setRowCount(0);
            if (Util.isNumber(text)) {
                String sql = "SELECT DISTINCT c.MaPM, a.MaDG, c.MaSach,a.NgayHenTra, c.TinhTrangSach, c.GhiChu, b.TenND " +
                        "FROM ctpm c " +
                        "JOIN phieumuon a ON a.MaPM = c.MaPM " +
                        "JOIN docgia d ON a.MaDG = d.MaDG " +
                        "JOIN sach e ON c.MaSach = e.MaSach " +
                        "LEFT JOIN taikhoan b ON a.User = b.User " +
                        "WHERE c.NgayTra IS NULL AND (a.MaPM = ? OR d.CCCD = ?)";
                int id = Integer.parseInt(text);
                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1, id);
                pre.setInt(2, id);
                ResultSet rs = pre.executeQuery();
                fillTable(rs);
                rs.close();
                pre.close();
                connection.close();
            } else {
                String sql = "SELECT DISTINCT c.MaPM, a.MaDG, c.MaSach, a.NgayHenTra, c.TinhTrangSach, c.GhiChu, b.TenND " +
                        "FROM ctpm c " +
                        "JOIN phieumuon a ON a.MaPM = c.MaPM " +
                        "JOIN docgia d ON a.MaDG = d.MaDG " +
                        "JOIN sach e ON c.MaSach = e.MaSach " +
                        "LEFT JOIN taikhoan b ON a.User = b.User " +
                        "WHERE c.NgayTra IS NULL AND (d.TenDG LIKE ? OR e.TenSach LIKE ?)";

                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
                String like = '%' + text + '%';
                pre.setString(1, like);
                pre.setString(2, like);
                ResultSet rs = pre.executeQuery();
                fillTable(rs);
                rs.close();
                pre.close();
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fillTable(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String maphieu = rs.getString("MaPM");
            String madocgia = rs.getString("MaDG");
            String masach = rs.getString("MaSach");
            String ngayhentra = rs.getString("NgayHenTra");
            String ttsachmuon = rs.getString("TinhTrangSach");
            String thuthu = rs.getString("TenND");

            Vector<String> vec = new Vector<String>();
            vec.add(maphieu);
            vec.add(madocgia);
            vec.add(masach);
            vec.add(ngayhentra);
            vec.add(ttsachmuon);
            vec.add(thuthu);
            dtmPhieuMuon.addRow(vec);
        }
    }

    @Override
    public void initComponents() {
        JPanel pnHienThiTimKiemPhieu = new JPanel();
        pnHienThiTimKiemPhieu.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiTimKiemPhieu, BorderLayout.CENTER);

        JPanel pnThanhTimKiem = new JPanel();
        pnThanhTimKiem.setLayout(new BorderLayout());


        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new FlowLayout());
        btnTimKiem = new JButton("TÌM KIẾM");
        btnTimKiem.setFocusPainted(false);
        pnTimKiem.add(btnTimKiem);
        pnThanhTimKiem.add(pnTimKiem, BorderLayout.EAST);
        btnTimKiem.setPreferredSize(new Dimension(130, 35));
        pnTimKiem.setBackground(new Color(4, 191, 138));
        btnTimKiem.setBackground(new Color(4, 191, 138));
        btnTimKiem.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        Font font7 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        btnTimKiem.setForeground(Color.WHITE);
        btnTimKiem.setFont(font7);

        JPanel pnTextTimKiem = new JPanel();
        pnTextTimKiem.setLayout(new BorderLayout());
        txtTimKiem = new PlaceholderTextField();
        txtTimKiem.setPlaceholder("Nhập mã phiếu, tên sách, tên độc giả, CCCD...");
        pnTextTimKiem.add(txtTimKiem);
        pnThanhTimKiem.add(pnTextTimKiem, BorderLayout.CENTER);
        txtTimKiem.setPreferredSize(new Dimension(100, 20));
        Font font6 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 18);
        txtTimKiem.setFont(font6);
        txtTimKiem.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));

        JPanel pnIcon = new JPanel();
        pnIcon.setLayout(new FlowLayout());
        JLabel lblIcon = new JLabel();
        pnIcon.add(lblIcon);
        pnIcon.setBackground(new Color(4, 191, 138));
        lblIcon.setIcon(Util.loadImage("tim.png"));
        pnThanhTimKiem.add(pnIcon, BorderLayout.WEST);


        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        pnHienThiTimKiemPhieu.add(pnThongTin, BorderLayout.CENTER);


        pnThongTin.add(pnThanhTimKiem, BorderLayout.NORTH);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnThongTin.add(pnHienThiChiTiet, BorderLayout.WEST);

        JPanel pnTieuDe1 = new JPanel();
        pnTieuDe1.setLayout(new FlowLayout());
        JLabel lblTieuDe1 = new JLabel("THÔNG TIN CHI TIẾT");
        pnTieuDe1.add(lblTieuDe1);
        pnHienThiChiTiet.add(pnTieuDe1, BorderLayout.NORTH);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 16);
        lblTieuDe1.setFont(font4);
        pnTieuDe1.setBackground(new Color(2, 115, 83));
        lblTieuDe1.setForeground(Color.WHITE);

        JPanel pnMaPhieu = new JPanel();
        pnMaPhieu.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnMaPhieu);
        JLabel lblMaPhieu = new JLabel("Mã phiếu: ");
        txtMaPhieu = new JTextField();
        txtMaPhieu.setPreferredSize(new Dimension(240, 22));
        pnMaPhieu.add(lblMaPhieu);
        pnMaPhieu.add(txtMaPhieu);

        JPanel pnMaDG = new JPanel();
        pnMaDG.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnMaDG);
        JLabel lblMaDG = new JLabel("Mã độc giả: ");
        txtMaDG = new JTextField();
        txtMaDG.setPreferredSize(new Dimension(240, 22));
        pnMaDG.add(lblMaDG);
        pnMaDG.add(txtMaDG);

        JPanel pnMaSach = new JPanel();
        pnMaSach.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnMaSach);
        JLabel lblMaSach = new JLabel("Mã sách: ");
        txtMaSach = new JTextField();
        txtMaSach.setPreferredSize(new Dimension(240, 22));
        pnMaSach.add(lblMaSach);
        pnMaSach.add(txtMaSach);


        JPanel pnNgayHenTra = new JPanel();
        pnNgayHenTra.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnNgayHenTra);
        JLabel lblNgayHenTra = new JLabel("Ngày hẹn trả: ");
        txtNgayHenTra = new JTextField();
        txtNgayHenTra.setPreferredSize(new Dimension(240, 22));
        pnNgayHenTra.add(lblNgayHenTra);
        pnNgayHenTra.add(txtNgayHenTra);

        JPanel pnTTSachMuon = new JPanel();
        pnTTSachMuon.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTTSachMuon);
        JLabel lblTTSachMuon = new JLabel("TT sách mượn: ");
        txtTTSachMuon = new JTextField();
        txtTTSachMuon.setPreferredSize(new Dimension(240, 22));
        pnTTSachMuon.add(lblTTSachMuon);
        pnTTSachMuon.add(txtTTSachMuon);

        JPanel pnThuThu = new JPanel();
        pnThuThu.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnThuThu);
        JLabel lblThuThuNhanSach = new JLabel("Thủ thư nhận sách: ");
        txtThuThu = new JTextField();
        txtThuThu.setPreferredSize(new Dimension(240, 22));
        pnThuThu.add(lblThuThuNhanSach);
        pnThuThu.add(txtThuThu);

        JPanel pnAN3 = new JPanel();
        pnAN3.setLayout(new FlowLayout());
        JLabel lblAN3 = new JLabel();
        pnAN3.add(lblAN3);
        pnAN3.setBackground(new Color(255, 255, 255));
        lblAN3.setIcon(Util.loadImage("suy.png"));
        pnHienThiChiTiet.add(pnAN3);

        JPanel pnTraSach = new JPanel();
        pnTraSach.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTraSach);
        btnTraSach = new JButton("TRẢ SÁCH");
        pnTraSach.add(btnTraSach);
        btnTraSach.setBackground(new Color(4, 191, 138));
        pnTraSach.setBackground(new Color(255, 255, 255));
        btnTraSach.setPreferredSize(new Dimension(180, 30));
        Font font8 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 17);
        btnTraSach.setFont(font8);
        btnTraSach.setForeground(Color.white);
        btnTraSach.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));


        JPanel pnBangThongKe = new JPanel();
        pnBangThongKe.setLayout(new BorderLayout());

        dtmPhieuMuon = new DefaultTableModel();
        dtmPhieuMuon.addColumn("Mã phiếu");
        dtmPhieuMuon.addColumn("Mã độc giả");
        dtmPhieuMuon.addColumn("Mã sách");
        dtmPhieuMuon.addColumn("Ngày hẹn trả");
        dtmPhieuMuon.addColumn("TT Sách mượn");
        dtmPhieuMuon.addColumn("Thủ thư nhận sách");
        tblPhieuMuon = new JTable(dtmPhieuMuon);
        JScrollPane sc = new JScrollPane(tblPhieuMuon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc.setPreferredSize(new Dimension(400, 320));
        pnBangThongKe.add(sc, BorderLayout.CENTER);
        pnThongTin.add(pnBangThongKe, BorderLayout.CENTER);

        JPanel pnTieuDe2 = new JPanel();
        JLabel lblTieuDe2 = new JLabel("DANH MỤC PHIẾU TÌM KIẾM");
        pnTieuDe2.add(lblTieuDe2);
        pnBangThongKe.add(pnTieuDe2, BorderLayout.NORTH);
        lblTieuDe2.setFont(font4);
        pnTieuDe2.setBackground(new Color(2, 115, 83));
        lblTieuDe2.setForeground(Color.WHITE);


        lblMaDG.setFont(font4);
        lblMaPhieu.setFont(font4);
        lblMaSach.setFont(font4);
        lblNgayHenTra.setFont(font4);
        lblTTSachMuon.setFont(font4);
        lblThuThuNhanSach.setFont(font4);

        lblMaDG.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblMaPhieu.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblMaSach.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblNgayHenTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblTTSachMuon.setPreferredSize(lblThuThuNhanSach.getPreferredSize());

        pnMaSach.setBackground(new Color(255, 255, 255));
        pnMaDG.setBackground(new Color(255, 255, 255));
        pnMaPhieu.setBackground(new Color(255, 255, 255));
        pnNgayHenTra.setBackground(new Color(255, 255, 255));
        pnTTSachMuon.setBackground(new Color(255, 255, 255));
        pnThuThu.setBackground(new Color(255, 255, 255));

        txtMaDG.setEditable(false);
        txtMaPhieu.setEditable(false);
        txtMaSach.setEditable(false);
        txtNgayHenTra.setEditable(false);
        txtThuThu.setEditable(false);
        txtTTSachMuon.setEditable(false);

    }

    public void showWindow() {
        this.setSize(1300, 780);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }
}
