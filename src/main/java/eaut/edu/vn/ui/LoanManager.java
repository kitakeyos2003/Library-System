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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.service.LoanService;
import eaut.edu.vn.service.BookService;
import eaut.edu.vn.ui.dialog.loan.Edit;
import eaut.edu.vn.ui.dialog.loan.Add;
import eaut.edu.vn.ui.dialog.loan.Delete;
import eaut.edu.vn.model.Loan;
import eaut.edu.vn.model.Book;
import eaut.edu.vn.util.Util;


public class LoanManager extends JFrame {
    public String tentk = "";
    public int thongke = 0;
    JTextField txtMaPhieu, txtTenDG, txtNgayMuon, txtNgayHenTra, txtSoSachMuon, txtThuThu, txtMaDG;
    JButton btnThem, btnXoa, btnSua, btnQuayLai;
    DefaultTableModel dtmPhieuMuon, dtmSachMuon;
    JTable tblPhieuMuon, tblSachMuon;
    ArrayList<Loan> dspm;
    ArrayList<Book> dssaches;
    Connection conn = ConnectMySQL.connect;

    public LoanManager(String tieude) {
        this.setTitle(tieude);
        addControls();
        addEvents();
        hienThiPhieuMuon();
    }

    protected void addEvents() {
        btnQuayLai.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int phanquyen = 0;
                if (thongke == 1) {
                    StatisticAnalyzer ui = new StatisticAnalyzer("Thống kê");
                    ui.tenTk = tentk;
                    ui.showWindow();
                    dispose();
                    thongke = 0;
                    return;
                }
                try {

                    String sql = "select PhanQuyen from taikhoan where User=?";
                    PreparedStatement pre = ConnectMySQL.connect.prepareStatement(sql);
                    pre.setString(1, tentk);
                    ResultSet rs = pre.executeQuery();
                    while (rs.next()) {
                        phanquyen = rs.getInt(1);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (phanquyen == 1) {
                    AdminManager ql = new AdminManager("Trang Chủ Phần Mềm Quản Lý Thư Viện");
                    ql.tentk = tentk;
                    ql.showWindow();
                    dispose();
                }
                if (phanquyen == 2) {
                    LibrarianManager ql = new LibrarianManager("Thủ thư: " + tentk);
                    ql.tentk = tentk;
                    ql.showWindow();
                    dispose();
                }
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Add tpm = new Add("Thêm phiếu mượn");
                tpm.tentk = tentk;
                tpm.hienThi();
                tpm.showWindow();
                hienThiPhieuMuon();
            }
        });
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Edit suapm = new Edit("Sửa phiếu mượn");
                suapm.maPM = txtMaPhieu.getText();
                suapm.hienThi();
                suapm.showWindow();
                hienThiPhieuMuon();
                dtmSachMuon.setRowCount(0);
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Delete xoapm = new Delete("Xóa phiếu mượn");
                xoapm.machon = txtMaPhieu.getText();
                xoapm.hienThi();
                xoapm.showWindow();
                dtmPhieuMuon.setRowCount(0);
                hienThiPhieuMuon();
            }
        });
        tblPhieuMuon.addMouseListener(new MouseListener() {

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
                int n = tblPhieuMuon.getSelectedRow();
                Object obj = dtmPhieuMuon.getValueAt(n, 0);
                String mapm = String.valueOf(obj);
                BookService sachsv = new BookService();
                dssaches = sachsv.laySachTheoPhieuMuon(mapm);
                dtmSachMuon.setRowCount(0);
                for (Book s : dssaches) {
                    Vector<Object> vec = new Vector<>();
                    vec.add(s.getMaSach());
                    vec.add(s.getTenSach());
                    vec.add(s.getTheLoai());
                    dtmSachMuon.addRow(vec);
                }
                try {
                    String sql = "Select a.MaPM,a.MaDG,b.TenDG,NgayMuon,NgayHenTra,SoLuongMuon,c.TenND from PhieuMuon a,DocGia b,Taikhoan c where b.MaDG=? and c.User=?";
                    PreparedStatement pre = conn.prepareStatement(sql);
                    pre.setString(1, String.valueOf(dtmPhieuMuon.getValueAt(n, 1)));
                    pre.setString(2, String.valueOf(dtmPhieuMuon.getValueAt(n, 5)));
                    ResultSet rs = pre.executeQuery();
                    while (rs.next()) {
                        txtMaPhieu.setText(mapm);
                        txtMaDG.setText(String.valueOf(dtmPhieuMuon.getValueAt(n, 1)));
                        txtTenDG.setText(rs.getString(3));
                        txtNgayMuon.setText(String.valueOf(dtmPhieuMuon.getValueAt(n, 2)));
                        txtNgayHenTra.setText(String.valueOf(dtmPhieuMuon.getValueAt(n, 3)));
                        txtSoSachMuon.setText(String.valueOf(dtmPhieuMuon.getValueAt(n, 4)));
                        txtThuThu.setText(rs.getString(7));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    protected void addControls() {
        Container con = getContentPane();

        JPanel pnPhieuMuon = new JPanel();
        pnPhieuMuon.setLayout(new BorderLayout());
        con.add(pnPhieuMuon);

        JPanel pnTieuDe = new JPanel();
        JLabel lblTieuDe = new JLabel("QUẢN LÝ PHIẾU MƯỢN");
        pnTieuDe.add(lblTieuDe);
        pnPhieuMuon.add(pnTieuDe, BorderLayout.NORTH);
        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        lblTieuDe.setFont(font1);
        pnTieuDe.setBackground(new Color(48, 51, 107));
        lblTieuDe.setForeground(Color.WHITE);

        JPanel pnLienHe = new JPanel();
        JLabel lblLienHe = new JLabel("THÔNG TIN TRỢ GIÚP - LIÊN HỆ: 0342565857");
        pnLienHe.add(lblLienHe);
        pnPhieuMuon.add(pnLienHe, BorderLayout.SOUTH);
        pnLienHe.setBackground(new Color(48, 51, 107));
        lblLienHe.setForeground(Color.WHITE);
        Font fontx = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        lblLienHe.setFont(fontx);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        pnPhieuMuon.add(pnThongTin, BorderLayout.CENTER);

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
        pnTieuDe1.setBackground(new Color(255, 177, 66));
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

        JPanel pnTenDG = new JPanel();
        pnTenDG.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnTenDG);
        JLabel lblTenDG = new JLabel("Tên độc giả: ");
        pnTenDG.add(lblTenDG);
        txtTenDG = new JTextField();
        txtTenDG.setPreferredSize(new Dimension(240, 22));
        pnTenDG.add(txtTenDG);

        JPanel pnNgayMuon = new JPanel();
        pnNgayMuon.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnNgayMuon);
        JLabel lblNgayMuon = new JLabel("Ngày mượn: ");
        txtNgayMuon = new JTextField();
        txtNgayMuon.setPreferredSize(new Dimension(240, 22));
        pnNgayMuon.add(lblNgayMuon);
        pnNgayMuon.add(txtNgayMuon);

        JPanel pnNgayHenTra = new JPanel();
        pnNgayHenTra.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnNgayHenTra);
        JLabel lblNgayHenTra = new JLabel("Ngày hẹn trả: ");
        txtNgayHenTra = new JTextField();
        txtNgayHenTra.setPreferredSize(new Dimension(240, 22));
        pnNgayHenTra.add(lblNgayHenTra);
        pnNgayHenTra.add(txtNgayHenTra);

        JPanel pnSachMuon = new JPanel();
        pnSachMuon.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnSachMuon);
        JLabel lblSachMuon = new JLabel("Sách mượn: ");
        txtSoSachMuon = new JTextField();
        txtSoSachMuon.setPreferredSize(new Dimension(240, 22));
        pnSachMuon.add(lblSachMuon);
        pnSachMuon.add(txtSoSachMuon);

        JPanel pnThuThu = new JPanel();
        pnThuThu.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnThuThu);
        JLabel lblThuThu = new JLabel("Thủ thư: ");
        txtThuThu = new JTextField();
        txtThuThu.setPreferredSize(new Dimension(240, 22));
        pnThuThu.add(lblThuThu);
        pnThuThu.add(txtThuThu);

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new BorderLayout());
        pnHienThiChiTiet.add(pnThaoTac, BorderLayout.CENTER);

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

        JPanel pnThongKeSachMuon = new JPanel();
        pnThongKeSachMuon.setLayout(new BorderLayout());
        pnHienThiChiTiet.add(pnThongKeSachMuon, BorderLayout.EAST);

        JPanel pnTieuDe2 = new JPanel();
        pnThongKeSachMuon.add(pnTieuDe2, BorderLayout.NORTH);
        JLabel lblTieuDe2 = new JLabel("DANH SÁCH SÁCH MƯỢN");
        lblTieuDe2.setFont(font2);
        pnTieuDe2.setBackground(new Color(255, 177, 66));
        lblTieuDe2.setForeground(Color.WHITE);
        pnTieuDe2.add(lblTieuDe2);

        dtmSachMuon = new DefaultTableModel();
        dtmSachMuon.addColumn("Mã sách");
        dtmSachMuon.addColumn("Tên sách");
        dtmSachMuon.addColumn("Thể loại");
        tblSachMuon = new JTable(dtmSachMuon);
        JScrollPane scSachMuon = new JScrollPane(tblSachMuon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scSachMuon.setPreferredSize(new Dimension(450, 300));
        pnThongKeSachMuon.add(scSachMuon, BorderLayout.CENTER);

        JPanel pnBangThongKe = new JPanel();
        pnBangThongKe.setLayout(new BorderLayout());

        dtmPhieuMuon = new DefaultTableModel();
        dtmPhieuMuon.addColumn("Mã phiếu");
        dtmPhieuMuon.addColumn("Mã độc giả");
        dtmPhieuMuon.addColumn("Ngày mượn");
        dtmPhieuMuon.addColumn("Ngày hẹn trả");
        dtmPhieuMuon.addColumn("Số sách mượn");
        dtmPhieuMuon.addColumn("Thủ thư");

        tblPhieuMuon = new JTable(dtmPhieuMuon);
        JScrollPane sc = new JScrollPane(tblPhieuMuon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc.setPreferredSize(new Dimension(400, 350));
        pnBangThongKe.add(sc, BorderLayout.CENTER);

        JPanel pnTieuDe3 = new JPanel();
        JLabel lblTieuDe3 = new JLabel("DANH SÁCH PHIẾU MƯỢN");
        pnTieuDe3.add(lblTieuDe3);
        pnBangThongKe.add(pnTieuDe3, BorderLayout.NORTH);
        lblTieuDe3.setFont(font2);
        pnTieuDe3.setBackground(new Color(48, 51, 107));
        lblTieuDe3.setForeground(Color.WHITE);

        pnThongTin.add(pnBangThongKe, BorderLayout.SOUTH);

        btnThem.setIcon(Util.loadImage("themmoi.png"));
        btnXoa.setIcon(Util.loadImage("xoa.png"));
        btnSua.setIcon(Util.loadImage("chinhsua.png"));
        btnQuayLai.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnThem.setBorder(null);
        btnXoa.setBorder(null);
        btnSua.setBorder(null);

        btnQuayLai.setPreferredSize(new Dimension(200, 30));
        btnThem.setPreferredSize(new Dimension(210, 60));
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        btnSua.setPreferredSize(btnThem.getPreferredSize());

        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 14);
        lblMaDG.setFont(font3);
        lblMaPhieu.setFont(font3);
        lblTenDG.setFont(font3);
        lblNgayMuon.setFont(font3);
        lblNgayHenTra.setFont(font3);
        lblSachMuon.setFont(font3);
        lblThuThu.setFont(font3);

        lblMaPhieu.setPreferredSize(lblNgayHenTra.getPreferredSize());
        lblMaDG.setPreferredSize(lblNgayHenTra.getPreferredSize());
        lblNgayMuon.setPreferredSize(lblNgayHenTra.getPreferredSize());
        lblTenDG.setPreferredSize(lblNgayHenTra.getPreferredSize());
        lblThuThu.setPreferredSize(lblNgayHenTra.getPreferredSize());
        lblSachMuon.setPreferredSize(lblNgayHenTra.getPreferredSize());

        pnMaDG.setBackground(new Color(255, 255, 255));
        pnTenDG.setBackground(new Color(255, 255, 255));
        pnMaPhieu.setBackground(new Color(255, 255, 255));
        pnNgayHenTra.setBackground(new Color(255, 255, 255));
        pnNgayMuon.setBackground(new Color(255, 255, 255));
        pnSachMuon.setBackground(new Color(255, 255, 255));
        pnThuThu.setBackground(new Color(255, 255, 255));


        txtMaPhieu.setEditable(false);
        txtTenDG.setEditable(false);
        txtMaDG.setEditable(false);
        txtNgayMuon.setEditable(false);
        txtNgayHenTra.setEditable(false);
        txtSoSachMuon.setEditable(false);
        txtThuThu.setEditable(false);
    }

    private void hienThiPhieuMuon() {
        LoanService pmsv = new LoanService();
        dspm = pmsv.layThongTinPhieuMuon();
        dtmPhieuMuon.setRowCount(0);
        for (Loan pm : dspm) {
            Vector<Object> vec = new Vector<>();
            vec.add(pm.getMaPM());
            vec.add(pm.getMaDG());
            vec.add(pm.getNgayMuon());
            vec.add(pm.getNgayTra());
            vec.add(pm.getSoLuong());
            vec.add(pm.getUser());
            dtmPhieuMuon.addRow(vec);
        }
    }

    public void showWindow() {
        this.setSize(1130, 775);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

}
