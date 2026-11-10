package splitwise.strategy;

import java.util.ArrayList;
import java.util.List;

import splitwise.models.User;
import splitwise.models.Split;

public class PercentageSplitStrategy implements SplitStrategy {

    @Override 
    public List<Split> createSplit(double totalAmount, User paidBy, List<String> participants, List<Double> splitValues){

        List<Split> splits = new ArrayList<>();

        if(splitValues == null || splitValues.size() == 0){
            throw new IllegalArgumentException("Empty split values, please define them correctly!");
        }
        if(participants == null || participants.size() == 0){
            throw new IllegalArgumentException("Empty participants, please define them correctly!");
        }

        double percentageSum = 0.0;

        for(double i : splitValues){
            percentageSum += i;
        }

        if(percentageSum != 100){
            throw new IllegalArgumentException("The percentage don't add up to 100%!");
        }

        for(int i = 0; i < participants.size(); i++){
            double amountPerPerson = (totalAmount * splitValues.get(i))/100;
            splits.add(new Split(participants.get(i), amountPerPerson));
        }

        return splits;
    }
    
}
