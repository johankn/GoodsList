package dataaccess;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import json.Ad;
import json.Electronics;
import json.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RemoteAccessTest {

  private static WireMockServer wireMockServer;
  private GoodsListAccess remoteGoodsListAccess;
  private WireMockConfiguration wireMockConfiguration;
  private String multipleAds;
  private String userlist;
  private User user;
  private String stringuser;
  private String updatedstringuser;
  private Ad ad;
  private String stringad;
  private String updatedstringad;

  /**
   * Method for setting up necessary stuff before each test. 
   */
  @BeforeEach
  public void setUp() throws URISyntaxException {
    this.wireMockConfiguration = WireMockConfiguration.wireMockConfig().port(8080);
    wireMockServer = new WireMockServer(wireMockConfiguration.portNumber());
    wireMockServer.start();
    WireMock.configureFor("localhost", wireMockConfiguration.portNumber());
    this.remoteGoodsListAccess = new RemoteGoodsListAccess(
      new URI("http://localhost:" + wireMockServer.port()), 
      "ui/src/test/resources/dataaccess/RemoteAccessTest.json");
    this.multipleAds = 
        "[ {\"adTitle\": \"Apple Iphone\", \"product\":{ \"@type\": \"electronics\", "
        + "\"price\": 6969, \"condition\": \"new\", \"brand\": \"Apple\", \"type\": "
        + "\"Iphone\"}, \"date\": \"2022-11-12\", \"description\": \"Very good phone\","
        + " \"adId\": \"1\", \"isSold\": true}, {\"adTitle\": \"Samsung phone\","
        + " \"product\":{ \"@type\": \"electronics\", \"price\": 42, \"condition\": \"new\","
        + " \"brand\": \"Samsung\", \"type\": \"phone\"}, \"date\": \"2022-11-12\", "
        + "\"description\": \"Very bad phone\", \"adId\": \"2\", \"isSold\": false}]";
    this.userlist = 
        "[ {\"username\": \"johangutt\", \"password\": \"stavanger\", \"fullname\": \"Johakn\", "
        + "\"myAds\": [], \"boughtAds\": []} ]";
    this.user = 
        new User("johangutt", "stavanger", "Johakn", new ArrayList<>(), new ArrayList<>());
    this.stringuser = 
        "{\"username\": \"johangutt\", \"password\": \"stavanger\", \"fullname\": \"Johakn\", "
        + "\"myAds\": [], \"boughtAds\": []}";
    this.updatedstringuser = 
        "{\"username\": \"johanmann\", \"password\": \"stavanger\", \"fullname\": \"Johakn\", "
        + "\"myAds\": [], \"boughtAds\": []}";
    this.ad = 
        new Ad("test", new Electronics(10, "used", "brand", "type"), 
        "17.11.2022", "description", "1", false);
    this.stringad = "{\"adTitle\": \"Apple Iphone\", \"product\":{ \"@type\": \"electronics\", "
        + "\"price\": 6969, \"condition\": \"new\", \"brand\": \"Apple\", \"type\": \"Iphone\"}";
    this.updatedstringad = 
        "{\"adTitle\": \"Apple Ipod\", \"product\":{ \"@type\": \"electronics\", "
        + "\"price\": 6969, \"condition\": \"new\", \"brand\": \"Apple\", \"type\": \"Iphone\"}";
  }

  @AfterEach
  public void shutDown() {
    wireMockServer.stop();
  }
  
  @Test
  @DisplayName("Method for testing that themockserver is running")
  public void testWireMock() {
    assertTrue(wireMockServer.isRunning());
  }
  
  
  @Test
  @DisplayName("Method for testing the getUsers requests and response. Using stubfor")
  public void testGetUsers() {
    assertThrows(RuntimeException.class, () -> {
      this.remoteGoodsListAccess.getAllUsers();
    });
    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/users"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(this.userlist)
    ));
    assertEquals(1, this.remoteGoodsListAccess.getAllUsers().size());
    assertTrue(this.remoteGoodsListAccess.getAllUsers().get(0).getUsername().equals("johangutt"));;
  }

  @Test
  @DisplayName("Method for testing the getActiveAds requests and response. Using stubfor")
  public void testGetActiveAds() {
    assertThrows(RuntimeException.class, () -> {
      this.remoteGoodsListAccess.getAllActiveAdsInFile();
    });
    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/ads"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(this.multipleAds)
    ));
    assertEquals(1, this.remoteGoodsListAccess.getAllActiveAdsInFile().size());
    assertTrue(this.remoteGoodsListAccess.getAllActiveAdsInFile().get(0).getAdTitle()
        .equals("Samsung phone"));
  }

  @Test
  @DisplayName("Method for testing the getAllAds requests and response. Using stubfor")
  public void testGetAllAds() {
    assertThrows(RuntimeException.class, () -> {
      this.remoteGoodsListAccess.getAllAdsInFile();
    });
    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/allAds"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(this.multipleAds)    
    ));
    assertEquals(2, this.remoteGoodsListAccess.getAllAdsInFile().size());
    assertTrue(this.remoteGoodsListAccess.getAllAdsInFile().get(0).getAdTitle()
        .equals("Apple Iphone"));
  }

  @Test
  @DisplayName("Method for testing the addUser requests and response. Using stubfor")
  public void testAddUser() {
    WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/newuser"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(this.stringuser)
    ));
    this.remoteGoodsListAccess.newUser(user);
    assertThrows(RuntimeException.class, () -> {
      this.remoteGoodsListAccess.newUser(null);
    });
  }

  @Test
  @DisplayName("Method for testing the addAd requests and response. Using stubfor")
  public void testAddAd() {
    StubMapping stub = WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/newad"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(this.stringad)
            ));
    assertEquals(200, stub.getResponse().getStatus()); //should be 200 when everything is succesfull
    assertEquals(this.stringad, stub.getResponse().getBody());
    this.remoteGoodsListAccess.newAd(ad);
    assertThrows(RuntimeException.class, () -> {
      this.remoteGoodsListAccess.newAd(null);
    });
  }

  @Test
  @DisplayName("Method for testing the updateUser requests and response. Using stubfor")
  public void testUpdateUser() {
    StubMapping initalStub = WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/newuser"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(this.userlist)
    ));
    this.remoteGoodsListAccess.newUser(user);
    StubMapping updateStub = WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/updateUser"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(this.updatedstringuser)
    ));
    assertNotEquals(initalStub.getRequest(), updateStub.getRequest());
    this.user.setUsername("johannmann");
    this.remoteGoodsListAccess.updateUser(user);
    assertEquals(200, updateStub.getResponse().getStatus());
    assertEquals(this.updatedstringuser, updateStub.getResponse().getBody());
    assertThrows(RuntimeException.class, () -> {
      this.remoteGoodsListAccess.updateUser(null);
    });
  }

  @Test
  @DisplayName("Method for testing the updateAd requests and response. Using stubfor")
  public void testUpdateAd() {
    StubMapping initalStub = WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/newad"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(this.stringad)
    ));
    this.remoteGoodsListAccess.newUser(user);
    StubMapping updateStub = WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/updateAd"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(this.updatedstringad)
    ));
    assertNotEquals(initalStub.getRequest(), updateStub.getRequest());
    this.ad.setAdTitle("Apple Ipod");
    this.remoteGoodsListAccess.updateAd(ad);
    assertEquals(200, updateStub.getResponse().getStatus());
    assertEquals(this.updatedstringad, updateStub.getResponse().getBody());
    assertThrows(RuntimeException.class, () -> {
      this.remoteGoodsListAccess.updateAd(null);
    });
  }


}
