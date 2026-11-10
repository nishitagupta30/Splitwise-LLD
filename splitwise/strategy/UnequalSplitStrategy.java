package splitwise.strategy;

import java.util.ArrayList;
import java.util.List;

import splitwise.models.User;
import splitwise.models.Split;

public class UnequalSplitStrategy implements SplitStrategy {

    @Override 
    public List<Split> createSplit(double totalAmount, User paidBy, List<User> participants, List<Double> splitValues){

        List<Split> splits = new ArrayList<>();

        if(splitValues == null || splitValues.size() == 0){
            throw new IllegalArgumentException("Empty split values, please define them correctly!");
        }
        if(participants == null || participants.size() == 0){
            throw new IllegalArgumentException("Empty participants, please define them correctly!");
        }

        double totalSum = 0.0;

        for(double i : splitValues){
            totalSum += i;
        }

        if(totalSum != totalAmount){
            throw new IllegalArgumentException("The split values don't add up to totalSum!");
        }

        for(int i = 0; i < participants.size(); i++){
            splits.add(new Split(participants.get(i), splitValues.get(i)));
        }

        return splits;
    }
    
}
