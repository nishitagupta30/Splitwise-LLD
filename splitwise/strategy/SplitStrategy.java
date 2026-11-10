package splitwise.strategy;

import splitwise.models.Split;
import splitwise.models.User;
import java.util.*;

public interface SplitStrategy{

    public List<Split> createSplit(double totalAmount, User paidBy, List<User> splitBetween, List<Double> splitValues);

}