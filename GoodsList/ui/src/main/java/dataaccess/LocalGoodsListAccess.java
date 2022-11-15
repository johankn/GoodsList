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
  public List<Ad> getAdsFromUser(User username) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void newAd(Ad ad) throws IOException {
    fileOperator.addAdToFile(filename, ad);
    
  }

  @Override
  public void newUser(User user) {
    fileOperator.writeNewUserDataToFile(filename, user);
    
  }

  @Override
  public List<Ad> getAllActiveAdsInFile() {
    return new AdSorter(fileOperator.getAllAdsInFile(filename)).sortAds(ad -> ad.getIsSold() == false);
  }

  @Override
  public List<User> getAllUsers() {
    return fileOperator.getAllUsersAsList(filename);
  }


  @Override
  public List<Ad> getAllActiveAdsWithPredicate(Predicate<Ad> expression) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Ad> getAllAdsInFile() {
    // TODO Auto-generated method stub
    return fileOperator.getAllAdsInFile(filename);
  }
  
}
