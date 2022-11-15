package dataaccess;

import java.io.IOException;
import java.util.List;

import core.RegisteredUser;
import json.Ad;
import json.User;

public interface GoodsListAccess {
  
  public List<Ad> getAdsFromUser(String username) throws IOException;
      
  public void newAdToUser(Ad ad, User username) throws IOException;

  public void newUser(RegisteredUser registeredUser) throws Exception;

  public List<Ad> getAllAdsInFile() throws IOException;

  public List<User> getAllUsers();

  public User userLogin(User user);
}
