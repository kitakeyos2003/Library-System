package eaut.edu.vn.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int id;
    private String name;
    private String authorName;
    private String publishingCompany;
    private String category;
    private int quantity;
    private double price;

    @Override
    public String toString() {
        return name;
    }
}
