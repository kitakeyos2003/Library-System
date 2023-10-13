package eaut.edu.vn.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.interfaces.IService;
import eaut.edu.vn.model.Loan;


public class LoanService implements IService<Loan> {
    public ArrayList<Loan> getAll() {
        ArrayList<Loan> dspm = new ArrayList<Loan>();
        try {
            String sql = "select * from phieumuon";
            PreparedStatement loan = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet result = loan.executeQuery();
            while (result.next()) {
                Loan pm = new Loan();
                pm.setId(result.getString(1));
                pm.setReaderName(result.getString(2));
                pm.setBorrowedDate(result.getDate(3));
                pm.setReturnDate(result.getDate(4));
                pm.setQuantity(result.getInt(5));
                pm.setUserId(result.getString(6));
                dspm.add(pm);
            }
            result.close();
            loan.close();
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

    public static LoanService getInstance() {
        return LoanService.SINGLETON.INSTANCE;
    }

    public static final class SINGLETON {

        public static final LoanService INSTANCE = new LoanService();

    }

}
