package dataaccess;

import java.io.IOException;
import java.util.List;

import core.RegisteredUser;
import json.Ad;
import json.FileOperator;
import json.User;

public class LocalGoodsListAccess implements GoodsListAccess {

  private FileOperator fileOperator;

  public LocalGoodsListAccess() {
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
  public void newUser(String filename, RegisteredUser registeredUser) {
    fileOperator.writeNewUserDataToFile(filename, registeredUser);
    
  }

  @Override
  public List<Ad> getAllAdsInFile(String filename) throws IOException {
    return fileOperator.getAllAdsInFile(filename);
  }

  @Override
  public List<User> getAllUsers(String filename) {
    return fileOperator.getAllUsersAsList(filename);
  }
  
}
