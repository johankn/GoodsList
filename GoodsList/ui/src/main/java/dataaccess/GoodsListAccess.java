package dataaccess;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import json.Ad;
import json.JsonFileAsObject;
import json.User;

/**
 * Interface for the GoodsListAccess classes. Used so you can call on this for each of them. 
 */
public interface GoodsListAccess {

  public void newAd(Ad ad);

  public void newUser(User user);

  public List<Ad> getAllActiveAdsInFile();

  public List<Ad> getAllAdsInFile();

  public List<User> getAllUsers();

  public void updateUser(User user);

  public void updateAd(Ad ad);

  public void initializeFile(JsonFileAsObject jsonFileAsObject);
}
