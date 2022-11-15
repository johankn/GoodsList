package rest.goodslist;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.RegisteredUser;
import json.Ad;
import json.DataObject;
import json.FileOperator;
import json.JsonFileAsObject;
import json.User;

//import core.DataObject;

@RestController
public class GoodsListController {

  // ./mvnw spring-boot:run

  GoodsListService service = new GoodsListService("..//ui/src/main/resources/ui/dataObjects.json");

  @GetMapping
  public JsonFileAsObject getJsonFileAsObject() {
    return service.getJsonFileAsObject();
  }

  @GetMapping("/ads")
  public List<Ad> getAds() {
    return service.getAds();
  }

  @PostMapping("/new-user")
  public void registerNewUser(@RequestBody RegisteredUser user) {
    service.addUser(user);
    
  }

    /**
   * Posts the login requests entered from client.
   *
   * @param username the username
   * @param password the password
   * @return the user if login is correct
   */

  //localhost:8080/login?email={email}&password={password}
  @PostMapping("/login")
  public User userLogin(@RequestParam("username") String username, 
      @RequestParam("password") String password) {
    if (service.userLogin(username, password)) {
      return service.getUserByUsername(username);
    }
    throw new IllegalStateException();
  }

  // @GetMapping("/{username}")
  // public String getAdsFromUser(@PathVariable("username") String username) {
  //   return service
  // }
}
