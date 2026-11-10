package splitwise.models;

import java.util.UUID;

public class User {

    private final String userId;
    private final String username;
    private BalanceSheet balance;

    public User(String username) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.balance = new BalanceSheet(username);
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public BalanceSheet getBalance() {
        return balance;
    }

}
