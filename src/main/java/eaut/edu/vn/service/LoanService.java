package eaut.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.model.Loan;


public class LoanService extends ConnectMySQL {
    public ArrayList<Loan> layThongTinPhieuMuon() {
        ArrayList<Loan> dspm = new ArrayList<Loan>();
        try {
            String sql = "select * from phieumuon";
            PreparedStatement pre = connect.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Loan pm = new Loan();
                pm.setMaPM(result.getString(1));
                pm.setMaDG(result.getString(2));
                pm.setNgayMuon(result.getDate(3));
                pm.setNgayTra(result.getDate(4));
                pm.setSoLuong(result.getInt(5));
                pm.setUser(result.getString(6));
                dspm.add(pm);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dspm;
    }

    public ArrayList<Loan> timKiemPhieuMuonTheoMaKH(String ma) {
        ArrayList<Loan> dspm = new ArrayList<Loan>();
        try {
            String sql = "select * from phieumuon where makh=?";
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, ma);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Loan pm = new Loan();

                dspm.add(pm);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dspm;
    }

    public ArrayList<Loan> timKiemPhieuMuonTheoMaPM(String mapm) {
        ArrayList<Loan> dspm = new ArrayList<Loan>();
        try {
            String sql = "select * from phieumuon where maphieu=?";
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, mapm);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Loan pm = new Loan();

                dspm.add(pm);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dspm;
    }

    public ArrayList<Loan> timKiemPhieuMuonTheoMaSach(String masach) {
        ArrayList<Loan> dspm = new ArrayList<Loan>();
        try {
            String sql = "select * from phieumuon where masach=?";
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, masach);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Loan pm = new Loan();

                dspm.add(pm);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dspm;
    }

}
