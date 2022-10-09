package core;

public class LoginUser {
    
    private String username;
    private String password;
    private String fullName;

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginUser(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }
    
    public String getfullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }
    
}
