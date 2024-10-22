package eaut.edu.vn.ui;

import eaut.edu.vn.interfaces.ITable;
import eaut.edu.vn.main.Application;
import eaut.edu.vn.model.Loan;
import eaut.edu.vn.model.Reader;
import eaut.edu.vn.service.LoanService;
import eaut.edu.vn.service.ReaderService;
import eaut.edu.vn.ui.controls.CustomFrame;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.reader.AddReader;
import eaut.edu.vn.ui.dialog.reader.DeleteReader;
import eaut.edu.vn.ui.dialog.reader.EditReader;
import eaut.edu.vn.ui.dialog.reader.ReaderDetail;
import eaut.edu.vn.util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Optional;
import java.util.Vector;


public class ReaderManager extends CustomFrame implements ITable {

    public int thongke = 0;
    JTextField txtMaDocGia, txtTenDocGia, txtSDT, txtDiaChi, txtGioiTinh, txtLanMatSach, txtCCCD;
    JButton btnThem, btnXoa, btnSua, btnQuayLai, btnThongTin;
    DefaultTableModel dtmPhieuMuon, dtmDocGia;
    JTable tblDocGia, tblPhieuMuon;
    private List<Reader> readers;

    public ReaderManager(String tieude) {
        super(tieude);
        this.setSize(1130, 780);
        setHeader(new Header("QUẢN LÝ ĐỘC GIẢ"));
        setFooter(new Footer());
        fillTable();
    }

    public void addEvents() {
        btnQuayLai.addActionListener(e -> {
            // TODO Auto-generated method stub
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

        btnThem.addActionListener(e -> {
            // TODO Auto-generated method stub
            AddReader themdg = new AddReader("Thêm độc giả");
            themdg.showWindow();
            dtmDocGia.setRowCount(0);
            fillTable();
        });
        btnXoa.addActionListener(e -> {
            // TODO Auto-generated method stub
            DeleteReader xoadg = new DeleteReader("Xóa độc giả");
            xoadg.machon = txtMaDocGia.getText();
            xoadg.loadInfo();
            xoadg.showWindow();
            dtmDocGia.setRowCount(0);
            fillTable();
        });
        btnSua.addActionListener(e -> {
            EditReader suadg = new EditReader("Sửa độc giả");
            suadg.ma = txtMaDocGia.getText();
            suadg.loadInfo();
            suadg.showWindow();
            dtmDocGia.setRowCount(0);
            fillTable();
        });

        tblDocGia.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int row = tblDocGia.rowAtPoint(e.getPoint());
                int col = tblDocGia.columnAtPoint(e.getPoint());
                int numcols = dtmDocGia.getColumnCount();

                for (int i = 0; i < numcols; i++) {
                    Object obj = dtmDocGia.getValueAt(row, i);
                    String str;
                    if (obj != null) {
                        str = obj.toString();
                    } else {
                        str = "";
                    }
                    if (i == 0) {
                        txtMaDocGia.setText(str);
                    }
                    if (i == 1) {
                        txtTenDocGia.setText(str);
                    }
                    if (i == 2) {
                        txtSDT.setText(str);
                    }
                    if (i == 3) {
                        txtDiaChi.setText(str);
                    }
                    if (i == 4) {
                        txtGioiTinh.setText(str);
                    }
                    if (i == 5) {
                        txtLanMatSach.setText(str);
                    }
                    if (i == 6) {
                        txtCCCD.setText(str);
                    }
                }
                String ma = txtMaDocGia.getText();
                dtmPhieuMuon.setRowCount(0);
                fillLoanByReaderId(ma);
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
        btnThongTin.addActionListener(e -> {
            int row = tblPhieuMuon.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Mời bạn chọn phiếu mượn");
                return;
            }
            Object idObj = dtmPhieuMuon.getValueAt(row, 0);
            Optional.ofNullable(idObj).ifPresent(o -> {
                String ma = idObj.toString();
                ReaderDetail a = new ReaderDetail("Chi tiết phiếu mượn");
                a.ma = ma;
                a.fillTable();
                a.showWindow();
            });
        });

    }

    @Override
    public void fillTable() {
        readers = ReaderService.getInstance().getAll();
        for (Reader reader : readers) {
            Vector<Object> vec = new Vector<>();
            vec.add(reader.getId());
            vec.add(reader.getName());
            vec.add(reader.getPhoneNumber());
            vec.add(reader.getAddress());
            vec.add(reader.getSex());
            vec.add(reader.getLostBooks());
            vec.add(reader.getIDCard());
            dtmDocGia.addRow(vec);
        }
    }

    void fillLoanByReaderId(String ma) {
        List<Loan> loans = LoanService.getInstance().search(ma);
        for (Loan loan : loans) {
            Vector<Object> vec = new Vector<>();
            vec.add(loan.getId());
            vec.add(loan.getQuantity());

            dtmPhieuMuon.addRow(vec);
        }
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

        JPanel pnMaDocGia = new JPanel();
        pnMaDocGia.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnMaDocGia);
        JLabel lblMaDocGia = new JLabel("Mã độc giả: ");
        txtMaDocGia = new JTextField();
        txtMaDocGia.setPreferredSize(new Dimension(240, 22));
        pnMaDocGia.add(lblMaDocGia);
        pnMaDocGia.add(txtMaDocGia);

        JPanel pnTenDG = new JPanel();
        pnTenDG.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnTenDG);
        JLabel lblTenDG = new JLabel("Tên độc giả: ");
        pnTenDG.add(lblTenDG);
        txtTenDocGia = new JTextField();
        txtTenDocGia.setPreferredSize(new Dimension(240, 22));
        pnTenDG.add(txtTenDocGia);

