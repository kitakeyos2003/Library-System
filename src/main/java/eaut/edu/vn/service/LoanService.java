package eaut.edu.vn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.interfaces.IService;
import eaut.edu.vn.model.Loan;
import eaut.edu.vn.util.Log;


public class LoanService implements IService<Loan> {
    public List<Loan> getAll() {
        List<Loan> dspm = new ArrayList<Loan>();
        try {
            String sql = "select * from phieumuon";
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Loan loan = new Loan();
                loan.setId(result.getString(1));
                loan.setReaderId(result.getString(2));
                loan.setBorrowedDate(result.getDate(3));
                loan.setReturnDate(result.getDate(4));
                loan.setQuantity(result.getInt(5));
                loan.setUserId(result.getString(6));
                dspm.add(loan);
            }
            result.close();
            ps.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dspm;
    }

    @Override
    public void add(Loan loan) {

    }

    @Override
    public void remove(Loan loan) {

    }

    @Override
    public Loan find(Object obj) {
        return null;
    }

    public List<Loan> searchByUser(String username) {
        final List<Loan> loans = new ArrayList<>();
        DbManager.getInstance().executeQuery("SELECT * FROM phieumuon where user=?", result -> {
            try {
                if (result.next()) {
                    Loan loan = new Loan();
                    loan.setId(result.getString(1));
                    loan.setReaderId(result.getString(2));
                    loan.setBorrowedDate(result.getDate(3));
                    loan.setReturnDate(result.getDate(4));
                    loan.setQuantity(result.getInt(5));
                    loan.setUserId(result.getString(6));
                    loans.add(loan);
                }
            } catch (SQLException e) {
                Log.error("find loan err", e);
            }
        }, username);
        return loans;
    }


    public List<Loan> search(String readerId) {
        final List<Loan> loans = new ArrayList<>();
        DbManager.getInstance().executeQuery("SELECT * FROM phieumuon where madg=?", result -> {
            try {
                if (result.next()) {
                    Loan loan = new Loan();
                    loan.setId(result.getString(1));
                    loan.setReaderId(result.getString(2));
                    loan.setBorrowedDate(result.getDate(3));
                    loan.setReturnDate(result.getDate(4));
                    loan.setQuantity(result.getInt(5));
                    loan.setUserId(result.getString(6));
                    loans.add(loan);
                }
            } catch (SQLException e) {
                Log.error("find loan err", e);
            }
        }, readerId);
        return loans;
    }

    public static LoanService getInstance() {
        return LoanService.SINGLETON.INSTANCE;
    }

    public static final class SINGLETON {

        public static final LoanService INSTANCE = new LoanService();

    }

}
