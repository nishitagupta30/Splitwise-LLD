package splitwise;

import java.util.concurrent.ConcurrentHashMap;

import splitwise.models.*;
import java.util.*;

public class SplitwiseService {

    private static SplitwiseService instance;
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Group> groups = new ConcurrentHashMap<>();

    private SplitwiseService(){};

    public static synchronized SplitwiseService getInstance(){
        if(instance == null){
            instance = new SplitwiseService();
        }

        return instance;
    }

    public User addUser(String username){
        User user = new User(username);
        users.put(user.getUserId(), user);
        return user;
    }

    public Group createGroup(String groupName, List<User> members){
        Group group = new Group(groupName, members);
        groups.put(group.getGroupId(), group);
        return group;
    }

    public User getUser(String id){
        return users.get(id);
    }

    public Group getGroup(String id){
        return groups.get(id);
    }

    public synchronized void createExpense(Expense.ExpenseBuilder builder) {
        Expense expense = builder.build();
        User paidBy = expense.getPaidBy();

        for (Split split : expense.getSplits()) {
            User participant = split.getUser();
            double amount = split.getSplitAmount();

            if (!paidBy.equals(participant)) {
                paidBy.getBalance().updateBalance(participant, amount);
                participant.getBalance().updateBalance(paidBy, -amount);
            }
        }
        System.out.println("Expense '" + expense.getDescription() + "' of amount " + expense.getAmount() + " created.");
    }

    public void simplifyGroupDebts(String groupId){
        Group group = getGroup(groupId);

        if(group == null) return;

        List<User> members = group.getMembers();

        Map<User, Double> simplifiedDebts = new HashMap<>();


        for(User member : members){
            Map<User, Double> balances = member.getBalance().getBalances();
            double balance = 0.0;

            for(Map.Entry<User, Double> entry = balances.entrySet()){
                if(members.contains(entry.getKey())){
                    balance += entry.getValue();
                }
            }

            simplifiedDebts.put(member, balance);
        }





    }
    
}
 