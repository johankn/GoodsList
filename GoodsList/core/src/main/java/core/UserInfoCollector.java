package core;

import java.util.List;
public class UserInfoCollector {
    //This class find info about the user by information we allready know about it. 

    private FileOperator fileOperator;

    public UserInfoCollector() {
        this.fileOperator = new FileOperator();
    }
    
    //Finds the full name of a user by its username.
    public String getFullNameByUsername(String filename, String userName){
        List<User> allExistingUsers = fileOperator.getAllUsersAsList(filename);
        for (int i = 0; i < allExistingUsers.size(); i++) {
            if(userName.equals(allExistingUsers.get(i).getUsername())){
                return allExistingUsers.get(i).getFullname();
            }
        }
        throw new NullPointerException("This user does not exist");
    }

    public FileOperator getFileOperator() {
        return fileOperator;
    }
}
