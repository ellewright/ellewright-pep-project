package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account createNewAccount(Account account) {
        return accountDAO.createNewAccount(account);
    }

    public Account loginAccount(Account account) {
        return accountDAO.loginAccount(account);
    }
}
