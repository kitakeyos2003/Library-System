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
        List<LoanDetail> list = new ArrayList<LoanDetail>();
        try {
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement loanDetail = connection.prepareStatement("Select * from ctpm");
            ResultSet result = loanDetail.executeQuery();
            while (result.next()) {
                LoanDetail detail = new LoanDetail();
                detail.setLoanId(result.getInt(1));
                detail.setBookId(result.getInt(2));
                detail.setReturnDate(result.getDate(3));
                detail.setBorrowedStatus(result.getInt(4));
                detail.setReturnStatus(result.getInt(5));
                detail.setUserName(result.getString(6));
                detail.setNote(result.getString(7));
                list.add(detail);
            }
            result.close();
            loanDetail.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
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
