package dataaccess;

import core.AdSorter;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import json.Ad;
import json.FileOperator;
import json.User;

/**
 * Class for running the app local. 
 */
public class LocalGoodsListAccess implements GoodsListAccess {

  private FileOperator fileOperator;
  private String filename;

  public LocalGoodsListAccess(String filename) {
    this.filename = filename;
    this.fileOperator = new FileOperator();
  }

  
  /** 
   * Method for making newad. 
   *
   * @param ad ad
   */
  @Override
  public void newAd(Ad ad) {
    fileOperator.addAdToFile(filename, ad);
  }

  
  /** 
   * Method for writing new user to file. 
   *
   * @param user user
   */
  @Override
  public void newUser(User user) {
    fileOperator.writeNewUserDataToFile(filename, user);
  }

  
  /** 
   * method for getting all the ads in file with isSold field false (not sold).
   *
   * @return List of ads
   */
  @Override
  public List<Ad> getAllActiveAdsInFile() {
    return new AdSorter(fileOperator.getAllAdsInFile(filename))
        .sortAds(ad -> ad.getIsSold() == false);
  }

  
  /** 
   * MEthod for getting all users in file.
   *
   * @return List of users
   */
  @Override
  public List<User> getAllUsers() {
    return fileOperator.getAllUsersAsList(filename);
  }

  
  /** 
   * method for getting all ads in file.  
   *
   * @return List of ads
   */
  @Override
  public List<Ad> getAllAdsInFile() {
    return fileOperator.getAllAdsInFile(filename);
  }

  
  /** 
   * method for updating a given user in file.  
   *
   * @param user user
   */
  @Override
  public void updateUser(User user) {
    fileOperator.updateUserObjectJsonFile(filename, user);
  }

  
  /** 
   * method for updating a given ad in file.  
   *
   * @param ad ad
   */
  @Override
  public void updateAd(Ad ad) {
    fileOperator.updateAdObjectJsonFile(filename, ad);
  }
}
