package dataaccess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import core.RegisteredUser;
import json.Ad;
import json.DataObject;
import json.FileOperator;
import json.JsonFileAsObject;
import json.User;

public class RemoteGoodsListAccess implements GoodsListAccess {

  private FileOperator fileOperator;
  private ObjectMapper objectMapper;
  private final URI remoteURI;
  private String filename;
  private List<User> users;
  private List<Ad> ads;

  public RemoteGoodsListAccess(final URI remoteURI, String filename) {
    this.remoteURI = remoteURI;
    fileOperator = new FileOperator();
    this.filename = filename;
    objectMapper = fileOperator.getObjectMapper();
    this.users = getAllUsers();
  }

  public URI resolveURI(String path) {
    return remoteURI.resolve(path);
  }

  @Override
  public List<Ad> getAdsFromUser(String username) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void newAdToUser(Ad ad, User username) throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void newUser(RegisteredUser registeredUser) throws Exception {
    String postMappingPath = "/new-user";
    try {
      String json = objectMapper.writeValueAsString(registeredUser.generateUser());
      HttpRequest httpRequest = HttpRequest.newBuilder(resolveURI(postMappingPath))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(json))
                    .build();

      HttpClient.newBuilder()
                .build()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
    

  @Override
  public List<Ad> getAllAdsInFile() throws IOException {
    HttpRequest httpRequest = HttpRequest.newBuilder(resolveURI("/ads"))
                                                .header("Accept", "application/json")
                                                .GET()
                                                .build();
    try {
      final HttpResponse<String> httpResponse = 
                HttpClient.newBuilder()
                        .build()
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());

      JsonFileAsObject jsonObject = objectMapper.readValue(httpResponse.body()
            .substring(1, httpResponse.body().length() - 1),
            JsonFileAsObject.class);
      ads = jsonObject.getAds();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return ads;
  }

  @Override
  public List<User> getAllUsers() {
    HttpRequest httpRequest = HttpRequest.newBuilder(remoteURI)
                                                .header("Accept", "application/json")
                                                .GET()
                                                .build();
    try {
      final HttpResponse<String> httpResponse = 
                HttpClient.newBuilder()
                        .build()
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());


      // HttpEntity entity = httpResponse.getEntity();
      // String responseString = EntityUtils.toString(entity, "UTF-8");
      JsonFileAsObject jsonObject = objectMapper.readValue(httpResponse.body(), 
          JsonFileAsObject.class);
      this.users = jsonObject.getUsers();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return users;
  }

  @Override
  public User userLogin(User user) {
    String postMappingPath = "/login?";
    String key1 = "username=";
    String value1 = user.getUsername() + "&";
    String key2 = "password=";
    String value2 = user.getPassword();

    System.out.println(user.getUsername());
    System.out.println(getAllUsers());
    
    try {
      HttpRequest httpRequest = HttpRequest
            .newBuilder(resolveURI(postMappingPath + key1 + value1 + key2 + value2))
            .header("Accept", "application/json")
            .POST(BodyPublishers.ofString(user.getUsername() + "|" + user.getPassword()))
            .build();
      final HttpResponse<String> httpResponse =
          HttpClient.newBuilder()
                    .build()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      System.out.println(httpResponse.body());
      JsonFileAsObject jsonFileAsObject = objectMapper.readValue(httpResponse.body(), JsonFileAsObject.class);
      return jsonFileAsObject.getUser(user);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  
  
  public static void main(String[] args) throws JsonProcessingException {
    RegisteredUser ru = new RegisteredUser("Johan", "Halla1234", "Johan", "Halla1234");
    User user = ru.generateUser();
    ObjectMapper objectMapper = new ObjectMapper();
    System.out.println(objectMapper.writeValueAsString(user));
  }
  
}
