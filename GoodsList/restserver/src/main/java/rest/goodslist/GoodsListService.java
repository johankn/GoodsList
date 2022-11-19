package rest.goodslist;

import core.AdSorter;
import java.util.ArrayList;
import java.util.List;
import json.Ad;
import json.FileOperator;
import json.User;

/** 
* Class service that represent the logic behind RestController.
*/
public class GoodsListService {
  
  private FileOperator fileOperator;
  private String filename;
  private List<User> users;
  private List<Ad> ads;

  /** 
   * A service class for the RestController, responsible for the logic behind the http mapping.
   */
  public GoodsListService(String filename) {
    this.fileOperator = new FileOperator();
    this.filename = filename;
    users = fileOperator.getAllUsersAsList(filename);
    ads = fileOperator.getAllAdsInFile(filename);
  }

  
  /** 
   * Method for getting all users in the json-file using FileOperator.
   *
   * @return users
   */
  public List<User> getUsers() {
    this.users = new FileOperator().getJsonFileAsObject(filename).getUsers();
    return new ArrayList<User>(this.users);
  }

  
  /** 
   * Sets the filename for the service.
   *
   * @param filename filename
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  
  /** 
   * Getter for the filename.
   *
   * @return String
   */
  public String getFilename() {
    return this.filename;
  }

  
  /** 
   * Add a new user to file using FileOperator. Updates the field user list.
   *
   * @param user user
   */
  public void addUser(User user) {
    new FileOperator().writeNewUserDataToFile(filename, user);
    this.users = fileOperator.getAllUsersAsList(filename);
  }
  
  
  /** 
   * Method for getting all ads in file that are marked isSold=false
   * using FileOperator and AdSorter.
   *
   * @return active ads
   */
  public List<Ad> getActiveAds() {
    this.ads = fileOperator.getAllAdsInFile(this.filename);
    return new AdSorter(ads).sortAds(ad -> ad.getIsSold() == false);
  }

  
  /** 
   * Add a new ad to file using FileOperator. Updates the field list of ads.
   *
   * @param ad ad
   */
  public void addAd(Ad ad) {
    new FileOperator().addAdToFile(filename, ad);
    ads = fileOperator.getAllAdsInFile(filename);
  }

  
  /** 
   * Getter for all ads. Updates the field first using FileOperator.
   *
   * @return all ads
   */
  public List<Ad> getAllAds() {
    this.ads = fileOperator.getAllAdsInFile(this.filename);
    return new ArrayList<Ad>(this.ads);
  }

  
  /** 
   * Updates the user in file. Used when user posts or buys an ad. 
   *
   * @param user user
   */
  public void updateUser(User user) {
    new FileOperator().updateUserObjectJsonFile(filename, user);
  }

  
  /** 
   * Updates the ad in file. Used when ad is sold.
   *
   * @param ad ad
   */
  public void updateAd(Ad ad) {
    new FileOperator().updateAdObjectJsonFile(filename, ad);
  }
}
