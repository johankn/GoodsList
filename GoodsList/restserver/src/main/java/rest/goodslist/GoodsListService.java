package rest.goodslist;

import java.io.IOException;
import java.util.ArrayList;
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
  private User activeUser;

  public GoodsListService() {
    this.fileOperator = new FileOperator();
    this.filename = "..//ui/src/main/resources/ui/dataObjects.json";
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

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public void addUser(User user) {
    // this.users.add(user.generateUser());
    // autoSave(user);
    new FileOperator().writeNewUserDataToFile(filename, user);
    this.users = fileOperator.getAllUsersAsList(filename);
  }
  
  public List<Ad> getAds() {
    this.ads = fileOperator.getAllAdsInFile(this.filename);
    return this.ads;
  }

  public List<Ad> getActiveAds() {
    this.ads = fileOperator.getAllAdsInFile(this.filename);
    return new AdSorter(this.ads).sortAds(ad -> ad.getIsSold() == false);
  }

  public JsonFileAsObject getJsonFileAsObject() {
    return this.jsonFile;
  }

  public User getUserByUsername(String username) {
    return jsonFile.getUserByUsername(username);
  }

  public void addAd(Ad ad) {
    new FileOperator().addAdToFile(filename, ad);
    ads = fileOperator.getAllAdsInFile(filename);
  }

  public List<Ad> getAllAds() {
    this.ads = fileOperator.getAllAdsInFile(this.filename);
    return new ArrayList<Ad>(this.ads);
  }

  public void updateUser(User user) {
    new FileOperator().updateUserObjectJsonFile(filename, user);
  }

  public void updateAd(Ad ad) {
    new FileOperator().updateAdObjectJsonFile(filename, ad);
  }
}
