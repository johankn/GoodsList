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

  public GoodsListService(String filename) {
    this.fileOperator = new FileOperator();
    this.filename = filename;
    //this.objectMapper = fileOperator.getObjectMapper();
    users = fileOperator.getAllUsersAsList(filename);
    ads = fileOperator.getAllAdsInFile(filename);
    jsonFile = fileOperator.getJsonFileAsObject(filename);
  }

  public List<User> getUsers() {
    this.users = new FileOperator().getJsonFileAsObject(filename).getUsers();
    return new ArrayList<User>(this.users);
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return this.filename;
  }

  public void addUser(User user) {
    // this.users.add(user.generateUser());
    // autoSave(user);
    new FileOperator().writeNewUserDataToFile(filename, user);
    this.users = fileOperator.getAllUsersAsList(filename);
  }
  
  public List<Ad> getActiveAds() {
    this.ads = fileOperator.getAllAdsInFile(this.filename);
    return new AdSorter(this.ads).sortAds(ad -> ad.getIsSold() == false);
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
