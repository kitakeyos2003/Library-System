package eaut.edu.vn.model;

import java.sql.Date;

public class LoanDetail {
    private String MaPM;
    private String MaSach;
    private Date NgayTra;
    private int TinhTrangSach;
    private int TinhTrangTra;
    private String User;
    private String GhiChu;

    public String getMaPM() {
        return MaPM;
    }

    public void setMaPM(String maPM) {
        MaPM = maPM;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date ngayTra) {
        NgayTra = ngayTra;
    }

    public int getTinhTrangSach() {
        return TinhTrangSach;
    }

    public void setTinhTrangSach(int tinhTrangSach) {
        TinhTrangSach = tinhTrangSach;
    }

    public int getTinhTrangTra() {
        return TinhTrangTra;
    }

    public void setTinhTrangTra(int tinhTrangTra) {
        TinhTrangTra = tinhTrangTra;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }


}
