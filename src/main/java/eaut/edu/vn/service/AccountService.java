package eaut.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.model.Account;

public class AccountService extends ConnectMySQL {
    public ArrayList<Account> layTaiKhoan() {
        ArrayList<Account> ds = new ArrayList<Account>();
        try {
            String sql = "Select * from taikhoan";
            PreparedStatement pre = connect.prepareStatement(sql);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Account tk = new Account();
                tk.setUser(result.getString(1));
                tk.setPass(result.getString(2));
                tk.setPhanQuyen(result.getInt(3));
                tk.setTenND(result.getString(4));
                tk.setSoDienThoai(result.getString(5));
                tk.setCMND(result.getString(6));
                ds.add(tk);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Account> layTaiKhoanTheoUser(String User) {
        ArrayList<Account> ds = new ArrayList<Account>();
        try {
            String sql = "Select * from taikhoan where user=?";
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, User);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                Account tk = new Account();
                tk.setUser(result.getString(1));
                tk.setPass(result.getString(2));
                tk.setPhanQuyen(result.getInt(3));
                tk.setTenND(result.getString(4));
                tk.setSoDienThoai(result.getString(5));
                tk.setCMND(result.getString(6));
                ds.add(tk);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ds;
    }
}

