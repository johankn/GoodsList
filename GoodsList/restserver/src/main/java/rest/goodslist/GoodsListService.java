package rest.goodslist;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.AdSorter;
import core.RegisteredUser;
import json.Ad;
import json.FileOperator;
import json.JsonFileAsObject;
import json.User;

public class GoodsListService {
  

  private FileOperator fileOperator;
  private String filename;
  private List<User> users;
  private List<Ad> ads;
  private JsonFileAsObject jsonFile;

  public GoodsListService(String filename) {
    this.fileOperator = new FileOperator();
    this.filename = filename;
    //this.objectMapper = fileOperator.getObjectMapper();
    users = fileOperator.getAllUsersAsList(filename);
    ads = fileOperator.getAllAdsInFile(filename);
    jsonFile = fileOperator.getJsonFileAsObject(filename);
  }

  private void autoSave(RegisteredUser registeredUser) {
    // try {
    //   fileOperator.writeNewUserDataToFile(filename, registeredUser);
    //   users = fileOperator.getAllUsersAsList(filename);
    //   ads = fileOperator.getAllAdsInFile(filename);
    //   jsonFile = fileOperator.getJsonFileAsObject(filename);
    // } catch (IllegalStateException e) {
    //   System.err.println(e);
    // }
  }

  public List<User> getUsers() {
    return this.users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public void addUser(User user) {
    // this.users.add(user.generateUser());
    // autoSave(user);
    new FileOperator().writeNewUserDataToFile(filename, user);
    users = fileOperator.getAllUsersAsList(filename);
  }
  
  public List<Ad> getAds() {
    return this.ads;
  }

  public List<Ad> getActiveAds() {
    return new AdSorter(this.ads).sortAds(ad -> ad.getIsSold() == false);
  }

  public JsonFileAsObject getJsonFileAsObject() {
    return this.jsonFile;
  }



  public User getUserByUsername(String username) {
    return jsonFile.getUserByUsername(username);
  }
}
