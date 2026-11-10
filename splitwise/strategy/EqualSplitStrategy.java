package splitwise.strategy;

import java.util.ArrayList;
import java.util.List;

import splitwise.models.User;
import splitwise.models.Split;

public class EqualSplitStrategy implements SplitStrategy{

    @Override 
    public List<Split> createSplit(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues){
        List<Split> splits = new ArrayList<>();

        double amountPerPerson = totalAmount/participants.size();

        for(User participant : participants){
            splits.add(new Split(participant, amountPerPerson));
        }

        return splits;
    }
}
