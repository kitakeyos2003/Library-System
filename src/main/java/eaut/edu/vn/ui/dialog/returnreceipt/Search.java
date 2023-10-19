package eaut.edu.vn.ui.dialog.returnreceipt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.controls.PlaceholderTextField;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.service.LoanDetailService;

import eaut.edu.vn.model.LoanDetail;
import eaut.edu.vn.util.Util;

public class Search extends Dialog {
    
    JButton btnTimKiem, btnTraSach;
    PlaceholderTextField txtTimKiem;
    JTextField txtMaPhieu, txtMaDG, txtMaSach, txtNgayHenTra, txtNgayTra, txtTTSachMuon, txtTTSachTra, txtThuThu, txtGhiChu;
    DefaultTableModel dtmPhieuMuon;
    JTable tblPhieuMuon;
    List<LoanDetail> dsctpm;
    DefaultTableModel dtmPhieuTra, dtmPhieuChuaTra;
    JTable tblPhieuTra, tblPhieuChuaTra;

    public Search(String title) {
        super(title);
        setHeader(new Header("QUẢN LÝ PHIẾU TRẢ"));
        setFooter(new Footer());
    }

    private void hienThiPhieuMuonDaTra() {
        dsctpm = LoanDetailService.getInstance().getAll();
        dtmPhieuTra.setRowCount(0);
        for (LoanDetail ctpm : dsctpm) {
            if (ctpm.getReturnDate() != null) {
                Vector<Object> vec = new Vector<Object>();
                vec.add(ctpm.getLoanId());
                vec.add(ctpm.getBookId());
                vec.add(ctpm.getNote());
				
				/*vec.add(ctpm.getNgayTra());
				vec.add(ctpm.getTinhtrangsach());
				vec.add(ctpm.getTinhtrangtra());
				vec.add(ctpm.getUser());
				*/
                dtmPhieuTra.addRow(vec);
            }
        }

    }


    private void hienThiPhieuMuonChuaTra() {
        LoanDetailService ctpmsv = new LoanDetailService();
        dsctpm = ctpmsv.getAll();
        dtmPhieuChuaTra.setRowCount(0);
        for (LoanDetail ctpm : dsctpm) {
            if (ctpm.getReturnDate() == null) {
                Vector<Object> vec = new Vector<Object>();
                vec.add(ctpm.getLoanId());
                vec.add(ctpm.getBookId());
                vec.add(null);
                vec.add(ctpm.getBorrowedStatus());
                vec.add(null);
                vec.add(null);
                vec.add(ctpm.getNote());
                dtmPhieuChuaTra.addRow(vec);
            }
        }

    }

