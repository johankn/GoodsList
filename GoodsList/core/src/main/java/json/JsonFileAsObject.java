package json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.User;

public class JsonFileAsObject {

    /* 
     * This class represents a jsonfiule with list of users as a java object
     */
    
    private List<User> users;

    /* 
     * JsonCreator to make jackson understand that this constructor should be used.
     * JsonProperty to tell json what to look for in json file when make this object
     */
    @JsonCreator
    public JsonFileAsObject(@JsonProperty(value = "users") List<User> users) {
        this.users = users;
    }

    
    /** 
     * Return the users
     * @return List<User>
     */
    public List<User> getUsers() {
        return users;
    }

    
    /** 
     * Setter for users
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    
    /** 
     * Adds a user
     * @param user
     */
    public void addUser(User user){
        users.add(user);
    }

    



    
}
