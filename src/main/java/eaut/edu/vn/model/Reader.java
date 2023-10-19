package eaut.edu.vn.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reader {
    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private String sex;
    private int lostBooks;

    @Override
    public String toString() {
        return name;
    }

}
