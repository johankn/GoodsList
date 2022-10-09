package core;

public class RegistratedUser extends LoginUser {

    private String repeatedPassword;

    public RegistratedUser(String username, String password, String fullName, String repeatedPassword) {
        super(username, password, fullName);
        this.repeatedPassword = repeatedPassword;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }
    
}
