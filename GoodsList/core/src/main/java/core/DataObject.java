package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataObject {

    //This class represents dataObjects.json as a java.util.Map.
    
    private Map<String, List<User>> jsonFileAsMap;
    private String filename;
    private ObjectMapper objectMapper;
    private User user;
    private boolean addUser;
    private List<User> userList;

    public DataObject(String filename, User user, boolean addUser) {
        this.filename = filename;
        this.user = user;
        this.addUser = addUser;
        objectMapper = new ObjectMapper();
        generateMapOfUsers();
    }

    public DataObject(String filename) {
        this.filename = filename;
        objectMapper = new ObjectMapper();
        generateMapOfUsers();
    }

    //Genrates a map og dataObject.json
    private void generateMapOfUsers(){
        String jsonString;
        try {
            jsonString = makeJsonObjectFromJsonFile().toString();
            jsonFileAsMap = objectMapper.readValue(jsonString, Map.class);
            if(addUser){
                addUserToMap(user);
            } else if (!addUser){
                updateUserActiveAds(user);
            }
            else if (user == null){
                setUserList();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addUserToMap(User userToBeAdded){
        List<User> userList = jsonFileAsMap.get("users");
        userList.add(userToBeAdded);
        jsonFileAsMap.put("users", userList);
    }

    private void updateUserActiveAds(User userToBeUpdated){
        List<User> userList = jsonFileAsMap.get("users");
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(userToBeUpdated.getUsername())){
                userList.set(i, userToBeUpdated);
            }
        }
    }

    private void setUserList(){
        userList = jsonFileAsMap.get("users");
    }

    public List<User> getUserList(){
        return userList;
    }

    //Help methood to make a jsonObject from the json-file
    private JSONObject makeJsonObjectFromJsonFile() throws JSONException, Exception {
        JSONObject jsonObject = new JSONObject(readFileAsString(filename));
        return jsonObject;
    }

    //Help methood to read a file as a String.
    private static String readFileAsString(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, List<User>> getJsonFileAsMap() {
        return jsonFileAsMap;
    }

    

    



    

}
