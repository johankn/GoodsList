package core;
import java.util.regex.Pattern;

public class RegistrationValidator {
    
    private FileOperator fileOperator;

    public RegistrationValidator() {
        this.fileOperator = new FileOperator();
    }

    public boolean isUsernameValid(String username) {
        if (!usernameValidation(username)) {
            throw new IllegalArgumentException("The username is not valid. It cannot be longer than 10 characters nor contain any special letters.");
        }
        return true;
    }

    public boolean isPasswordValid(String password) {
        if (!passwordValidation(password)) {
            throw new IllegalArgumentException("The password is not valid. It needs to be at least 8 characters long, has to contain at least one capital letter and one number.");
        }
        return true;
    }

    public boolean isFullNameValid(String fullname) {
        if (!fullNameValidation(fullname)) {
            throw new IllegalArgumentException("The name is not valid. It needs to be your full name and has to start with a capital letter.");
        }
        return true;
    }

    public boolean checkExcistingUsername(String username) {
        // mangler lese fra fil med JSON
        // if brukernavn finnes fra før:
        //     throw new IllegalArgumentException("Brukernavnet er allerede tatt");
        return true;
    }

    public boolean equalPasswords(String password1, String password2) {
        if (password1.equals(password2)) {
            return true;
        }
        else {
            throw new IllegalArgumentException("The two passwords doesnt match!");
        }
    }

    private boolean usernameValidation(String username) {
        return Pattern.matches("^[a-zA-Z0-9ÆØÅæøå]+${1,11}", username);
    }

    private boolean passwordValidation(String password) {
        return Pattern.matches("^(?=.*)(?=.*[a-z0-9ÆØÅæøå])(?=.*[A-Z0-9ÆØÅæøå])(?=.*[a-zA-Z0-9ÆØÅæøå]).{8,}$",  password);
    }

    private boolean fullNameValidation(String fullname) {
        return Pattern.matches("^[ÆØÅA-Z][æøåa-zÆØÅA-Z]{2,}(?: [ÆØÅA-Z][æøåa-zÆØÅA-Z]*){0,2}$", fullname);
    }

    public boolean isRegistrationLegal(String username, String password, String repeatedpassword, String fullname) {
        if (username.isBlank() || password.isBlank() || repeatedpassword.isBlank() || fullname.isBlank()) {
            throw new IllegalArgumentException("You have to fill out all of the input fields");
        }
        if (isUsernameValid(username) &&
        isFullNameValid(fullname) && 
        isPasswordValid(password) &&
        equalPasswords(password, repeatedpassword) &&
        checkExcistingUsername(username)) {
            return true;
        }
        else {
            return false;
        }
    }

}
