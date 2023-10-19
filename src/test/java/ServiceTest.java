import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.model.Account;
import eaut.edu.vn.service.AccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ServiceTest {

    @Before
    public void setUp() {
        DbManager.getInstance().start();
    }

    @After
    public void tearDown() {
        DbManager.getInstance().shutdown();
    }

    @Test
    public void useService() {
        List<Account> accounts = AccountService.getInstance().getAll();
        for (Account account : accounts) {
            System.out.println("account - username: " + account.getName() + " password: " + account.getPassword());
        }
    }
}
