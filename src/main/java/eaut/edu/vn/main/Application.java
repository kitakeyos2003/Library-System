package eaut.edu.vn.main;

import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.service.ReaderService;
import eaut.edu.vn.ui.*;

public class Application {

    public static Account account;
    public static void main(String[] args) {
        DbManager.getInstance().start();
        SINGLETON.LOGIN.showWindow();
    }

    public static final class SINGLETON {

        public static final Login LOGIN = new Login("Đăng nhập hệ thống");
        public static final AccountManager ACCOUNT_MANAGER =  new AccountManager("Quản lý người dùng");
        public static final AdminManager ADMIN_MANAGER =  new AdminManager("Trang chủ");
        public static final BookManager BOOK_MANAGER =  new BookManager("Quản lý sách");
        public static final LibrarianManager LIBRARIAN_MANAGER =  new LibrarianManager("Trang chủ");
        public static final LoanManager LOAN_MANAGER =  new LoanManager("Quản lý phiếu mượn");
        public static final ReaderManager READER_MANAGER =  new ReaderManager("Quản lý độc giả");
        public static final ReturnManager RETURN_MANAGER =  new ReturnManager("Quản lý phiếu trả");
        public static final StatisticAnalyzer STATISTIC_ANALYZER =  new StatisticAnalyzer("Thống kê");
    }
}
