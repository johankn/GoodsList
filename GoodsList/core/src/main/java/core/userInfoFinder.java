package core;

import java.util.List;

public class userInfoFinder {

    //This class find info about the user by information we allready know about it. 

    private FileOperator fileOperator;
    private List<String> allExistingUsers;
    private static final String filename = "..//core/src/main/java/json/dataObjects.json";

    public userInfoFinder() {
        this.fileOperator = new FileOperator();
    }
    
    //Finds the full name of a user by its username.
    public String getFullNameByUsername(String userName){
        allExistingUsers = fileOperator.getAllUsersAsList(filename);
        for (int i = 0; i < allExistingUsers.size(); i+=3) {
            if(userName.equals(allExistingUsers.get(i))){
                return allExistingUsers.get(i+2);
            }
        }
        throw new NullPointerException("This user does not exist");
    }
    
}
