package eaut.edu.vn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.interfaces.IService;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.util.Log;

import javax.swing.*;

public class AccountService implements IService<Account> {
    @Override
    public List<Account> getAll() {
        final List<Account> list = new ArrayList<>();
        DbManager.getInstance().executeQuery("Select * from taikhoan", result -> {
            try {
                while (result.next()) {
                    Account account = new Account();
                    account.setUsername(result.getString(1));
                    account.setPassword(result.getString(2));
                    account.setRole(result.getInt(3));
                    account.setName(result.getString(4));
                    account.setPhoneNumber(result.getString(5));
                    account.setIdentityNumber(result.getString(6));
                    list.add(account);
                }
            } catch (SQLException e) {
                Log.error("getAll account err, ", e);
            }
        });
        return list;
    }

    @Override
    public void add(Account account) {
        try {
            String sql = "insert into taikhoan values (?,?,?,?,?,?)";
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setInt(3, account.getRole());
            stmt.setString(4, account.getName());
            stmt.setString(5, account.getPhoneNumber());
            stmt.setString(6, account.getIdentityNumber());
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Log.error("Insert account failed!", ex);
        }
    }

    @Override
    public void remove(Account account) {
        try {
            String sql = "Delete from taikhoan where user=?";
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, account.getUsername());
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Log.error("Delete account failed!", ex);
        }
    }

    @Override
    public Account find(Object obj) {
        final Account[] accounts = new Account[1];
        DbManager.getInstance().executeQuery("SELECT * FROM `taikhoan` WHERE User =  ? LIMIT 1;", resultSet -> {
            try {
                if (resultSet.next()) {
                    Account account = new Account();
                    account.setUsername(resultSet.getString(1));
                    account.setPassword(resultSet.getString(2));
                    account.setRole(resultSet.getInt(3));
                    account.setName(resultSet.getString(4));
                    account.setPhoneNumber(resultSet.getString(5));
                    account.setIdentityNumber(resultSet.getString(6));
                    accounts[0] = account;
                }
            } catch (SQLException e) {
                Log.error("authenticate err", e);
            }
        }, obj);
        return accounts[0];
    }

    public List<Account> search(String user) {
        final List<Account> list = new ArrayList<>();
        DbManager.getInstance().executeQuery("Select * from taikhoan where user=?", result -> {
            try {
                while (result.next()) {
                    Account account = new Account();
                    account.setUsername(result.getString(1));
                    account.setPassword(result.getString(2));
                    account.setRole(result.getInt(3));
                    account.setName(result.getString(4));
                    account.setPhoneNumber(result.getString(5));
                    account.setIdentityNumber(result.getString(6));
                    list.add(account);
                }
            } catch (SQLException e) {
                Log.error("search account err, ", e);
            }
        }, user);
        return list;
    }

    public Account authenticate(String username, String password) {
        final Account[] accounts = new Account[1];
        DbManager.getInstance().executeQuery("SELECT * FROM `taikhoan` WHERE User =  ? AND Password = ? LIMIT 1;", resultSet -> {
            try {
                if (resultSet.next()) {
                    Account account = new Account();
                    account.setUsername(resultSet.getString(1));
                    account.setPassword(resultSet.getString(2));
                    account.setRole(resultSet.getInt(3));
                    account.setName(resultSet.getString(4));
                    account.setPhoneNumber(resultSet.getString(5));
                    account.setIdentityNumber(resultSet.getString(6));
                    accounts[0] = account;
                }
            } catch (SQLException e) {
                Log.error("authenticate err", e);
            }
        }, username, password);
        return accounts[0];
    }

    public void update(Account account) {
        try {
            String sql = "Update taikhoan set Password=?,PhanQuyen=?,TenND=?,SDT=?,CMND=? where User=?";
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, account.getPassword());
            stmt.setInt(2, account.getRole());
            stmt.setString(3, account.getName());
            stmt.setString(4, account.getPhoneNumber());
            stmt.setString(5, account.getIdentityNumber());
            stmt.setString(6, account.getUsername());
            int x = stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Log.error("Update account failed!", ex);
        }
    }

    public static AccountService getInstance() {
        return AccountService.SINGLETON.INSTANCE;
    }

    public static final class SINGLETON {

        public static final AccountService INSTANCE = new AccountService();

    }
}

