package dataaccess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import core.AdSorter;
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
    this.users = new ArrayList<>();
    this.ads = new ArrayList<>();
  }

  public URI resolveURI(String path) {
    return remoteURI.resolve(path);
  }

  @Override
  public List<Ad> getAdsFromUser(User username) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void newAd(Ad ad) {
    String putMappingPath = "/newAd";
    try {
      if (ad == null) {
        throw new NullPointerException("ad cannot be null");
      }
      String json = objectMapper.writeValueAsString(ad);
      HttpRequest httpRequest = HttpRequest.newBuilder(resolveURI(putMappingPath))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .PUT(BodyPublishers.ofString(json))
                    .build();

      HttpClient.newBuilder()
                .build()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    
  }

  @Override
  public void newUser(User user) {
    String postMappingPath = "/newuser";
    try {
      if (user == null) {
        throw new NullPointerException("User cannot be null");
      }
      String json = objectMapper.writeValueAsString(user);
      HttpRequest httpRequest = HttpRequest.newBuilder(resolveURI(postMappingPath))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .PUT(BodyPublishers.ofString(json))
                    .build();
      final HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
    

  @Override
  public List<Ad> getAllActiveAdsInFile() {
    HttpRequest httpRequest = HttpRequest.newBuilder(resolveURI("/ads"))
                                                .header("Accept", "application/json")
                                                .GET()
                                                .build();
    try {
      final HttpResponse<String> httpResponse = 
                HttpClient.newBuilder()
                        .build()
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      // JsonFileAsObject jsonObject = objectMapper.readValue(httpResponse.body()
      //       .substring(1, httpResponse.body().length() - 1),
      //       JsonFileAsObject.class);
      ads = objectMapper.readValue(httpResponse.body(), new TypeReference<List<Ad>>() {});
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new AdSorter(this.ads).sortAds(ad -> ad.getIsSold() == false);
  }

  @Override
  public List<User> getAllUsers() {
    HttpRequest httpRequest = HttpRequest.newBuilder(resolveURI("/users"))
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
      this.users = objectMapper.readValue(httpResponse.body(), new TypeReference<List<User>>() {});
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return users;
  }


  @Override
  public List<Ad> getAllActiveAdsWithPredicate(Predicate<Ad> expression) throws IOException {
    ads = this.getAllActiveAdsInFile();
    return new AdSorter(ads).sortAds(expression);
  }

  @Override
  public List<Ad> getAllAdsInFile() {
    HttpRequest httpRequest = HttpRequest.newBuilder(resolveURI("/allAds"))
                                                .header("Accept", "application/json")
                                                .GET()
                                                .build();
    try {
      final HttpResponse<String> httpResponse = 
                HttpClient.newBuilder()
                        .build()
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      // JsonFileAsObject jsonObject = objectMapper.readValue(httpResponse.body()
      //       .substring(1, httpResponse.body().length() - 1),
      //       JsonFileAsObject.class);
      ads = objectMapper.readValue(httpResponse.body(), new TypeReference<List<Ad>>() {});
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return ads;
  }

  @Override
  public void updateUser(User user) {
    String postMappingPath = "/updateUser";
    try {
      if (user == null) {
        throw new NullPointerException("user cannot be null");
      }
      String json = objectMapper.writeValueAsString(user);
      HttpRequest httpRequest = HttpRequest.newBuilder(resolveURI(postMappingPath))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .PUT(BodyPublishers.ofString(json))
                    .build();

      HttpClient.newBuilder()
                .build()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void updateAd(Ad ad) {
    String postMappingPath = "/updateAd";
    try {
      if (ad == null) {
        throw new NullPointerException("Ad cannot be null");
      }
      String json = objectMapper.writeValueAsString(ad);
      HttpRequest httpRequest = HttpRequest.newBuilder(resolveURI(postMappingPath))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .PUT(BodyPublishers.ofString(json))
                    .build();

      HttpClient.newBuilder()
                .build()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    
  }
  
}
