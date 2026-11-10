package splitwise.models;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {

    private ConcurrentHashMap<String, Double> balances;
    private final String owner;

    public BalanceSheet(String owner) {
        this.owner = owner;
        this.balances = new ConcurrentHashMap<>();
    }

    public void updateBalance(String user, double amount){
        balances.merge(user, amount, Double::sum);
    }

    public HashMap<String, Double> getBalances(){
        return new HashMap<>(balances);
    }

    public void printCurrentBalanceStatus(){
        double amountToBePaid = 0.0;
        double amountOwed = 0.0;

        Iterator<Map.Entry<String, Double>> it = balances.entrySet().iterator();

        while(it.hasNext()){

            Map.Entry<String, Double> entry = it.next();

            if(entry.getValue() >= 0.01){
                amountToBePaid += entry.getValue();
                System.out.println("User " + entry.getKey() + "owes " + entry.getValue() + "ruppees to " + owner + ".");
            }else if(entry.getValue() < 0){
                amountOwed += Math.abs(entry.getValue());
                System.out.println("User " + entry.getKey() + "is owed " + Math.abs(entry.getValue()) + "ruppees by" + owner + ".");
            }

            System.out.println(owner + "owes a total of " + amountOwed);
            System.out.println(owner + "is owed a total of " + amountToBePaid);

        }
    }
    
}
