package dataaccess;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import core.AdSorter;
import core.RegisteredUser;
import json.Ad;
import json.FileOperator;
import json.User;

public class LocalGoodsListAccess implements GoodsListAccess {

  private FileOperator fileOperator;
  private String filename;

  public LocalGoodsListAccess(String filename) {
    this.filename = filename;
    this.fileOperator = new FileOperator();
  }

  @Override
  public List<Ad> getAdsFromUser(String username) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void newAdToUser(Ad ad, User user) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void newUser(User user) {
    fileOperator.writeNewUserDataToFile(filename, user);
    
  }

  @Override
  public List<Ad> getAllActiveAdsInFile() throws IOException {
    return new AdSorter(fileOperator.getAllAdsInFile(filename)).sortAds(ad -> ad.getIsSold() == false);
  }

  @Override
  public List<User> getAllUsers() {
    return fileOperator.getAllUsersAsList(filename);
  }


  @Override
  public List<Ad> getAllActiveAdsWithPredicate(Predicate expression) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }
  
}
