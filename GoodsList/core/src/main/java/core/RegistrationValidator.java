package core;
import java.util.List;
import java.util.regex.Pattern;

import json.FileOperator;

public class RegistrationValidator {

    /*  
     * This class represents a validator for registration.
     */
    
    private FileOperator fileOperator;

    public RegistrationValidator() {
        this.fileOperator = new FileOperator();
    }

    
    /** 
     * Getter 
     * @return FileOperator
     */
    public FileOperator getFileOperator() {
        return this.fileOperator;
    }

    
    /** 
     * checks if the username is valid. if not it throws IllegalArgumentException
     * @param username
     * @return boolean
     */
    public boolean isUsernameValid(String username) {
        if (!usernameValidation(username)) {
            throw new IllegalArgumentException("The username is not valid. It cannot be longer than 10 characters nor contain any special letters.");
        }
        return true;
    }

    
    /** 
     * checks if the password is valid. if not it throws IllegalArgumentException
     * @param password
     * @return boolean
     */
    public boolean isPasswordValid(String password) {
        if (!passwordValidation(password)) {
            throw new IllegalArgumentException("The password is not valid. It needs to be at least 8 characters long, has to contain at least one capital letter and one number.");
        }
        return true;
    }

    
    /** 
     * checks if the full name is valid. if not it throws IllegalArgumentException
     * @param fullname
     * @return boolean
     */
    public boolean isFullNameValid(String fullname) {
        if (!fullNameValidation(fullname)) {
            throw new IllegalArgumentException("The name is not valid. It needs to be your full name and has to start with a capital letter.");
        }
        return true;
    }

    
    /** 
     * checks if the username already exist. If true it throws IllegalArgumentException
     * @param excistingUsers
     * @param username
     * @return boolean
     */
    public boolean checkExcistingUsername(List<User> excistingUsers, String username) {
        for (int i = 0; i < excistingUsers.size(); i++) {
            if (username.equals(excistingUsers.get(i).getUsername())) {
                throw new IllegalArgumentException("This username is already taken.");
            }
        }
        return true;
    }

    
    /** 
     * checks if the the two passwords matches. If not it throws IllegalArgumentException
     * @param password1
     * @param password2
     * @return boolean
     */
    public boolean equalPasswords(String password1, String password2) {
        if (password1.equals(password2)) {
            return true;
        }
        else {
            throw new IllegalArgumentException("The two passwords doesnt match!");
        }
    }

    
    /** 
     * checks if the username is valid with regex. if not it throws IllegalArgumentException
     * @param username
     * @return boolean
     */
    private boolean usernameValidation(String username) {
        return Pattern.matches("^[a-zA-Z0-9ÆØÅæøå]{1,10}$", username);
    }

    
    /** 
     * checks if the password is valid with regex. if not it throws IllegalArgumentException
     * @param password
     * @return boolean
     */
    private boolean passwordValidation(String password) {
        return Pattern.matches("^(?=.*)(?=.*[a-z0-9ÆØÅæøå])(?=.*[A-Z0-9ÆØÅæøå])(?=.*[a-zA-Z0-9ÆØÅæøå]).{8,}$",  password);
    }

    
    /** 
     * checks if the fullname is valid with regex. if not it throws IllegalArgumentException
     * @param fullname
     * @return boolean
     */
    private boolean fullNameValidation(String fullname) {
        return Pattern.matches("^[ÆØÅA-Z][æøåa-zÆØÅA-Z]{2,}(?: [ÆØÅA-Z][æøåa-zÆØÅA-Z]*){0,2}$", fullname);
    }

    
    /** 
     * This method checks if the registration passes all the requierments to register a user.
     * Calls all methods in this class. If alle the fields is not filled in 
     * it throws IllegalArgumentException
     *  
     * @param username
     * @param password
     * @param repeatedpassword
     * @param fullname
     * @param excistingUsers
     * @return boolean
     */
    public boolean isRegistrationLegal(String username, String password, String repeatedpassword, String fullname, List<User> excistingUsers) {
        if (username.isBlank() || password.isBlank() || repeatedpassword.isBlank() || fullname.isBlank()) {
            throw new IllegalArgumentException("You have to fill out all of the input fields");
        }
        else {
            return isUsernameValid(username) &&
            isFullNameValid(fullname) && 
            isPasswordValid(password) &&
            equalPasswords(password, repeatedpassword) &&
            checkExcistingUsername(excistingUsers, username);
        }
    }


}
