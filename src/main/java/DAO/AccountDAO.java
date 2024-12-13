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
}
