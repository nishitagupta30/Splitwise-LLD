package splitwise.models;

import java.util.UUID;

public class Transaction {

    private final String transactionId;
    private final User paidBy;
    private final User paidTo;
    private final double amount;

    public Transaction(User paidBy, User paidTo, double amount){
        this.transactionId = UUID.randomUUID().toString();
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.amount = amount;

        this.paidBy.getBalance().updateBalance(paidTo.getUsername(), amount);
        this.paidTo.getBalance().updateBalance(paidBy.getUsername(), -amount);
    }

    public String getTransactionId(){
        return this.transactionId;
    }
    
}
