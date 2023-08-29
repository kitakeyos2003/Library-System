package eaut.edu.vn.model;

import java.sql.Date;

public class Loan {
    private String MaPM;
    private String MaDG;
    private Date NgayMuon;
    private Date NgayTra;
    private int SoLuong;
    private String User;

    public String getMaPM() {
        return MaPM;
    }

    public void setMaPM(String maPM) {
        MaPM = maPM;
    }

    public String getMaDG() {
        return MaDG;
    }

    public void setMaDG(String maDG) {
        MaDG = maDG;
    }

    public Date getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        NgayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date ngayTra) {
        NgayTra = ngayTra;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }


}
