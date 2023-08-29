package eaut.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.model.Reader;

public class ReaderService extends ConnectMySQL {
    public ArrayList<Reader> layToanBoDocGia() {
        ArrayList<Reader> dsdg = new ArrayList<Reader>();
        try {
            String sql = "select * from docgia";
            PreparedStatement pre = connect.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Reader dg = new Reader();
                dg.setMaDG(result.getString(1));
                dg.setTenDG(result.getString(2));
                dg.setSoDienThoai(result.getString(3));
                dg.setDiaChi(result.getString(4));
                dg.setGioiTinh(result.getString(5));
                dg.setMatSach(result.getInt(6));
                dsdg.add(dg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dsdg;
    }

    public ArrayList<Reader> timDocGiaTheoMaDG(String madg) {
        ArrayList<Reader> dsdg = new ArrayList<Reader>();
        try {
            String sql = "select * from docgia where madg=?";
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, madg);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Reader dg = new Reader();
                dg.setMaDG(result.getString(1));
                dg.setTenDG(result.getString(2));
                dg.setDiaChi(result.getString(3));
                dg.setGioiTinh(result.getString(4));
                dg.setSoDienThoai(result.getString(5));
                dsdg.add(dg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dsdg;
    }

}
