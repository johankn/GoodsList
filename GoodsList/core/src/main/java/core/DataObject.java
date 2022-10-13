package core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataObject {

    //This class represents dataObjects.json as a java.util.Map.
    
    private Map<String, List<User>> jsonFileAsMap;
    private String filename;
    private ObjectMapper objectMapper;
    private User user;
    private boolean addUser;
    private List<User> userList;
    private JsonFileAsObject jsonFileAsObject;

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
        setUserList();
    }

    //Genrates a map og dataObject.json
    private void generateMapOfUsers(){
        String jsonString;
        try {
            jsonString = makeJsonObjectFromJsonFile().toString();
            jsonFileAsObject = objectMapper.readValue(jsonString , JsonFileAsObject.class);
            if(addUser){
                addUserToMap(user);
            } else if (!addUser){
                updateUserActiveAds(user);
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
        List<User> userList = jsonFileAsObject.getUsers();
        for (int i = 0; i < jsonFileAsObject.getUsers().size(); i++) {
            if (jsonFileAsObject.getUsers().get(i).getUsername().equals(userToBeUpdated.getUsername())){
                jsonFileAsObject.set(i, userToBeUpdated);
            }
        }
    }

    private void setUserList(){
        String jsonString;
        try {
            jsonString = makeJsonObjectFromJsonFile().toString();
            System.out.println(jsonString);
            //https://www.baeldung.com/jackson-object-mapper-tutorial
            int a = 0;
            jsonFileAsObject = objectMapper.readValue(jsonString , JsonFileAsObject.class);
            int b = 0;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //userList = jsonFileAsMap.get("users");
    }

    public List<User> getUserList(){
        return jsonFileAsObject.getUsers();
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
