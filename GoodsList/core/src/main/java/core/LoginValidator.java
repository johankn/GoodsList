package core;

import java.util.List;

import json.FileOperator;

public class LoginValidator {

    private FileOperator fileOperator;

    public LoginValidator() {
        this.fileOperator = new FileOperator();
    }

    public FileOperator getFileOperator() {
        return fileOperator;
    }

    private boolean doesUserNameExist(String username, List<User> listOfExistingUsers) {

        for (int i = 0; i < listOfExistingUsers.size(); i++) {
            if (username.equals(listOfExistingUsers.get(i).getUsername())) {
                return true;
            }
        }
        throw new IllegalArgumentException("This username does not exist");
    }

    private boolean doesUserNameAndPasswordMatch(String username, String password, List<User> listOfExistingUsers) {
        for (int i = 0; i < listOfExistingUsers.size(); i++ ) {
            if (username.equals(listOfExistingUsers.get(i).getUsername()) && password.equals(listOfExistingUsers.get(i).getPassword())) {
                return true;
            }
        }
        throw new IllegalArgumentException("This password is incorrect");
    }

    public boolean isLoginLegal(String username, String password, List<User> exsitingUsers){
        if (username.isBlank() || password.isBlank()){
            throw new IllegalArgumentException("You need to fill out all the fields!");
        }
        return ((doesUserNameExist(username, exsitingUsers))) && (doesUserNameAndPasswordMatch(username, password, exsitingUsers));
    }

}
