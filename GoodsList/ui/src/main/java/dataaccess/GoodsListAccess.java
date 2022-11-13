package dataaccess;

import java.io.IOException;
import java.util.List;

import core.RegisteredUser;
import json.Ad;
import json.User;

public interface GoodsListAccess {
  
  public List<Ad> getAdsFromUser(String username) throws IOException;
      
  public void newAdToUser(Ad ad, User user) throws IOException;

  public void newUser(String filename, RegisteredUser registeredUser);

  public List<Ad> getAllAdsInFile(String filename) throws IOException;

  public List<User> getAllUsers(String filename);
}
