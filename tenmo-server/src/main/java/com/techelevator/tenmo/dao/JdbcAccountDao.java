package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;


    public JdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BigDecimal getBalance(int userId) {
        BigDecimal balance = null;
        String sql = "SELECT balance FROM accounts WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);

        while (result.next()) {
            balance = result.getBigDecimal("balance");
        }

        return balance;
    }


    @Override
    public Account getAccountByUserId(int userId) {
        Account account = null;

        String sql = "SELECT * FROM account WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while (result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }

    @Override
    public Account getAccountByAccountId(int accountId) {
        Account account = null;

        String sql = "SELECT * FROM account WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);
        while (result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }



    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id")); // Need Setter in Account
        account.setUserId(rowSet.getInt("user_id")); // Need Setter in Account
        account.setBalance(rowSet.getBigDecimal("balance"));
        return account;
    }
}