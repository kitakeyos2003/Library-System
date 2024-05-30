package eaut.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import eaut.edu.vn.interfaces.ITable;
import eaut.edu.vn.main.Application;
import eaut.edu.vn.service.LoanDetailService;

import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.CustomFrame;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.returnreceipt.Search;
import eaut.edu.vn.ui.dialog.returnreceipt.ReturnReceipt;
import eaut.edu.vn.model.LoanDetail;
import eaut.edu.vn.util.Util;

public class ReturnManager extends CustomFrame implements ITable {
    
    public int thongke = 0;
    JTextField txtMaPhieu, txtMaDG, txtMaSach, txtNgayHenTra, txtNgayTra, txtTTSachMuon, txtTTSachTra, txtThuThuNhanSach, txtGhiChu;
    JButton btnTraSach, btnQuayLai, btnTimKiem;
    DefaultTableModel dtmPhieuTra, dtmPhieuChuaTra;
    JTable tblPhieuTra, tblPhieuChuaTra;
    List<LoanDetail> dsctpm;

    public ReturnManager(String tieude) {
        super(tieude);
        this.setSize(1130, 775);
        setHeader(new Header("QUẢN LÝ PHIẾU TRẢ"));
        setFooter(new Footer());
        fillTable();
    }

    @Override
    public void fillTable() {
        dsctpm = LoanDetailService.getInstance().getAll();
        dtmPhieuTra.setRowCount(0);
        for (LoanDetail ctpm : dsctpm) {
            if (ctpm.getReturnDate() != null) {
                Vector<Object> vec = new Vector<Object>();
                vec.add(ctpm.getLoanId());
                vec.add(ctpm.getBookId());
                vec.add(ctpm.getReturnDate());
                vec.add(ctpm.getBorrowedStatus());
                vec.add(ctpm.getReturnStatus());
                vec.add(ctpm.getUserName());
                vec.add(ctpm.getNote());
                dtmPhieuTra.addRow(vec);
            }
        }
        dtmPhieuChuaTra.setRowCount(0);
        for (LoanDetail ctpm : dsctpm) {
            if (ctpm.getReturnDate() == null) {
                Vector<Object> vec = new Vector<Object>();
                vec.add(ctpm.getLoanId());
                vec.add(ctpm.getBookId());
                vec.add(ctpm.getBorrowedStatus());
                dtmPhieuChuaTra.addRow(vec);
            }
        }
    }

