package eaut.edu.vn.main;

import com.formdev.flatlaf.FlatLightLaf;
import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.ui.Login;

public class Application {

    public static Account account;

    public static void main(String[] args) {
        DbManager.getInstance().start();
        FlatLightLaf.setup();
        Login LOGIN = new Login("Đăng nhập hệ thống");
        LOGIN.showWindow();
    }
}
