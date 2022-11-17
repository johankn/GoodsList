package rest.goodslist;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.AdSorter;
import core.RegisteredUser;
import json.Ad;
import json.DataObject;
import json.FileOperator;
import json.JsonFileAsObject;
import json.User;

//import core.DataObject;

@RestController
public class GoodsListController {

  GoodsListService service = new GoodsListService("..//ui/src/main/resources/ui/dataObjects.json");
  //"..//ui/src/main/resources/ui/dataObjects.json"
  @GetMapping("/users")
  public List<User> getJsonFileAsObject() {
    return service.getJsonFileAsObject().getUsers();
  }

  @GetMapping("/ads")
  public List<Ad> getAds() {
    return service.getActiveAds();
  }

  @GetMapping("/allAds")
  public List<Ad> getAllAds() {
    return service.getAllAds();
  }

  @PutMapping("/newuser")
  public void registerNewUser(@RequestBody User user) {
    service.addUser(user);
  }

  @PutMapping("/newAd")
  public void registerNewAd(@RequestBody Ad ad) {
    service.addAd(ad);
  }

  @PutMapping("/updateUser")
  public void updateUser(@RequestBody User user) {
    service.updateUser(user);
  }

  @PutMapping("/updateAd")
  public void updateAd(@RequestBody Ad ad) {
    service.updateAd(ad);
  }

}
