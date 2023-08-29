package eaut.edu.vn.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectMySQL {
    public static Connection connect;

    public ConnectMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/qlsach", "root", "");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
