package eaut.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.model.LoanDetail;


public class LoanDetailService extends ConnectMySQL {
    public ArrayList<LoanDetail> layChiTietPhieuMuon() {
        ArrayList<LoanDetail> dspm = new ArrayList<LoanDetail>();
        try {
            String sql = "Select * from ctpm";
            PreparedStatement pre = connect.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                LoanDetail ctpm = new LoanDetail();
                ctpm.setMaPM(result.getString(1));
                ctpm.setMaSach(result.getString(2));
                ctpm.setNgayTra(result.getDate(3));
                ctpm.setTinhTrangSach(result.getInt(4));
                ctpm.setTinhTrangTra(result.getInt(5));
                ctpm.setUser(result.getString(6));
                ctpm.setGhiChu(result.getString(7));
                dspm.add(ctpm);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dspm;
    }

}
