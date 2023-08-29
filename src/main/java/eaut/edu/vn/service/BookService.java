package eaut.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.model.Book;


public class BookService extends ConnectMySQL {
    public ArrayList<Book> layToanBoSach() {
        ArrayList<Book> ds = new ArrayList<Book>();
        try {
            String sql = "select * from sach";
            PreparedStatement pre = connect.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Book sh = new Book();
                sh.setMaSach(result.getString(1));
                sh.setTenSach(result.getString(2));
                sh.setTenTG(result.getString(3));
                sh.setNhaXB(result.getString(4));
                sh.setTheLoai(result.getString(5));
                sh.setSoLuong(result.getInt(6));
                sh.setGiaTien(result.getDouble(7));
                ds.add(sh);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Book> timKiemSachTheoMaSach(String masach) {
        ArrayList<Book> ds = new ArrayList<Book>();
        try {
            String sql = "select * from sach where masach=?";
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, masach);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Book sh = new Book();

                ds.add(sh);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Book> timKiemSachTheoMaNXB(String manxb) {
        ArrayList<Book> ds = new ArrayList<Book>();
        try {
            String sql = "select * from sach where manxb=?";
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, manxb);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Book sh = new Book();

                ds.add(sh);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Book> timKiemSachTheoTenSach(String tensach) {
        ArrayList<Book> ds = new ArrayList<Book>();
        try {
            String sql = "select * from sach where tensach like ? ";
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, '%' + tensach + '%');
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Book sh = new Book();

                ds.add(sh);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Book> laySachTheoPhieuMuon(String mapm) {
        ArrayList<Book> dssaches = new ArrayList<Book>();
        try {
            String sql = "Select a.MaSach,a.TenSach from sach a,ctpm b where a.MaSach=b.MaSach and b.MaPM=?";
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, mapm);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Book book = new Book();
                book.setMaSach(result.getString(1));
                book.setTenSach(result.getString(2));
                dssaches.add(book);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dssaches;
    }

}
