package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

public class AccountDAO {
    public Account createNewAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();

            ResultSet primaryKeys = preparedStatement.getGeneratedKeys();
            if (primaryKeys.next()) {
                int generated_account_id = (int) primaryKeys.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Account loginAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        Account retrievedAccount = new Account();

        try {
            String sql = "SELECT account_id, username, password FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                retrievedAccount.setAccount_id(resultSet.getInt(1));
                retrievedAccount.setUsername(resultSet.getString(2));
                retrievedAccount.setPassword(resultSet.getString(3));
            }

            return retrievedAccount;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
