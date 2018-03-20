import com.thoughtworks.step.Account;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    @Test
    public void checkBalance() {
        Account account = new Account("1234", 1000);
        assertThat(account.getBalance(),is(1000));
    }
}
