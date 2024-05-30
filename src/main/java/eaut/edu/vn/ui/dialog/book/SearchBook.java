package eaut.edu.vn.ui.dialog.book;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.ui.controls.PlaceholderTextField;
import eaut.edu.vn.ui.dialog.Dialog;
import eaut.edu.vn.util.Util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchBook extends Dialog {
    JButton btnTimKiem;
    PlaceholderTextField txtTimKiem;
    JTextField txtMaSach, txtTenSach, txtTacGia, txtNhaXB, txtTheLoai, txtSoLuong, txtGia;
    DefaultTableModel dtmSach;
    JTable tblSach;

    public SearchBook(String title) {
        super(title, "QUẢN LÝ SÁCH");
    }

    @Override
    public void addEvents() {
        btnTimKiem.addActionListener(e -> {
            // TODO Auto-generated method stub
            dtmSach.setRowCount(0);
            String tensach = txtTimKiem.getText();
            try {
                String sql = "select * from sach where tensach like ?";
                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1, '%' + tensach + '%');
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    String ma = rs.getString(1);
                    String ten = rs.getString(2);
                    String tg = rs.getString(3);
                    String nxb = rs.getString(4);
                    String tl = rs.getString(5);
                    String sl = String.valueOf(rs.getInt(6));
                    String gia = String.valueOf(rs.getInt(7));

                    Vector<String> vec = new Vector<String>();
                    vec.add(ma);
                    vec.add(ten);
                    vec.add(tg);
                    vec.add(nxb);
                    vec.add(tl);
                    vec.add(sl);
                    vec.add(gia);

                    dtmSach.addRow(vec);
                }
                rs.close();
                pre.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        tblSach.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblSach.rowAtPoint(e.getPoint());
                int col = tblSach.columnAtPoint(e.getPoint());
                int numcols = tblSach.getColumnCount();

                for (int i = 0; i < numcols; i++) {
                    String str = (String) dtmSach.getValueAt(row, i);
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
                    dtmSach.setRowCount(0);
                    String tensach = txtTimKiem.getText();
                    try {
                        String sql = "select * from sach where tensach like ?";
                        Connection connection = DbManager.getInstance().getConnection();
                        PreparedStatement pre = connection.prepareStatement(sql);
                        pre.setString(1, '%' + tensach + '%');
                        ResultSet rs = pre.executeQuery();
                        while (rs.next()) {
                            String ma = rs.getString(1);
                            String ten = rs.getString(2);
                            String tg = rs.getString(3);
                            String nxb = rs.getString(4);
                            String tl = rs.getString(5);
                            String sl = String.valueOf(rs.getInt(6));
                            String gia = String.valueOf(rs.getInt(7));

                            Vector<String> vec = new Vector<String>();
                            vec.add(ma);
                            vec.add(ten);
                            vec.add(tg);
                            vec.add(nxb);
                            vec.add(tl);
                            vec.add(sl);
                            vec.add(gia);
                            dtmSach.addRow(vec);
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
    }

    @Override
    public void initComponents() {
        JPanel pnHienThiTimKiemSach = new JPanel();
        pnHienThiTimKiemSach.setLayout(new BorderLayout());
        mainPanel.add(pnHienThiTimKiemSach, BorderLayout.CENTER);

        JPanel pnThanhTimKiem = new JPanel();
        pnThanhTimKiem.setLayout(new BorderLayout());


        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new FlowLayout());
        btnTimKiem = new JButton("TÌM KIẾM");
        pnTimKiem.add(btnTimKiem);
        pnThanhTimKiem.add(pnTimKiem, BorderLayout.EAST);
        btnTimKiem.setPreferredSize(new Dimension(130, 35));
        pnTimKiem.setBackground(new Color(48, 51, 107));
        btnTimKiem.setBackground(new Color(48, 51, 107));
        btnTimKiem.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        Font font7 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        btnTimKiem.setForeground(Color.WHITE);
        btnTimKiem.setFont(font7);

        JPanel pnTextTimKiem = new JPanel();
        pnTextTimKiem.setLayout(new BorderLayout());
        txtTimKiem = new PlaceholderTextField();
        txtTimKiem.setPlaceholder("Nhập tên sách...");
        pnTextTimKiem.add(txtTimKiem);
        pnThanhTimKiem.add(pnTextTimKiem, BorderLayout.CENTER);
        txtTimKiem.setPreferredSize(new Dimension(100, 20));
        Font font6 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 18);
        txtTimKiem.setFont(font6);
        txtTimKiem.setBorder(BorderFactory.createLineBorder(new Color(48, 51, 107)));

        JPanel pnIcon = new JPanel();
        pnIcon.setLayout(new FlowLayout());
        JLabel lblIcon = new JLabel();
        pnIcon.add(lblIcon);
        pnIcon.setBackground(new Color(48, 51, 107));
        lblIcon.setIcon(Util.loadImage("tim.png"));
        pnThanhTimKiem.add(pnIcon, BorderLayout.WEST);


        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());
        pnHienThiTimKiemSach.add(pnThongTin, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout());
        JLabel lblTimKiemSach = new JLabel("TÌM KIẾM SÁCH");
        pnTitle.add(lblTimKiemSach);
        pnHienThiTimKiemSach.add(pnTitle, BorderLayout.NORTH);
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 25);
        lblTimKiemSach.setFont(font5);
        lblTimKiemSach.setForeground(new Color(48, 51, 107));


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
        pnTieuDe1.setBackground(new Color(4, 191, 138));
        lblTieuDe1.setForeground(Color.WHITE);


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

        JPanel pnAN3 = new JPanel();
        pnAN3.setLayout(new FlowLayout());
        JLabel lblAN3 = new JLabel();
        pnAN3.add(lblAN3);
        pnAN3.setBackground(new Color(255, 255, 255));
        lblAN3.setIcon(Util.loadImage("an3.png"));
        pnHienThiChiTiet.add(pnAN3);


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
        pnThongTin.add(pnBangThongKe, BorderLayout.CENTER);

        JPanel pnTieuDe2 = new JPanel();
        JLabel lblTieuDe2 = new JLabel("DANH MỤC SÁCH TÌM KIẾM");
        pnTieuDe2.add(lblTieuDe2);
        pnBangThongKe.add(pnTieuDe2, BorderLayout.NORTH);
        lblTieuDe2.setFont(font4);
        pnTieuDe2.setBackground(new Color(4, 191, 138));
        lblTieuDe2.setForeground(Color.WHITE);


        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);
        lblTenSach.setFont(font3);
        lblMaSach.setFont(font3);
        lblTacGia.setFont(font3);
        lblNhaXB.setFont(font3);
        lblSoLuong.setFont(font3);
        lblTheLoai.setFont(font3);
        lblGia.setFont(font3);

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

    }

    public void showWindow() {
        this.setSize(1200, 710);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

}
