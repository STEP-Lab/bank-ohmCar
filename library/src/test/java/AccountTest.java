import com.thoughtworks.step.Account;
import com.thoughtworks.step.InvalidAccountNumberException;
import com.thoughtworks.step.MinimumBalanceException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    private Account account;

    @Before
    public void setUp() throws MinimumBalanceException, InvalidAccountNumberException {
        account = new Account("1111-2222", 5000);
    }

    @Test
    public void checkBalance() {
        assertThat(account.getBalance(),is(5000.0));
    }

    @Test
    public void checkAccountNumber() {
        assertThat(account.getAccountNumber(),is("1111-2222"));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalance () throws MinimumBalanceException,InvalidAccountNumberException {
        new Account("2222-1111",800);
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void validateAccountNumber() throws InvalidAccountNumberException, MinimumBalanceException{
        new Account("11-22",2000);
    }

    @Test
    public void checkCreditTransfer() throws MinimumBalanceException{
        assertThat(account.getBalance(),is(5000.0));
        assertThat(account.credit(2000),is(7000.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalanceCredit() throws MinimumBalanceException{
        assertThat(account.getBalance(),is(5000.0));
        account.credit(-1000);
    }

    @Test
    public void checkDebitTransfer() throws MinimumBalanceException{
        assertThat(account.getBalance(),is(5000.0));
        account.debit(3000);
        assertThat(account.getBalance(),is(2000.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void checkMinimumBalanceDebit() throws MinimumBalanceException{
        assertThat(account.getBalance(),is(5000.0));
        account.debit(4999);
    }
}
