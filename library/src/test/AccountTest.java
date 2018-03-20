import com.thoughtworks.step.Account;
import com.thoughtworks.step.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws MinimumBalanceException {
        account = new Account("1234", 1000);
    }

    @Test
    public void checkBalance() {
        assertThat(account.getBalance(),is(1000));
    }

    @Test
    public void checkAccountNumber() {
        assertThat(account.getAccountNumber(),is("1234"));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance () throws MinimumBalanceException {
        new Account("2345",200);
    }
}
