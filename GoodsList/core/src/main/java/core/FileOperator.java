package core;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
            objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsObject());
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    public void updateUserObjectJsonFile(String filename, User user){
        dataObject = new DataObject(filename, user, false);
        try {
            objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsObject());
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
   
}