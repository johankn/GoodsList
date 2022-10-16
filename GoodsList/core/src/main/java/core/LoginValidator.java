package core;

import java.util.List;

import json.FileOperator;

public class LoginValidator {

    /* 
     * This class represents a validator for logging in.
     */

    private FileOperator fileOperator;

    public LoginValidator() {
        this.fileOperator = new FileOperator();
    }

    
    /** 
     * getter method
     * @return FileOperator
     */
    public FileOperator getFileOperator() {
        return fileOperator;
    }

    
    /** 
     * Checks if the username exist and throws IllegalArgumentException if not.
     * @param username
     * @param listOfExistingUsers
     * @return boolean
     */
    private boolean doesUserNameExist(String username, List<User> listOfExistingUsers) {

        for (int i = 0; i < listOfExistingUsers.size(); i++) {
            if (username.equals(listOfExistingUsers.get(i).getUsername())) {
                return true;
            }
        }
        throw new IllegalArgumentException("This username does not exist");
    }

    
    /** 
     * Checks if the passwords match. Throws IllegalArgumentException if not.
     * @param username
     * @param password
     * @param listOfExistingUsers
     * @return boolean
     */
    private boolean doesUserNameAndPasswordMatch(String username, String password, List<User> listOfExistingUsers) {
        for (int i = 0; i < listOfExistingUsers.size(); i++ ) {
            if (username.equals(listOfExistingUsers.get(i).getUsername()) && password.equals(listOfExistingUsers.get(i).getPassword())) {
                return true;
            }
        }
        throw new IllegalArgumentException("This password is incorrect");
    }

    
    /** 
     * Method that checks if the log in is legal by using two help methods.
     * @param username
     * @param password
     * @param exsitingUsers
     * @return boolean
     */
    public boolean isLoginLegal(String username, String password, List<User> exsitingUsers){
        if (username.isBlank() || password.isBlank()){
            throw new IllegalArgumentException("You need to fill out all the fields!");
        }
        return ((doesUserNameExist(username, exsitingUsers))) && (doesUserNameAndPasswordMatch(username, password, exsitingUsers));
    }

}
