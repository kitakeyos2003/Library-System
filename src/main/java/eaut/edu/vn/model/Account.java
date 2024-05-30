package eaut.edu.vn.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private int id;
    private String username;
    private String password;
    private int role;
    private String name;
    private String phoneNumber;
    private String identityNumber;
}
