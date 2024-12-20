package eaut.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.interfaces.ITable;
import eaut.edu.vn.main.Application;
import eaut.edu.vn.model.Book;
import eaut.edu.vn.service.BookService;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.CustomFrame;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.dialog.book.EditBook;
import eaut.edu.vn.ui.dialog.book.AddBook;
import eaut.edu.vn.ui.dialog.book.SearchBook;
import eaut.edu.vn.ui.dialog.book.DeleteBook;
import eaut.edu.vn.util.Log;
import eaut.edu.vn.util.Util;

public class BookManager extends CustomFrame implements ITable {
    
    public int thongke = 0;
    JTextField txtMaSach, txtTenSach, txtTacGia, txtNhaXB, txtTheLoai, txtSoLuong, txtGia;
    JButton btnThem, btnXoa, btnSua, btnQuayLai, btnTimKiem;
    DefaultTableModel dtmSach;
    JTable tblSach;
    List<Book> books;
    DefaultListModel defaultListTheLoai;
    JList listTheLoai;

    public BookManager(String tieude) {
        super(tieude);
        this.setSize(1130, 775);
        setHeader(new Header("QUẢN LÝ SÁCH"));
        setFooter(new Footer());
        fillTable();
    }

    @Override
    public void fillTable() {
        books = BookService.getInstance().getAll();
        for (Book book : books) {
            Vector<Object> vec = new Vector<>();
            vec.add(book.getId());
            vec.add(book.getName());
            vec.add(book.getAuthorName());
            vec.add(book.getPublishingCompany());
            vec.add(book.getCategory());
            vec.add(book.getQuantity());
            vec.add(Util.numberFormat(book.getPrice()));

            dtmSach.addRow(vec);
        }

    }

