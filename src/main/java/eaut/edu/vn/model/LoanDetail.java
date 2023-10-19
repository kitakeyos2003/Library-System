package eaut.edu.vn.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class LoanDetail {
    private int loanId;
    private int bookId;
    private Date returnDate;
    private int borrowedStatus;
    private int returnStatus;
    private String userName;
    private String note;
}
