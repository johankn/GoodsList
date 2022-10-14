package core;

import java.util.ArrayList;

public class RegisteredUser{

    private String username;
    private String password;
    private String fullName;
    private String repeatedPassword;


    public RegisteredUser(String username, String password, String fullName, String repeatedPassword) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.repeatedPassword = repeatedPassword;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getRepeatedPassword() {
        return this.repeatedPassword;
    }

    public User generateUser(){
        return new User(this.getUsername(),
                        this.getPassword(),
                        this.getFullName()
                    );
    }


    
}
