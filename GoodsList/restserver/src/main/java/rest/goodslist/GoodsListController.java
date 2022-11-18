package rest.goodslist;

import java.util.List;
import json.Ad;
import json.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class RestController. Sets default filename in service.
 */
@RestController
public class GoodsListController {

  GoodsListService service = new GoodsListService("..//ui/src/main/resources/ui/dataObjects.json");
  
  /**
   * http://localhost:8080/users
   * GetMapping to get all users in file.
   *
   * @return Users
   */
  @GetMapping("/users")
  public List<User> getJsonFileAsObject() {
    return service.getUsers();
  }

  /** 
   * http://localhost:8080/ads
   * GetMapping to get all active ads in file.
   *
   * @return active ads
   */
  @GetMapping("/ads")
  public List<Ad> getAds() {
    return service.getActiveAds();
  }

  /**
   * http://localhost:8080/allAds
   * GetMapping to get all ads in file, both active and sold.
   *
   * @return all ads
   */
  @GetMapping("/allAds")
  public List<Ad> getAllAds() {
    return service.getAllAds();
  }
  
  /** 
   * http://localhost:8080/newuser
   * PutMapping to register a new user in file.
   *
   * @RequestBody User user
   */
  @PutMapping("/newuser")
  public void registerNewUser(@RequestBody User user) {
    service.addUser(user);
  }

  /**
   * http://localhost:8080/newAd
   * PutMapping to upload a new ad in file.
   *
   * @RequestBody Ad ad
   */
  @PutMapping("/newAd")
  public void registerNewAd(@RequestBody Ad ad) {
    service.addAd(ad);
  }

  /** 
   * http://localhost:8080/updateUser
   * PutMapping to update a given user.
   *
   * @RequestBody User user
   */
  @PutMapping("/updateUser")
  public void updateUser(@RequestBody User user) {
    service.updateUser(user);
  }

  /** 
   * http://localhost:8080/updateAd
   * PutMapping to update a given ad if its sold. 
   *
   * @RequestBody Ad ad
   */
  @PutMapping("/updateAd")
  public void updateAd(@RequestBody Ad ad) {
    service.updateAd(ad);
  }
  
  /** 
   * http://localhost:8080/setFilename
   * PutMapping to set the filename so the RestController knows if its a test or not.
   *
   * @RequestBody String filename
   */
  @PutMapping("/setFilename")
  public void setFilename(@RequestBody String filename) {
    service.setFilename(filename);
  }
}
