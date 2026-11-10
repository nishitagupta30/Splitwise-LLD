package splitwise.models;

import java.util.*;

public class Group {

    private final String groupId;
    private final String groupName;
    private final List<User> members;

    public Group(String groupName, List<User> members){
        this.groupId = UUID.randomUUID().toString();
        this.groupName = groupName;
        this.members = List.copyOf(members);
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<User> getMembers() {
        return members;
    }

}