    @Override
    public void addEvents() {
        btnTimKiem.addActionListener(e -> {
            // TODO Auto-generated method stub
            dtmPhieuMuon.setRowCount(0);
            String phieutra = txtTimKiem.getText();
            try {
                String sql = "Select c.MaPM,a.MaDG,c.MaSach,c.NgayTra,a.NgayHenTra,c.TinhTrangSach,c.TinhTrangTra,c.GhiChu,b.TenND FROM ctpm c,phieumuon a,taikhoan b  where a.MaPM=c.MaPM and b.User=c.User HAVING c.MaPM like ?";

                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1, '%' + txtTimKiem.getText() + '%');
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    String maphieu = rs.getString(1);
                    String madocgia = rs.getString(2);
                    String masach = rs.getString(3);
                    String ngayhentra = rs.getString(4);
                    String ngaytra = rs.getString(5);
                    String ttsachmuon = rs.getString(6);
                    String ttsachtra = rs.getString(7);
                    String thuthu = rs.getString(9);
                    String ghichu = rs.getString(8);

                    Vector<String> vec = new Vector<String>();
                    vec.add(maphieu);
                    vec.add(madocgia);
                    vec.add(masach);
                    vec.add(ngayhentra);
                    vec.add(ngaytra);
                    vec.add(ttsachmuon);
                    vec.add(ttsachtra);
                    vec.add(thuthu);
                    vec.add(ghichu);
                    dtmPhieuMuon.addRow(vec);
                }
                rs.close();
                pre.close();
                connection.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Hi");

                ex.printStackTrace();
            }
        });

        tblPhieuMuon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblPhieuMuon.rowAtPoint(e.getPoint());
                int col = tblPhieuMuon.columnAtPoint(e.getPoint());
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
                        txtNgayTra.setText(str);
                    if (i == 5)
                        txtTTSachMuon.setText(str);
                    if (i == 6)
                        txtTTSachTra.setText(str);
                    if (i == 7)
                        txtThuThu.setText(str);
                    if (i == 8)
                        txtGhiChu.setText(str);
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
                    dtmPhieuMuon.setRowCount(0);
                    String tensach = txtTimKiem.getText();
                    try {
                        String sql = "Select c.MaPM,a.MaDG,c.MaSach,c.NgayTra,a.NgayHenTra,c.TinhTrangSach,c.TinhTrangTra,c.GhiChu,b.TenND FROM ctpm c,phieumuon a,taikhoan b  where a.MaPM=c.MaPM and b.User=c.User HAVING c.MaPM like ?";

                        Connection connection = DbManager.getInstance().getConnection();
                        PreparedStatement pre = connection.prepareStatement(sql);
                        pre.setString(1, '%' + txtTimKiem.getText() + '%');
                        ResultSet rs = pre.executeQuery();
                        while (rs.next()) {
                            String maphieu = rs.getString(1);
                            String madocgia = rs.getString(2);
                            String masach = rs.getString(3);
                            String ngayhentra = rs.getString(4);
                            String ngaytra = rs.getString(5);
                            String ttsachmuon = rs.getString(6);
                            String ttsachtra = rs.getString(7);
                            String thuthu = rs.getString(9);
                            String ghichu = rs.getString(8);

                            Vector<String> vec = new Vector<String>();
                            vec.add(maphieu);
                            vec.add(madocgia);
                            vec.add(masach);
                            vec.add(ngayhentra);
                            vec.add(ngaytra);
                            vec.add(ttsachmuon);
                            vec.add(ttsachtra);
                            vec.add(thuthu);
                            vec.add(ghichu);


                            dtmPhieuMuon.addRow(vec);
                        }
                        rs.close();
                        pre.close();
                        connection.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
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
            // TODO Auto-generated method stub
            if (txtNgayTra.getText().length() != 0) {
                JOptionPane.showMessageDialog(null, "Phiếu mượn đã trả sách rồi");
                return;
            }
            ReturnReceipt ts = new ReturnReceipt("Trả sách");
            ts.MaDG = txtMaDG.getText();
            ts.MaPM = txtMaPhieu.getText();
            ts.MaSach = txtMaSach.getText();
            ts.NgayHenTra = txtNgayHenTra.getText();
            ts.TinhTrangSach = txtTTSachMuon.getText();
            ts.hienThi();
            ts.showWindow();
        });
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
        txtTimKiem.setPlaceholder("Nhập mã phiếu...");
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

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblTimKiemSach = new JLabel("TÌM KIẾM PHIẾU TRẢ");
        pnTitle.add(lblTimKiemSach);
        pnHienThiTimKiemPhieu.add(pnTitle, BorderLayout.NORTH);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 25);
        lblTimKiemSach.setFont(font5);
        lblTimKiemSach.setForeground(new Color(4, 191, 138));


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

        JPanel pnNgayTra = new JPanel();
        pnNgayTra.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnNgayTra);
        JLabel lblNgayTra = new JLabel("Ngày trả: ");
        txtNgayTra = new JTextField();
        txtNgayTra.setPreferredSize(new Dimension(240, 22));
        pnNgayTra.add(lblNgayTra);
        pnNgayTra.add(txtNgayTra);

        JPanel pnTTSachMuon = new JPanel();
        pnTTSachMuon.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTTSachMuon);
        JLabel lblTTSachMuon = new JLabel("TT sách mượn: ");
        txtTTSachMuon = new JTextField();
        txtTTSachMuon.setPreferredSize(new Dimension(240, 22));
        pnTTSachMuon.add(lblTTSachMuon);
        pnTTSachMuon.add(txtTTSachMuon);

        JPanel pnTTSachTra = new JPanel();
        pnTTSachTra.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTTSachTra);
        JLabel lblTTSachTra = new JLabel("TT sách trả: ");
        txtTTSachTra = new JTextField();
        txtTTSachTra.setPreferredSize(new Dimension(240, 22));
        pnTTSachTra.add(lblTTSachTra);
        pnTTSachTra.add(txtTTSachTra);

        JPanel pnThuThu = new JPanel();
        pnThuThu.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnThuThu);
        JLabel lblThuThuNhanSach = new JLabel("Thủ thư nhận sách: ");
        txtThuThu = new JTextField();
        txtThuThu.setPreferredSize(new Dimension(240, 22));
        pnThuThu.add(lblThuThuNhanSach);
        pnThuThu.add(txtThuThu);

        JPanel pnGhiChu = new JPanel();
        pnGhiChu.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnGhiChu);
        JLabel lblGhiChu = new JLabel("Ghi chú: ");
        txtGhiChu = new JTextField();
        txtGhiChu.setPreferredSize(new Dimension(240, 22));
        pnGhiChu.add(lblGhiChu);
        pnGhiChu.add(txtGhiChu);

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
        dtmPhieuMuon.addColumn("Ngày trả");
        dtmPhieuMuon.addColumn("TT Sách mượn");
        dtmPhieuMuon.addColumn("TT Sách trả");
        dtmPhieuMuon.addColumn("Thủ thư nhận sách");
        dtmPhieuMuon.addColumn("Ghi chú");
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


        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        lblMaDG.setFont(font4);
        lblMaPhieu.setFont(font4);
        lblMaSach.setFont(font4);
        lblNgayTra.setFont(font4);
        lblNgayHenTra.setFont(font4);
        lblTTSachTra.setFont(font4);
        lblTTSachMuon.setFont(font4);
        lblThuThuNhanSach.setFont(font4);
        lblGhiChu.setFont(font4);

        lblMaDG.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblMaPhieu.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblMaSach.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblNgayHenTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblNgayTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblGhiChu.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblTTSachMuon.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblTTSachTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());

        pnMaSach.setBackground(new Color(255, 255, 255));
        pnMaDG.setBackground(new Color(255, 255, 255));
        pnMaPhieu.setBackground(new Color(255, 255, 255));
        pnNgayHenTra.setBackground(new Color(255, 255, 255));
        pnNgayTra.setBackground(new Color(255, 255, 255));
        pnGhiChu.setBackground(new Color(255, 255, 255));
        pnTTSachMuon.setBackground(new Color(255, 255, 255));
        pnThuThu.setBackground(new Color(255, 255, 255));
        pnTTSachTra.setBackground(new Color(255, 255, 255));

        txtMaDG.setEditable(false);
        txtMaPhieu.setEditable(false);
        txtMaSach.setEditable(false);
        txtNgayHenTra.setEditable(false);
        txtNgayTra.setEditable(false);
        txtThuThu.setEditable(false);
        txtTTSachMuon.setEditable(false);
        txtTTSachTra.setEditable(false);
        txtGhiChu.setEditable(false);

    }

    public void showWindow() {
        this.setSize(1300, 780);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }
}
