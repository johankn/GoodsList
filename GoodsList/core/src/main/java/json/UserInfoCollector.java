package json;

import java.util.List;

/**
 * Class for getting info about a user.
 */
public class UserInfoCollector {
  /**
   *This class find info about the user by information we allready know about it.
   */

  private FileOperator fileOperator;

  public UserInfoCollector() {
    this.fileOperator = new FileOperator();
  }

  /**
   * Gets the full name of a user by knowing its user name.
   *
   * @param filename filename
   * @param userName username
   * @return String
   */
  public String getFullNameByUsername(String filename, String userName) {
    List<User> allExistingUsers = fileOperator.getAllUsersAsList(filename);
    for (int i = 0; i < allExistingUsers.size(); i++) {
      if (userName.equals(allExistingUsers.get(i).getUsername())) {
        return allExistingUsers.get(i).getFullname();
      }
    }
    throw new NullPointerException("This user does not exist");
  }

  /**
   * getter for fileoerator-field.
   *
   * @return FileOperator
   */
  public FileOperator getFileOperator() {
    return fileOperator;
  }
}