    @Override
    public void addEvents() {
        btnQuayLai.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (thongke == 1) {
                StatisticAnalyzer ui =   new StatisticAnalyzer("Thống kê");
                ui.showWindow();
                dispose();
                thongke = 0;
                return;
            }

            int phanquyen = Application.account.getRole();
            if (phanquyen == 1) {
                AdminManager ql = new AdminManager("Trang chủ");;
                ql.showWindow();
                dispose();
            }
            if (phanquyen == 2) {
                LibrarianManager ql = new LibrarianManager("Trang chủ");
                ql.showWindow();
                dispose();
            }
        });
        tblSach.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblSach.rowAtPoint(e.getPoint());
                int col = tblSach.columnAtPoint(e.getPoint());
                int numcols = tblSach.getColumnCount();

                for (int i = 0; i < numcols; i++) {
                    String str = dtmSach.getValueAt(row, i).toString();
                    if (i == 0)
                        txtMaSach.setText(str);
                    if (i == 1)
                        txtTenSach.setText(str);
                    if (i == 2)
                        txtTacGia.setText(str);
                    if (i == 3)
                        txtNhaXB.setText(str);
                    if (i == 4)
                        txtTheLoai.setText(str);
                    if (i == 5)
                        txtSoLuong.setText(str);
                    if (i == 6)
                        txtGia.setText(str);
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
        listTheLoai.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    dtmSach.setRowCount(0);
                    String theloai = (String) defaultListTheLoai.get(index);
                    books.stream().filter(book -> book.getCategory().equals(theloai)).forEach(book -> {
                        Vector<Object> vec = new Vector<>();
                        vec.add(book.getId());
                        vec.add(book.getName());
                        vec.add(book.getAuthorName());
                        vec.add(book.getPublishingCompany());
                        vec.add(book.getCategory());
                        vec.add(book.getQuantity());
                        vec.add(Util.numberFormat(book.getPrice()));
                        dtmSach.addRow(vec);
                    });
                } else if (evt.getClickCount() == 3) {
                    int index = list.locationToIndex(evt.getPoint());
                }
            }
        });
        btnThem.addActionListener(e -> {
            AddBook addBook = new AddBook("Thêm sách");
            addBook.showWindow();
            dtmSach.setRowCount(0);
            fillTable();
        });
        btnTimKiem.addActionListener(e -> {
            SearchBook searchBook = new SearchBook("Tìm kiếm thông tin sách");
            searchBook.showWindow();
            dtmSach.setRowCount(0);
            fillTable();
        });
        btnSua.addActionListener(e -> {
            EditBook editBook = new EditBook("Sửa thông tin sách");
            editBook.ma = txtMaSach.getText();
            editBook.loadInfo();
            editBook.showWindow();
            dtmSach.setRowCount(0);
            fillTable();
        });
        btnXoa.addActionListener(e -> {
            DeleteBook deleteBook = new DeleteBook("Xóa thông tin sách");
            deleteBook.ma = txtMaSach.getText();
            deleteBook.loadInfo();
            deleteBook.showWindow();
            dtmSach.setRowCount(0);
            fillTable();
        });
    }

    @Override
    public void initComponents() {
        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        mainPanel.add(pnThongTin, BorderLayout.CENTER);

        JPanel pnChiTietSach = new JPanel();
        pnChiTietSach.setLayout(new BorderLayout());
        pnThongTin.add(pnChiTietSach, BorderLayout.CENTER);

        JPanel pnTongHopTheLoai = new JPanel();
        pnTongHopTheLoai.setLayout(new BorderLayout());
        pnThongTin.add(pnTongHopTheLoai, BorderLayout.WEST);
        pnTongHopTheLoai.setPreferredSize(new Dimension(300, 0));

        JPanel pnTieuDeTheLoai = new JPanel();
        JLabel lblTieuDeTheLoai = new JLabel("THỂ LOẠI");
        pnTieuDeTheLoai.add(lblTieuDeTheLoai);
        pnTongHopTheLoai.add(pnTieuDeTheLoai, BorderLayout.NORTH);
        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 18);
        lblTieuDeTheLoai.setFont(font2);
        pnTieuDeTheLoai.setBackground(new Color(2, 115, 83));
        lblTieuDeTheLoai.setForeground(Color.WHITE);
        defaultListTheLoai = new DefaultListModel();
        DbManager.getInstance().executeQuery("select theloai from sach group by theloai", rs -> {
            try {
                while (rs.next()) {
                    defaultListTheLoai.addElement(rs.getString(1));
                }
            } catch (SQLException e) {
                Log.error("load category err", e);
            }
        });


        listTheLoai = new JList(defaultListTheLoai);
        listTheLoai.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTheLoai.setSelectedIndex(0);
        listTheLoai.setVisibleRowCount(6);
        listTheLoai.setBackground(new Color(241, 242, 246));
        pnTongHopTheLoai.add(listTheLoai, BorderLayout.CENTER);


        JPanel pnTieuDe1 = new JPanel();
        JLabel lblTieuDe1 = new JLabel("THÔNG TIN CHI TIẾT");
        pnTieuDe1.add(lblTieuDe1);
        pnChiTietSach.add(pnTieuDe1, BorderLayout.NORTH);
        lblTieuDe1.setFont(font2);
        pnTieuDe1.setBackground(new Color(2, 115, 83));
        lblTieuDe1.setForeground(Color.WHITE);

        JPanel pnHienThiChiTiet = new JPanel();
        pnHienThiChiTiet.setLayout(new BoxLayout(pnHienThiChiTiet, BoxLayout.Y_AXIS));
        pnChiTietSach.add(pnHienThiChiTiet, BorderLayout.CENTER);

        JPanel pnMaSach = new JPanel();
        pnMaSach.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnMaSach);
        JLabel lblMaSach = new JLabel("Mã sách: ");
        txtMaSach = new JTextField();
        txtMaSach.setPreferredSize(new Dimension(240, 22));
        pnMaSach.add(lblMaSach);
        pnMaSach.add(txtMaSach);


        JPanel pnTenSach = new JPanel();
        pnTenSach.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTenSach);
        JLabel lblTenSach = new JLabel("Tên sách: ");
        txtTenSach = new JTextField();
        txtTenSach.setPreferredSize(new Dimension(240, 22));
        pnTenSach.add(lblTenSach);
        pnTenSach.add(txtTenSach);

        JPanel pnTacGia = new JPanel();
        pnTacGia.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTacGia);
        JLabel lblTacGia = new JLabel("Tác giả: ");
        txtTacGia = new JTextField();
        txtTacGia.setPreferredSize(new Dimension(240, 22));
        pnTacGia.add(lblTacGia);
        pnTacGia.add(txtTacGia);

        JPanel pnNhaXB = new JPanel();
        pnNhaXB.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnNhaXB);
        JLabel lblNhaXB = new JLabel("Nhà xuất bản: ");
        txtNhaXB = new JTextField();
        txtNhaXB.setPreferredSize(new Dimension(240, 22));
        pnNhaXB.add(lblNhaXB);
        pnNhaXB.add(txtNhaXB);

        JPanel pnTheLoai = new JPanel();
        pnTheLoai.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnTheLoai);
        JLabel lblTheLoai = new JLabel("Thể loại: ");
        txtTheLoai = new JTextField();
        txtTheLoai.setPreferredSize(new Dimension(240, 22));
        pnTheLoai.add(lblTheLoai);
        pnTheLoai.add(txtTheLoai);

        JPanel pnSoLuong = new JPanel();
        pnSoLuong.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnSoLuong);
        JLabel lblSoLuong = new JLabel("Số lượng: ");
        txtSoLuong = new JTextField();
        txtSoLuong.setPreferredSize(new Dimension(240, 22));
        pnSoLuong.add(lblSoLuong);
        pnSoLuong.add(txtSoLuong);

        JPanel pnGia = new JPanel();
        pnGia.setLayout(new FlowLayout());
        pnHienThiChiTiet.add(pnGia);
        JLabel lblGia = new JLabel("Giá: ");
        txtGia = new JTextField();
        txtGia.setPreferredSize(new Dimension(240, 22));
        pnGia.add(lblGia);
        pnGia.add(txtGia);

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new BorderLayout());

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
        pnChucNang.setBackground(new Color(241, 242, 246));
        pnChucNang.setLayout(new BoxLayout(pnChucNang, BoxLayout.Y_AXIS));


        JPanel pnThem = new JPanel();
        pnThem.setLayout(new FlowLayout());
        btnThem = new JButton();
        pnThem.add(btnThem);
        btnThem.setBackground(new Color(241, 242, 246));
        pnThem.setBackground(new Color(241, 242, 246));

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

        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new FlowLayout());
        btnTimKiem = new JButton();
        btnTimKiem.setBackground(new Color(241, 242, 246));
        pnTimKiem.add(btnTimKiem);
        pnTimKiem.setBackground(new Color(241, 242, 246));

        pnChucNang.add(pnThem);
        pnChucNang.add(pnXoa);
        pnChucNang.add(pnSua);
        pnChucNang.add(pnTimKiem);

        pnThaoTac.add(pnChucNang, BorderLayout.CENTER);
		
		/* Border borderHienThi= BorderFactory.createLineBorder(new java.awt.Color(4, 191, 138));
		TitledBorder titleBorderHienThi= new TitledBorder(borderHienThi);
		titleBorderHienThi.setTitleJustification(TitledBorder.LEFT);
		pnHienThiChiTiet.setBorder(titleBorderHienThi); */

        JPanel pnBangThongKe = new JPanel();
        pnBangThongKe.setLayout(new BorderLayout());

        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã sách");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("Tác giả");
        dtmSach.addColumn("Nhà xuất bản");
        dtmSach.addColumn("Thể loại");
        dtmSach.addColumn("Số lượng");
        dtmSach.addColumn("Giá");
        tblSach = new JTable(dtmSach);
        JScrollPane sc = new JScrollPane(tblSach, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc.setPreferredSize(new Dimension(400, 320));
        pnBangThongKe.add(sc, BorderLayout.CENTER);

        JPanel pnTieuDe2 = new JPanel();
        JLabel lblTieuDe2 = new JLabel("DANH SÁCH SÁCH");
        pnTieuDe2.add(lblTieuDe2);
        pnBangThongKe.add(pnTieuDe2, BorderLayout.NORTH);
        lblTieuDe2.setFont(font2);
        pnTieuDe2.setBackground(new Color(2, 115, 83));
        lblTieuDe2.setForeground(Color.WHITE);

        btnQuayLai.setPreferredSize(new Dimension(220, 30));
        btnThem.setPreferredSize(new Dimension(210, 60));
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        btnSua.setPreferredSize(btnThem.getPreferredSize());

        pnThongTin.add(pnChiTietSach, BorderLayout.CENTER);
        pnThongTin.add(pnThaoTac, BorderLayout.EAST);
        pnThaoTac.setPreferredSize(new Dimension(250, 0));
        pnThongTin.add(pnBangThongKe, BorderLayout.SOUTH);


        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        lblTenSach.setFont(font3);
        lblMaSach.setFont(font3);
        lblTacGia.setFont(font3);
        lblNhaXB.setFont(font3);
        lblSoLuong.setFont(font3);
        lblTheLoai.setFont(font3);
        lblGia.setFont(font3);
        listTheLoai.setFont(font4);

        lblMaSach.setPreferredSize(lblNhaXB.getPreferredSize());
        lblTenSach.setPreferredSize(lblNhaXB.getPreferredSize());
        lblTacGia.setPreferredSize(lblNhaXB.getPreferredSize());
        lblTheLoai.setPreferredSize(lblNhaXB.getPreferredSize());
        lblSoLuong.setPreferredSize(lblNhaXB.getPreferredSize());
        lblGia.setPreferredSize(lblNhaXB.getPreferredSize());

        pnMaSach.setBackground(new Color(255, 255, 255));
        pnTenSach.setBackground(new Color(255, 255, 255));
        pnTacGia.setBackground(new Color(255, 255, 255));
        pnTheLoai.setBackground(new Color(255, 255, 255));
        pnNhaXB.setBackground(new Color(255, 255, 255));
        pnSoLuong.setBackground(new Color(255, 255, 255));
        pnGia.setBackground(new Color(255, 255, 255));

        txtMaSach.setEditable(false);
        txtTenSach.setEditable(false);
        txtGia.setEditable(false);
        txtSoLuong.setEditable(false);
        txtNhaXB.setEditable(false);
        txtTheLoai.setEditable(false);
        txtTacGia.setEditable(false);

        btnThem.setIcon(Util.loadImage("themmoi.png"));
        btnXoa.setIcon(Util.loadImage("xoa.png"));
        btnSua.setIcon(Util.loadImage("sua.png"));
        btnTimKiem.setIcon(Util.loadImage("timkiem.png"));
        btnQuayLai.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnThem.setBorder(null);
        btnXoa.setBorder(null);
        btnSua.setBorder(null);
        btnTimKiem.setBorder(null);


    }

}
