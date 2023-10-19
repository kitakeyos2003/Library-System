package eaut.edu.vn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.interfaces.IService;
import eaut.edu.vn.model.LoanDetail;


public class LoanDetailService implements IService<LoanDetail> {

    @Override
    public List<LoanDetail> getAll() {
        List<LoanDetail> dspm = new ArrayList<LoanDetail>();
        try {
            String sql = "Select * from ctpm";
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement loanDetail = connection.prepareStatement(sql);
            ResultSet result = loanDetail.executeQuery();
            while (result.next()) {
                LoanDetail ctpm = new LoanDetail();
                ctpm.setLoanId(result.getInt(1));
                ctpm.setBookId(result.getInt(2));
                ctpm.setReturnDate(result.getDate(3));
                ctpm.setBorrowedStatus(result.getInt(4));
                ctpm.setReturnStatus(result.getInt(5));
                ctpm.setUserName(result.getString(6));
                ctpm.setNote(result.getString(7));
                dspm.add(ctpm);
            }
            result.close();
            loanDetail.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dspm;
    }

    @Override
    public void add(LoanDetail loanDetail) {

    }

    @Override
    public void remove(LoanDetail loanDetail) {

    }

    @Override
    public LoanDetail find(Object obj) {
        return null;
    }

    public static LoanDetailService getInstance() {
        return LoanDetailService.SINGLETON.INSTANCE;
    }

    public static final class SINGLETON {

        public static final LoanDetailService INSTANCE = new LoanDetailService();

    }

}
