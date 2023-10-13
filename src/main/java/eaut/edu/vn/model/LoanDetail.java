package eaut.edu.vn.model;

import java.sql.Date;

public class LoanDetail {
    private String loanId;
    private String bookId;
    private Date returnDate;
    private int borrowedStatus;
    private int returnStatus;
    private String userName;
    private String note;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getBorrowedStatus() {
        return borrowedStatus;
    }

    public void setBorrowedStatus(int borrowedStatus) {
        this.borrowedStatus = borrowedStatus;
    }

    public int getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(int returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
