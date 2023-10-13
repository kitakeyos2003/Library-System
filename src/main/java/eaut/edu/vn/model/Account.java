package eaut.edu.vn.model;

public class Account {
    private String username;
    private String password;
    private int role;
    private String name;
    private String phoneNumber;
    private String identityNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int phanQuyen) {
        role = phanQuyen;
    }

    public String getName() {
        return name;
    }

    public void setName(String tenND) {
        name = tenND;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String soDienThoai) {
        phoneNumber = soDienThoai;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String cMND) {
        identityNumber = cMND;
    }


}
