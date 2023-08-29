package eaut.edu.vn.model;

public class Account {
    private String User;
    private String Pass;
    private int PhanQuyen;
    private String TenND;
    private String SoDienThoai;
    private String CMND;

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public int getPhanQuyen() {
        return PhanQuyen;
    }

    public void setPhanQuyen(int phanQuyen) {
        PhanQuyen = phanQuyen;
    }

    public String getTenND() {
        return TenND;
    }

    public void setTenND(String tenND) {
        TenND = tenND;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String cMND) {
        CMND = cMND;
    }


}
