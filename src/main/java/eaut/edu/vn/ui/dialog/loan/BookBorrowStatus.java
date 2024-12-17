package eaut.edu.vn.ui.dialog.loan;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.*;

import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.model.Book;
import eaut.edu.vn.service.BookService;
import eaut.edu.vn.ui.dialog.Dialog;


import eaut.edu.vn.util.Util;


public class BookBorrowStatus extends Dialog {
    public int MaPM;
    public String user = "";
    JTextField txtMaPM, txtTinhTrangSach;
    JComboBox<Book> cbBook;
    JButton btnThem;

    public BookBorrowStatus(String title) {
        super(title, "THÊM THÔNG TIN SÁCH");
    }

    public void fill() {
        List<Book> books = BookService.getInstance().getAll();
        for (Book book : books) {
            cbBook.addItem(book);
        }
        txtMaPM.setText(String.valueOf(MaPM));
    }

    @Override
    public void initComponents() {JPanel pnHienThiThemPM = new JPanel();
        pnHienThiThemPM.setLayout(new BoxLayout(pnHienThiThemPM, BoxLayout.Y_AXIS));
        mainPanel.add(pnHienThiThemPM, BorderLayout.CENTER);

        JPanel pnMaPM = new JPanel();
        pnMaPM.setLayout(new FlowLayout());
        JLabel lblMaPM = new JLabel("Mã phiếu: ");
        txtMaPM = new JTextField();
        txtMaPM.setEnabled(false);
        txtMaPM.setPreferredSize(new Dimension(340, 30));
        pnMaPM.add(lblMaPM);
        pnMaPM.add(txtMaPM);

        JPanel pnMaSach = new JPanel();
        pnMaSach.setLayout(new FlowLayout());
        JLabel lblMaSach = new JLabel("Sách: ");
        cbBook = new JComboBox<>();
        cbBook.setPreferredSize(new Dimension(340, 30));
        pnMaSach.add(lblMaSach);
        pnMaSach.add(cbBook);

        JPanel pnTinhTrang = new JPanel();
        pnTinhTrang.setLayout(new FlowLayout());
        JLabel lblTinhTrang = new JLabel("Tình trạng sách: ");
        txtTinhTrangSach = new JTextField();
        txtTinhTrangSach.setPreferredSize(new Dimension(340, 30));
        pnTinhTrang.add(lblTinhTrang);
        pnTinhTrang.add(txtTinhTrangSach);

        pnHienThiThemPM.add(pnMaPM);
        pnHienThiThemPM.add(pnMaSach);
        pnHienThiThemPM.add(pnTinhTrang);

        JPanel pnThaoTac = new JPanel();
        pnThaoTac.setLayout(new FlowLayout());
        pnHienThiThemPM.add(pnThaoTac);
        btnThem = new JButton("THÊM");
        btnThem.setPreferredSize(new Dimension(110, 35));
        pnThaoTac.add(btnThem);
        pnThaoTac.setBackground(new Color(241, 242, 246));
        Font font5 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 13);
        btnThem.setFont(font5);

        btnThem.setBackground(new Color(4, 191, 138));
        btnThem.setForeground(Color.white);
        btnThem.setBorder(BorderFactory.createLineBorder(new Color(4, 191, 138)));

        Font font1 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 24);
        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 15);

        lblMaPM.setFont(font4);
        lblMaSach.setFont(font4);
        lblTinhTrang.setFont(font4);

        txtMaPM.setFont(font4);
        cbBook.setFont(font4);
        txtTinhTrangSach.setFont(font4);

        pnMaPM.setBackground(new Color(241, 242, 246));
        pnMaSach.setBackground(new Color(241, 242, 246));
        pnTinhTrang.setBackground(new Color(241, 242, 246));

        lblMaSach.setPreferredSize(lblTinhTrang.getPreferredSize());
        lblMaPM.setPreferredSize(lblTinhTrang.getPreferredSize());

        txtMaPM.setEditable(false);
    }

    @Override
    public void addEvents() {
        btnThem.addActionListener(e -> {
            try {
                Book book = (Book) cbBook.getSelectedItem();
                if (book == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sách");
                    return;
                }
                if (txtTinhTrangSach.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không được để trống");
                    return;
                }
                int soluongsach = 0;

                try {

                    Connection connection = DbManager.getInstance().getConnection();
                    String sqlss = "Select SoLuong from sach where MaSach=?";
                    PreparedStatement presach = connection.prepareStatement(sqlss);
                    presach.setInt(1,book.getId());
                    ResultSet rssach = presach.executeQuery();
                    while (rssach.next()) {
                        soluongsach = rssach.getInt(1);
                    }
                    rssach.close();
                    presach.close();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (soluongsach == 0) {
                    JOptionPane.showMessageDialog(null, "Sách hết.Xin vui lòng chọn cuốn khác !");
                    dispose();
                    return;
                }
                soluongsach--;

                try {
                    Connection connection = DbManager.getInstance().getConnection();
                    String sqlss1 = "update sach set SoLuong=? where MaSach=?";
                    PreparedStatement presach1 = connection.prepareStatement(sqlss1);
                    presach1.setInt(1, soluongsach);
                    presach1.setInt(2, book.getId());
                    int c = presach1.executeUpdate();
                    presach1.close();
                    connection.close();
                    if (c > 0) {
                        JOptionPane.showMessageDialog(null, "Cập nhật số lượng sách thành công");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                String sql = "insert into ctpm values(?,?,?,?,?,?,?)";
                Connection connection = DbManager.getInstance().getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1, txtMaPM.getText());
                pre.setInt(2, book.getId());
                pre.setDate(3, null);
                pre.setString(4, txtTinhTrangSach.getText());
                pre.setString(5, null);
                pre.setString(6, null);
                pre.setString(7, null);
                int x = pre.executeUpdate();
                pre.close();
                connection.close();
                if (x > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm sách thành công");
                    dispose();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
    }

    public void showWindow() {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }

}
