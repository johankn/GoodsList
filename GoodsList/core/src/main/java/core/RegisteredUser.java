package core;

public class RegisteredUser extends LoginUser {

    private String repeatedPassword;

    public RegisteredUser(String username, String password, String fullName, String repeatedPassword) {
        super(username, password, fullName);
        this.repeatedPassword = repeatedPassword;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }
    
}
