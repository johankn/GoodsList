package core;

import java.util.List;

public class LoginValidator {

    private static final String filename = "..//core/src/main/java/json/dataObjects.json";
    private FileOperator fileOperator;

    public LoginValidator() {
        this.fileOperator = new FileOperator();
    }

    public FileOperator getFileOperator() {
        return fileOperator;
    }

    private boolean doesUserNameExist(String username, List<String> listOfExistingUsers) {

        for (int i = 0; i < listOfExistingUsers.size(); i += 3) {
            if (username.equals(listOfExistingUsers.get(i))) {
                return true;
            }
        }
        throw new IllegalArgumentException("This username does not exist");
    }

    private boolean doesUserNameAndPasswordMatch(String username, String password, List<String> listOfExistingUsers) {
        for (int i = 0; i < listOfExistingUsers.size(); i += 3) {
            if (username.equals(listOfExistingUsers.get(i)) && password.equals(listOfExistingUsers.get(i + 1))) {
                return true;
            }
        }
        throw new IllegalArgumentException("This password is incorrect");
    }

    public boolean isLoginLegal(String username, String password){
        if (username.isBlank() || password.isBlank()){
            throw new IllegalArgumentException("You need to fill out all the fields!");
        }
        return ((doesUserNameExist(username, fileOperator.getAllUsersAsList(filename))) && (doesUserNameAndPasswordMatch(username, password, fileOperator.getAllUsersAsList(filename))));
    }

}
