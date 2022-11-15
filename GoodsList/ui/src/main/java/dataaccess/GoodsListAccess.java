package dataaccess;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import core.RegisteredUser;
import json.Ad;
import json.User;

public interface GoodsListAccess {
  
  public List<Ad> getAdsFromUser(User username) throws IOException;
      
  public void newAd(Ad ad) throws IOException;

  public void newUser(User user) throws Exception;

  public List<Ad> getAllActiveAdsInFile();

  public List<Ad> getAllAdsInFile();

  public List<Ad> getAllActiveAdsWithPredicate(Predicate<Ad> expression) throws IOException;

  public List<User> getAllUsers();
}
