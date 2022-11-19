package dataaccess;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.AdSorter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import json.Ad;
import json.FileOperator;
import json.JsonFileAsObject;
import json.User;

/**
 * Remote version of GoodsListAccess, when app is running via rest-api. 
 */
public class RemoteGoodsListAccess implements GoodsListAccess {

  private FileOperator fileOperator;
  private ObjectMapper objectMapper;
  private final URI remoteUri;
  private List<User> users;
  private List<Ad> ads;

  /**
   * Constructor.  
   *
   * @param remoteUri uri
   */
  public RemoteGoodsListAccess(final URI remoteUri) {
    this.remoteUri = remoteUri;
    fileOperator = new FileOperator();
    objectMapper = fileOperator.getObjectMapper();
    this.users = new ArrayList<>();
    this.ads = new ArrayList<>();
  }

  
  /** 
   * method for resolving urI from a path.  
   *
   * @param path path
   * @return URI
   */
  public URI resolveUri(String path) {
    return remoteUri.resolve(path);
  }

  
  /** 
   * Method for making newad. 
   *
   * @param ad ad
   */
  @Override
  public void newAd(Ad ad) {
    String putMappingPath = "/newAd";
    try {
      if (ad == null) {
        throw new NullPointerException("ad cannot be null");
      }
      String json = objectMapper.writeValueAsString(ad);
      HttpRequest httpRequest =
          HttpRequest.newBuilder(resolveUri(putMappingPath))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(BodyPublishers.ofString(json))
              .build();

      HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  
  /** 
   * Method for writing new user to file. 
   *
   * @param user user
   */
  @Override
  public void newUser(User user) {
    String postMappingPath = "/newuser";
    try {
      if (user == null) {
        throw new NullPointerException("User cannot be null");
      }
      String json = objectMapper.writeValueAsString(user);
      HttpRequest httpRequest =
          HttpRequest.newBuilder(resolveUri(postMappingPath))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(BodyPublishers.ofString(json))
              .build();
      HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  
  /** 
   * method for getting all the ads in file with isSold field false (not sold).
   *
   * @return List of ads
   */
  @Override
  public List<Ad> getAllActiveAdsInFile() {
    HttpRequest httpRequest =
        HttpRequest.newBuilder(resolveUri("/ads"))
            .header("Accept", "application/json")
            .GET()
            .build();
    try {
      final HttpResponse<String> httpResponse =
          HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
      // JsonFileAsObject jsonObject = objectMapper.readValue(httpResponse.body()
      //       .substring(1, httpResponse.body().length() - 1),
      //       JsonFileAsObject.class);
      ads = objectMapper.readValue(httpResponse.body(), new TypeReference<List<Ad>>() {});
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new AdSorter(this.ads).sortAds(ad -> ad.getIsSold() == false);
  }

  
  /** 
   * Method for getting ads with a certain predicate. 
   *
   * @return List of ads
   */
  @Override
  public List<User> getAllUsers() {
    HttpRequest httpRequest =
        HttpRequest.newBuilder(resolveUri("/users"))
            .header("Accept", "application/json")
            .GET()
            .build();
    try {
      final HttpResponse<String> httpResponse =
          HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());

      // HttpEntity entity = httpResponse.getEntity();
      // String responseString = EntityUtils.toString(entity, "UTF-8");
      this.users = objectMapper.readValue(httpResponse.body(), new TypeReference<List<User>>() {});
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return users;
  }

  
  /** 
   * method for getting all ads in file.  
   *
   * @return List of ads
   */
  @Override
  public List<Ad> getAllAdsInFile() {
    HttpRequest httpRequest =
        HttpRequest.newBuilder(resolveUri("/allAds"))
            .header("Accept", "application/json")
            .GET()
            .build();
    try {
      final HttpResponse<String> httpResponse =
          HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
      // JsonFileAsObject jsonObject = objectMapper.readValue(httpResponse.body()
      //       .substring(1, httpResponse.body().length() - 1),
      //       JsonFileAsObject.class);
      ads = objectMapper.readValue(httpResponse.body(), new TypeReference<List<Ad>>() {});
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return ads;
  }

  
  /** 
   * method for updating a given user in file.  
   *
   * @param user user
   */
  @Override
  public void updateUser(User user) {
    String postMappingPath = "/updateUser";
    try {
      if (user == null) {
        throw new NullPointerException("user cannot be null");
      }
      String json = objectMapper.writeValueAsString(user);
      HttpRequest httpRequest =
          HttpRequest.newBuilder(resolveUri(postMappingPath))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(BodyPublishers.ofString(json))
              .build();

      HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  
  /** 
   * method for updating a given ad in file.  
   *
   * @param ad ad
   */
  @Override
  public void updateAd(Ad ad) {
    String postMappingPath = "/updateAd";
    try {
      if (ad == null) {
        throw new NullPointerException("Ad cannot be null");
      }
      String json = objectMapper.writeValueAsString(ad);
      HttpRequest httpRequest =
          HttpRequest.newBuilder(resolveUri(postMappingPath))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(BodyPublishers.ofString(json))
              .build();

      HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void initializeFile(JsonFileAsObject jsonFileAsObject) {
    System.out.println(jsonFileAsObject.getUsers());
    String postMappingPath = "/initialize";
    try {
      String json = objectMapper.writeValueAsString(jsonFileAsObject);
      HttpRequest httpRequest =
          HttpRequest.newBuilder(resolveUri(postMappingPath))
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .PUT(BodyPublishers.ofString(json))
              .build();

      HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