    public void addEvents() {

        btnQuayLai.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (thongke == 1) {
                StatisticAnalyzer ui = Application.SINGLETON.STATISTIC_ANALYZER;
                dispose();
                thongke = 0;
                return;
            }
            int phanquyen = Application.account.getRole();
            if (phanquyen == 1) {
                AdminManager ql = Application.SINGLETON.ADMIN_MANAGER;
                ql.showWindow();
                dispose();
            }
            if (phanquyen == 2) {
                LibrarianManager ql = Application.SINGLETON.LIBRARIAN_MANAGER;
                ql.showWindow();
                dispose();
            }
        });

        tblPhieuChuaTra.addMouseListener(new MouseListener() {

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
                int n = tblPhieuChuaTra.getSelectedRow();
                try {
                    String sql = "Select c.MaPM,a.MaDG,c.MaSach,c.NgayTra,a.NgayHenTra,c.TinhTrangSach,c.TinhTrangTra,c.GhiChu,b.TenND FROM ctpm c,phieumuon a,taikhoan b  where a.MaPM=c.MaPM and b.User=a.User HAVING c.MaSach=? and c.MaPM=?";
                    Connection connection = DbManager.getInstance().getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setString(1, String.valueOf(dtmPhieuChuaTra.getValueAt(n, 1)));
                    ps.setString(2, String.valueOf(dtmPhieuChuaTra.getValueAt(n, 0)));
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        txtMaPhieu.setText(rs.getString(1));
                        txtMaDG.setText(rs.getString(2));
                        txtMaSach.setText(rs.getString(3));
                        txtNgayTra.setText(rs.getString(4));
                        txtNgayHenTra.setText(rs.getString(5));
                        txtTTSachMuon.setText(rs.getString(6));
                        txtTTSachTra.setText(rs.getString(7));
                        txtGhiChu.setText(rs.getString(8));
                        txtThuThuNhanSach.setText(null);
                    }
                    rs.close();
                    ps.close();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                tblPhieuTra.setSelectionMode(0);

            }
        });

        tblPhieuTra.addMouseListener(new MouseListener() {

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
                int n = tblPhieuTra.getSelectedRow();

                try {
                    String sql = "Select c.MaPM,a.MaDG,c.MaSach,c.NgayTra,a.NgayHenTra,c.TinhTrangSach,c.TinhTrangTra,c.GhiChu,b.TenND FROM ctpm c,phieumuon a,taikhoan b  where a.MaPM=c.MaPM and b.User=c.User HAVING c.MaSach=? and c.MaPM=?";
                    Connection connection = DbManager.getInstance().getConnection();
                    PreparedStatement pre = connection.prepareStatement(sql);
                    pre.setString(1, String.valueOf(dtmPhieuTra.getValueAt(n, 1)));
                    pre.setString(2, String.valueOf(dtmPhieuTra.getValueAt(n, 0)));
                    ResultSet rs = pre.executeQuery();
                    while (rs.next()) {
                        txtMaPhieu.setText(rs.getString(1));
                        txtMaDG.setText(rs.getString(2));
                        txtMaSach.setText(rs.getString(3));
                        txtNgayTra.setText(rs.getString(4));
                        txtNgayHenTra.setText(rs.getString(5));
                        txtTTSachMuon.setText(rs.getString(6));
                        txtTTSachTra.setText(rs.getString(7));
                        txtGhiChu.setText(rs.getString(8));
                        txtThuThuNhanSach.setText(rs.getString(9));
                    }
                    rs.close();
                    pre.close();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                tblPhieuChuaTra.setSelectionMode(0);
            }
        });
        btnTraSach.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (txtNgayTra.getText().length() != 0) {
                JOptionPane.showMessageDialog(null, "Phiếu mượn đã trả sách rồi");
                return;
            }
            ReturnReceipt ts = new ReturnReceipt("Trả sách");
            ts.tentk = Application.account.getUsername();
            ts.MaDG = txtMaDG.getText();
            ts.MaPM = txtMaPhieu.getText();
            ts.MaSach = txtMaSach.getText();
            ts.NgayHenTra = txtNgayHenTra.getText();
            ts.TinhTrangSach = txtTTSachMuon.getText();
            ts.loadInfo();
            ts.showWindow();
            fillTable();
        });
        btnTimKiem.addActionListener(e -> {
            Search timphieu = new Search("Tìm kiếm thông tin sách");
            timphieu.showWindow();
            dtmPhieuChuaTra.setRowCount(0);
            dtmPhieuTra.setRowCount(0);
            fillTable();
        });

    }

    @Override
    public void initComponents() {
        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        mainPanel.add(pnThongTin, BorderLayout.CENTER);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BorderLayout());
        pnThongTin.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnThongTinChiTiet = new JPanel();
        pnThongTinChiTiet.setLayout(new BoxLayout(pnThongTinChiTiet, BoxLayout.Y_AXIS));
        pnHienThiChiTiet.add(pnThongTinChiTiet, BorderLayout.WEST);
        pnThongTinChiTiet.setPreferredSize(new Dimension(450, 0));

        JPanel pnTieuDe1 = new JPanel();
        JLabel lblTieuDe1 = new JLabel("THÔNG TIN CHI TIẾT");
        pnTieuDe1.add(lblTieuDe1);
        pnThongTinChiTiet.add(pnTieuDe1, BorderLayout.NORTH);

        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 18);
        lblTieuDe1.setFont(font2);
        pnTieuDe1.setBackground(new Color(2, 115, 83));
        lblTieuDe1.setForeground(Color.WHITE);

        JPanel pnMaPhieu = new JPanel();
        pnMaPhieu.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnMaPhieu);
        JLabel lblMaPhieu = new JLabel("Mã phiếu: ");
        txtMaPhieu = new JTextField();
        txtMaPhieu.setPreferredSize(new Dimension(240, 22));
        pnMaPhieu.add(lblMaPhieu);
        pnMaPhieu.add(txtMaPhieu);

        JPanel pnMaDG = new JPanel();
        pnMaDG.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnMaDG);
        JLabel lblMaDG = new JLabel("Mã độc giả: ");
        pnMaDG.add(lblMaDG);
        txtMaDG = new JTextField();
        txtMaDG.setPreferredSize(new Dimension(240, 22));
        pnMaDG.add(txtMaDG);

        JPanel pnMaSach = new JPanel();
        pnMaSach.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnMaSach);
        JLabel lblMaSach = new JLabel("Mã sách: ");
        pnMaSach.add(lblMaSach);
        txtMaSach = new JTextField();
        txtMaSach.setPreferredSize(new Dimension(240, 22));
        pnMaSach.add(txtMaSach);

        JPanel pnNgayHenTra = new JPanel();
        pnNgayHenTra.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnNgayHenTra);
        JLabel lblNgayHenTra = new JLabel("Ngày hẹn trả: ");
        txtNgayHenTra = new JTextField();
        txtNgayHenTra.setPreferredSize(new Dimension(240, 22));
        pnNgayHenTra.add(lblNgayHenTra);
        pnNgayHenTra.add(txtNgayHenTra);

        JPanel pnNgayTra = new JPanel();
        pnNgayTra.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnNgayTra);
        JLabel lblNgayTra = new JLabel("Ngày trả: ");
        txtNgayTra = new JTextField();
        txtNgayTra.setPreferredSize(new Dimension(240, 22));
        pnNgayTra.add(lblNgayTra);
        pnNgayTra.add(txtNgayTra);

        JPanel pnTinhTrangSach = new JPanel();
        pnTinhTrangSach.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnTinhTrangSach);
        JLabel lblTinhTrangSach = new JLabel("TT sách mượn: ");
        txtTTSachMuon = new JTextField();
        txtTTSachMuon.setPreferredSize(new Dimension(240, 22));
        pnTinhTrangSach.add(lblTinhTrangSach);
        pnTinhTrangSach.add(txtTTSachMuon);

        JPanel pnTinhTrangTra = new JPanel();
        pnTinhTrangTra.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnTinhTrangTra);
        JLabel lblTinhTrangTra = new JLabel("TT sách trả: ");
        txtTTSachTra = new JTextField();
        txtTTSachTra.setPreferredSize(new Dimension(240, 22));
        pnTinhTrangTra.add(lblTinhTrangTra);
        pnTinhTrangTra.add(txtTTSachTra);

        JPanel pnThuThuNhanSach = new JPanel();
        pnThuThuNhanSach.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnThuThuNhanSach);
        JLabel lblThuThuNhanSach = new JLabel("Thủ thư nhận sách: ");
        txtThuThuNhanSach = new JTextField();
        txtThuThuNhanSach.setPreferredSize(new Dimension(240, 22));
        pnThuThuNhanSach.add(lblThuThuNhanSach);
        pnThuThuNhanSach.add(txtThuThuNhanSach);

        JPanel pnGhiChu = new JPanel();
        pnGhiChu.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnGhiChu);
        JLabel lblGhiChu = new JLabel("Ghi chú: ");
        txtGhiChu = new JTextField();
        txtGhiChu.setPreferredSize(new Dimension(240, 22));
        pnGhiChu.add(lblGhiChu);
        pnGhiChu.add(txtGhiChu);

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new BorderLayout());
        pnHienThiChiTiet.add(pnThaoTac, BorderLayout.CENTER);

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

        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(new Color(4, 191, 138));
        lblHinhAnh.setIcon(Util.loadImage("nenanh.png"));
        pnHinhAnh.add(lblHinhAnh);

        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new FlowLayout());
        btnTimKiem = new JButton();
        btnTimKiem.setBackground(new Color(241, 242, 246));
        pnTimKiem.add(btnTimKiem);
        pnTimKiem.setBackground(new Color(241, 242, 246));

        JPanel pnTraSach = new JPanel();
        pnTraSach.setLayout(new FlowLayout());
        btnTraSach = new JButton();
        pnTraSach.add(btnTraSach);
        btnTraSach.setBackground(new Color(241, 242, 246));
        pnTraSach.setBackground(new Color(241, 242, 246));

        pnChucNang.add(pnHinhAnh);
        pnChucNang.add(pnTimKiem);
        pnChucNang.add(pnTraSach);

        pnThaoTac.add(pnChucNang, BorderLayout.CENTER);
        JPanel pnThongKePhieuChuaTra = new JPanel();
        pnThongKePhieuChuaTra.setLayout(new BorderLayout());
        pnHienThiChiTiet.add(pnThongKePhieuChuaTra, BorderLayout.EAST);

        JPanel pnTieuDe2 = new JPanel();
        pnThongKePhieuChuaTra.add(pnTieuDe2, BorderLayout.NORTH);
        JLabel lblTieuDe2 = new JLabel("DANH SÁCH PHIẾU CHƯA TRẢ");
        lblTieuDe2.setFont(font2);
        pnTieuDe2.setBackground(new Color(2, 115, 83));
        lblTieuDe2.setForeground(Color.WHITE);
        pnTieuDe2.add(lblTieuDe2);

        dtmPhieuChuaTra = new DefaultTableModel();
        dtmPhieuChuaTra.addColumn("Mã phiếu");
        dtmPhieuChuaTra.addColumn("Mã sách");
        dtmPhieuChuaTra.addColumn("TT sách mượn");
        tblPhieuChuaTra = new JTable(dtmPhieuChuaTra);
        JScrollPane scPhieuChuaTra = new JScrollPane(tblPhieuChuaTra, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scPhieuChuaTra.setPreferredSize(new Dimension(450, 300));
        pnThongKePhieuChuaTra.add(scPhieuChuaTra, BorderLayout.CENTER);

        JPanel pnBangThongKe = new JPanel();
        pnBangThongKe.setLayout(new BorderLayout());

        dtmPhieuTra = new DefaultTableModel();
        dtmPhieuTra.addColumn("Mã phiếu");
        dtmPhieuTra.addColumn("Mã sách");
        dtmPhieuTra.addColumn("Ngày trả");
        dtmPhieuTra.addColumn("Tình trạng sách mượn");
        dtmPhieuTra.addColumn("Tình trạng sách trả");
        dtmPhieuTra.addColumn("Thủ thư nhận sách");
        dtmPhieuTra.addColumn("Ghi chú");

        tblPhieuTra = new JTable(dtmPhieuTra);
        JScrollPane sc = new JScrollPane(tblPhieuTra, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc.setPreferredSize(new Dimension(400, 290));
        pnBangThongKe.add(sc, BorderLayout.CENTER);

        JPanel pnTieuDe3 = new JPanel();
        JLabel lblTieuDe3 = new JLabel("DANH SÁCH PHIẾU TRẢ");
        pnTieuDe3.add(lblTieuDe3);
        pnBangThongKe.add(pnTieuDe3, BorderLayout.NORTH);
        lblTieuDe3.setFont(font2);
        pnTieuDe3.setBackground(new Color(48, 51, 107));
        lblTieuDe3.setForeground(Color.WHITE);

        pnThongTin.add(pnBangThongKe, BorderLayout.SOUTH);


        btnTraSach.setIcon(Util.loadImage("trasach.png"));
        btnTimKiem.setIcon(Util.loadImage("timkiem.png"));
        btnQuayLai.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnTraSach.setBorder(null);
        btnTimKiem.setBorder(null);

        btnQuayLai.setPreferredSize(new Dimension(200, 30));
        btnTraSach.setPreferredSize(new Dimension(210, 60));
        btnTimKiem.setPreferredSize(btnTraSach.getPreferredSize());


        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 14);
        lblMaDG.setFont(font3);
        lblMaPhieu.setFont(font3);
        lblMaSach.setFont(font3);
        lblNgayTra.setFont(font3);
        lblNgayHenTra.setFont(font3);
        lblTinhTrangSach.setFont(font3);
        lblTinhTrangTra.setFont(font3);
        lblThuThuNhanSach.setFont(font3);
        lblGhiChu.setFont(font3);

        lblMaPhieu.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblMaDG.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblMaSach.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblNgayTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblNgayHenTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblTinhTrangSach.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblTinhTrangTra.setPreferredSize(lblThuThuNhanSach.getPreferredSize());
        lblGhiChu.setPreferredSize(lblThuThuNhanSach.getPreferredSize());

        pnMaDG.setBackground(new Color(255, 255, 255));
        pnMaPhieu.setBackground(new Color(255, 255, 255));
        pnMaSach.setBackground(new Color(255, 255, 255));
        pnNgayHenTra.setBackground(new Color(255, 255, 255));
        pnNgayTra.setBackground(new Color(255, 255, 255));
        pnThuThuNhanSach.setBackground(new Color(255, 255, 255));
        pnGhiChu.setBackground(new Color(255, 255, 255));
        pnTinhTrangSach.setBackground(new Color(255, 255, 255));
        pnTinhTrangTra.setBackground(new Color(255, 255, 255));


        txtMaPhieu.setEditable(false);
        txtMaSach.setEditable(false);
        txtMaDG.setEditable(false);
        txtNgayTra.setEditable(false);
        txtNgayHenTra.setEditable(false);
        txtTTSachMuon.setEditable(false);
        txtTTSachTra.setEditable(false);
        txtThuThuNhanSach.setEditable(false);
        txtGhiChu.setEditable(false);
    }

}
