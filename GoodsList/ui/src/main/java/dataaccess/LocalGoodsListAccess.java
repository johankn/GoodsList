package dataaccess;

import java.io.IOException;
import java.util.List;

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
  public void newUser(RegisteredUser registeredUser) {
    fileOperator.writeNewUserDataToFile(filename, registeredUser);
    
  }

  @Override
  public List<Ad> getAllAdsInFile() throws IOException {
    return fileOperator.getAllAdsInFile(filename);
  }

  @Override
  public List<User> getAllUsers() {
    return fileOperator.getAllUsersAsList(filename);
  }

  @Override
  public User userLogin(User user) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
