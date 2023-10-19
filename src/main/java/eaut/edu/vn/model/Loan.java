package eaut.edu.vn.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Loan {
    private int id;
    private int readerId;
    private Date borrowedDate;
    private Date returnDate;
    private int quantity;
    private String userId;
}
