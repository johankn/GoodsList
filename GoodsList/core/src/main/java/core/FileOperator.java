package core;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.FileNotFoundException;

public class FileOperator {

    private ObjectMapper objectMapper;
    private ObjectWriter objectWriter;
    private DataObject dataObject;

    public FileOperator() {
        objectMapper = new ObjectMapper();
        objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
    }


    // Writes a user to the json-file
    public void writeNewUserDataToFile(String filename, RegisteredUser registeredUser) {
        dataObject = new DataObject(filename, registeredUser.generateUser(), true);
        try {
            objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsMap());
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    public void updateUserObjectJsonFile(String filename, User user){
        dataObject = new DataObject(filename, user, false);
        try {
            objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsMap());
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    
    //Methood to get all the users in the jsonfile on the fromat: [username, password, fullname, username, password,...]
    public List<User> getAllUsersAsList(String filename){
        dataObject = new DataObject(filename);
        List<User> list = dataObject.getUserList();
        return list;
    }

    public static void main(String[] args) {
        FileOperator f = new FileOperator();
        System.out.println(f.getAllUsersAsList("GoodsList/core/src/main/java/json/dataObjects.json"));
        List<Ad> listOfAds = new ArrayList<>();
        /* this.pages = pages;
        this.author = author;
        this.genre = genre;
        this.releaseYear = releaseYear; */
        Books book1 = new Books(12, "Good", "Mikkemus", "embre", "comic", 2003, 75);
        Ad ad1 = new Ad(book1, "12.10.2022", "very nice book");
        listOfAds.add(ad1);
        User user = new User("eliasls", "kookok", "Mathias VAl", listOfAds);
        f.updateUserObjectJsonFile("GoodsList/core/src/main/java/json/dataObjects.json", user);
        
    }

}