        JPanel pnSoDienThoai = new JPanel();
        pnSoDienThoai.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnSoDienThoai);
        JLabel lblSoDienThoai = new JLabel("Số điện thoại: ");
        pnSoDienThoai.add(lblSoDienThoai);
        txtSDT = new JTextField();
        txtSDT.setPreferredSize(new Dimension(240, 22));
        pnSoDienThoai.add(txtSDT);

        JPanel pnDiaChi = new JPanel();
        pnDiaChi.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnDiaChi);
        JLabel lblDiaChi = new JLabel("Địa chỉ: ");
        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(new Dimension(240, 22));
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);

        JPanel pnGioiTinh = new JPanel();
        pnGioiTinh.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnGioiTinh);
        JLabel lblGioiTinh = new JLabel("Giới tính: ");
        txtGioiTinh = new JTextField();
        txtGioiTinh.setPreferredSize(new Dimension(240, 22));
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(txtGioiTinh);

        JPanel pnMatSach = new JPanel();
        pnMatSach.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnMatSach);
        JLabel lblMatSach = new JLabel("Số lần mất sách: ");
        txtLanMatSach = new JTextField();
        txtLanMatSach.setPreferredSize(new Dimension(240, 22));
        pnMatSach.add(lblMatSach);
        pnMatSach.add(txtLanMatSach);

        JPanel pnCCCD = new JPanel();
        pnCCCD.setLayout(new FlowLayout());
        pnThongTinChiTiet.add(pnCCCD);
        JLabel lblCCCD = new JLabel("CCCD: ");
        txtCCCD = new JTextField();
        txtCCCD.setPreferredSize(new Dimension(240, 22));
        pnCCCD.add(lblCCCD);
        pnCCCD.add(txtCCCD);

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

        JPanel pnThem = new JPanel();
        pnThem.setLayout(new FlowLayout());
        btnThem = new JButton();
        pnThem.add(btnThem);
        btnThem.setBackground(new Color(241, 242, 246));
        pnThem.setBackground(new Color(241, 242, 246));
		
		/* JPanel pnTimKiem= new JPanel();
		pnTimKiem.setLayout(new FlowLayout());
		btnTimKiem= new JButton();
		btnTimKiem.setBackground(new java.awt.Color(4, 191, 138));
		pnTimKiem.add(btnTimKiem);
		pnTimKiem.setBackground(new java.awt.Color(4, 191, 138)); 
		*/

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

        JPanel pnThongKePhieuMuon = new JPanel();
        pnThongKePhieuMuon.setLayout(new BorderLayout());
        pnHienThiChiTiet.add(pnThongKePhieuMuon, BorderLayout.EAST);

        JPanel pnTieuDe2 = new JPanel();
        pnThongKePhieuMuon.add(pnTieuDe2, BorderLayout.NORTH);
        JLabel lblTieuDe2 = new JLabel("DANH SÁCH PHIẾU MƯỢN");
        lblTieuDe2.setFont(font2);
        pnTieuDe2.setBackground(new Color(2, 115, 83));
        lblTieuDe2.setForeground(Color.WHITE);
        pnTieuDe2.add(lblTieuDe2);

        dtmPhieuMuon = new DefaultTableModel();
        dtmPhieuMuon.addColumn("Mã phiếu");
        dtmPhieuMuon.addColumn("Số sách mượn");
        tblPhieuMuon = new JTable(dtmPhieuMuon);
        JScrollPane scPhieuMuon = new JScrollPane(tblPhieuMuon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scPhieuMuon.setPreferredSize(new Dimension(450, 300));
        pnThongKePhieuMuon.add(scPhieuMuon, BorderLayout.CENTER);

        JPanel pnChiTiet = new JPanel();
        pnChiTiet.setLayout(new FlowLayout());
        btnThongTin = new JButton("CHI TIẾT");
        pnChiTiet.add(btnThongTin);
        btnThongTin.setFont(font4);
        btnThongTin.setForeground(new Color(2, 115, 83));
        btnThongTin.setBackground(new Color(255, 255, 255));
        pnChiTiet.setBackground(new Color(255, 255, 255));

        pnThongKePhieuMuon.add(pnChiTiet, BorderLayout.SOUTH);

        JPanel pnBangThongKe = new JPanel();
        pnBangThongKe.setLayout(new BorderLayout());

        dtmDocGia = new DefaultTableModel();
        dtmDocGia.addColumn("Mã độc giả");
        dtmDocGia.addColumn("Tên độc giả");
        dtmDocGia.addColumn("Số điện thoại");
        dtmDocGia.addColumn("Địa chỉ");
        dtmDocGia.addColumn("Giới tính");
        dtmDocGia.addColumn("Số lần mất sách");
        dtmDocGia.addColumn("CCCD");

        tblDocGia = new JTable(dtmDocGia);
        JScrollPane scDocGia = new JScrollPane(tblDocGia, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scDocGia.setPreferredSize(new Dimension(400, 350));
        scDocGia.setBackground(new Color(255, 255, 255));
        //tblDocGia.setFont(font4);
        pnBangThongKe.add(scDocGia, BorderLayout.CENTER);

        JPanel pnTieuDe3 = new JPanel();
        JLabel lblTieuDe3 = new JLabel("DANH SÁCH ĐỘC GIẢ");
        pnTieuDe3.add(lblTieuDe3);
        pnBangThongKe.add(pnTieuDe3, BorderLayout.NORTH);
        lblTieuDe3.setFont(font2);
        pnTieuDe3.setBackground(new Color(2, 115, 83));
        lblTieuDe3.setForeground(Color.WHITE);

        pnThongTin.add(pnBangThongKe, BorderLayout.SOUTH);

        btnThem.setIcon(Util.loadImage("themmoi.png"));
        btnXoa.setIcon(Util.loadImage("xoa.png"));
        btnSua.setIcon(Util.loadImage("chinhsua.png"));
        btnThongTin.setBorder(BorderFactory.createLineBorder(new Color(2, 115, 83)));
        btnQuayLai.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnThem.setBorder(null);
        btnXoa.setBorder(null);
        btnSua.setBorder(null);

        btnQuayLai.setPreferredSize(new Dimension(200, 30));
        btnThongTin.setPreferredSize(new Dimension(120, 30));
        btnThem.setPreferredSize(new Dimension(210, 60));
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        btnSua.setPreferredSize(btnThem.getPreferredSize());

        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 14);
        lblMaDocGia.setFont(font3);
        lblMatSach.setFont(font3);
        lblTenDG.setFont(font3);
        lblDiaChi.setFont(font3);
        lblGioiTinh.setFont(font3);
        lblSoDienThoai.setFont(font3);

        lblMaDocGia.setPreferredSize(lblMatSach.getPreferredSize());
        lblTenDG.setPreferredSize(lblMatSach.getPreferredSize());
        lblDiaChi.setPreferredSize(lblMatSach.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblMatSach.getPreferredSize());
        lblSoDienThoai.setPreferredSize(lblMatSach.getPreferredSize());
        lblCCCD.setPreferredSize(lblMatSach.getPreferredSize());

        pnMaDocGia.setBackground(new Color(255, 255, 255));
        pnTenDG.setBackground(new Color(255, 255, 255));
        pnMatSach.setBackground(new Color(255, 255, 255));
        pnSoDienThoai.setBackground(new Color(255, 255, 255));
        pnDiaChi.setBackground(new Color(255, 255, 255));
        pnGioiTinh.setBackground(new Color(255, 255, 255));
        pnCCCD.setBackground(new Color(255, 255, 255));


        txtMaDocGia.setEditable(false);
        txtTenDocGia.setEditable(false);
        txtLanMatSach.setEditable(false);
        txtSDT.setEditable(false);
        txtGioiTinh.setEditable(false);
        txtDiaChi.setEditable(false);
        txtCCCD.setEditable(false);

    }
}
