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
    }

    public String getTransactionId(){
        return this.transactionId;
    }

    public String toString(){
        String s = paidBy.getUsername() + " should pay " + amount + " to " + paidTo.getUsername();
        System.out.println(s);
        return s;
    }
    
}
