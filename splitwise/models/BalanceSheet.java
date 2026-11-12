package splitwise.models;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {

    private Map<User, Double> balances = new ConcurrentHashMap<>();
    private final String owner;

    public BalanceSheet(String owner) {
        this.owner = owner;
    }

    public void updateBalance(User user, double amount){
        balances.merge(user, amount, Double::sum);
    }

    public Map<User, Double> getBalances(){
        return balances;
    }

    public void printCurrentBalanceStatus(){
        double amountToBePaid = 0.0;
        double amountOwed = 0.0;

        Iterator<Map.Entry<User, Double>> it = balances.entrySet().iterator();

        while(it.hasNext()){

            Map.Entry<User, Double> entry = it.next();

            if(entry.getValue() >= 0.01){
                amountToBePaid += entry.getValue();
                System.out.println("User " + entry.getKey().getUsername() + "owes " + entry.getValue() + "ruppees to " + owner + ".");
            }else if(entry.getValue() < 0){
                amountOwed += Math.abs(entry.getValue());
                System.out.println("User " + entry.getKey().getUsername() + "is owed " + Math.abs(entry.getValue()) + "ruppees by" + owner + ".");
            }

            System.out.println(owner + "owes a total of " + amountOwed);
            System.out.println(owner + "is owed a total of " + amountToBePaid);

        }
    }
    
}
