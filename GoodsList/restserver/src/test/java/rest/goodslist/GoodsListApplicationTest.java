package rest.goodslist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import json.Ad;
import json.Clothing;
import json.Electronics;
import json.FileOperator;
import json.User;
import json.Vehicles;


@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = { GoodsListController.class, 
    GoodsListService.class, GoodsListApplication.class })
@TestMethodOrder(OrderAnnotation.class)
@WebMvcTest
public class GoodsListApplicationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GoodsListService service;

  private ObjectMapper objectMapper;

  private User user1 = new User("bruker1","Password123","Kjell",new ArrayList<>(),
      new ArrayList<>());

  private Vehicles product1 = new Vehicles(30000, "Used", "Volvo", "sedan", 2000, "Brown");
  private Ad ad1 = new Ad("Volvo family car", product1, "2022-11-16", "Well used family car", 
        "1", false);


  @Test
  public void contextLoads() throws Exception {
    assertNotNull(service);
  }

  @BeforeAll
  public void setup() throws Exception {

    String json = "..//ui/src/test/resources/ui/uiTest.json";
  
    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("setFilename"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    objectMapper = new ObjectMapper();
    service = new GoodsListService("..//ui/src/test/resources/ui/uiTest.json");

    // final User user2 = new User("bruker2","Password123","Mari",new ArrayList<>(),
    //     new ArrayList<>());

    final Vehicles product2 = new Vehicles(55000, "New", "Mercedes", "c-class", 2010, "Blue");
    final Ad ad2 = new Ad("Mercedes sports car", product2, "2022-11-16", "New sportscar", 
        "2", true);

    this.service.addUser(user1);
    this.service.addAd(ad1);
    this.service.addAd(ad2);
    // this.service.addUser(user2);

  }

  // @BeforeEach
  // public void setUserStandard() {
  //   this.user1 = new User("bruker1","Password123","Kjell",new ArrayList<>(),
  //   new ArrayList<>());
  // }

  private String getUrl(String... segments) {
    String url = "http://localhost:8080/";
    for (String segment : segments) {
      url = url + segment;
    }
    return url;
  }

  @Order(1)
  @Test
  public void testGetAllUsers() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(getUrl("users"))
           .accept(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andReturn();

    String actualString = mockMvc.perform(MockMvcRequestBuilders.get(getUrl("users"))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();
    String expectedString = 
        "[{\"username\":\"bruker1\",\"password\":\"Password123\","
        + "\"fullname\":\"Kjell\",\"myAds\":[],\"boughtAds\":[]}]";
    Assertions.assertEquals(expectedString, actualString);
  }

  @Order(2)
  @Test
  public void testGetAllActiveAds() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(getUrl("ads"))
           .accept(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andReturn();

    String actualString = mockMvc.perform(MockMvcRequestBuilders.get(getUrl("ads"))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    String expectedString = 
        "[{\"adTitle\":\"Volvo family car\",\"product\":{\"@type\":\"vehicles\"," 
        + "\"price\":30000,\"condition\":\"Used\",\"brand\":\"Volvo\",\"modelName\":\"sedan\"," 
        + "\"modelYear\":2000,\"color\":\"Brown\"},\"date\":\"2022-11-16\",\"description\":" 
        + "\"Well used family car\",\"adId\":\"1\",\"isSold\":false}]";
    Assertions.assertEquals(expectedString, actualString);
  }

  @Order(3)
  @Test
  public void testGetAllAds() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(getUrl("allAds"))
           .accept(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andReturn();

    String actualString = mockMvc.perform(MockMvcRequestBuilders.get(getUrl("allAds"))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    String expectedString =
        "[{\"adTitle\":\"Volvo family car\",\"product\":{\"@type\":\"vehicles\"," 
        + "\"price\":30000,\"condition\":\"Used\",\"brand\":\"Volvo\",\"modelName\":\"sedan\"," 
        + "\"modelYear\":2000,\"color\":\"Brown\"},\"date\":\"2022-11-16\",\"description\":" 
        + "\"Well used family car\",\"adId\":\"1\",\"isSold\":false},{\"adTitle\":" 
        + "\"Mercedes sports car\",\"product\":{\"@type\":\"vehicles\",\"price\":55000," 
        + "\"condition\":\"New\",\"brand\":\"Mercedes\",\"modelName\":\"c-class\"," 
        + "\"modelYear\":2010,\"color\":\"Blue\"},\"date\":\"2022-11-16\"," 
        + "\"description\":\"New sportscar\",\"adId\":\"2\",\"isSold\":true}]";
    Assertions.assertEquals(expectedString, actualString);

  }

  @Order(4)
  @Test
  public void testUpdateUser() throws Exception {
    // final User user3 = new User("bruker3","Password123","Harald", new ArrayList<>(),
    //     new ArrayList<>());
    final Electronics product4 = new Electronics(1200, "new", "Samsung", "TV");
    final Ad ad4 = new Ad("Samsung tv", product4, "2022-11-16", "Nice TV", 
        "4", false);
    user1.addAdToList(ad4.getAdId());
    
    String json = objectMapper.writeValueAsString(user1);

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("updateUser"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    String actualString = mockMvc.perform(MockMvcRequestBuilders.get(getUrl("users"))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();
    String expectedString = 
        "[{\"username\":\"bruker1\",\"password\":\"Password123\","
        + "\"fullname\":\"Kjell\",\"myAds\":[\"4\"],\"boughtAds\":[]}]";
    Assertions.assertEquals(expectedString, actualString);
  }

  @Order(5)
  @Test
  public void testNewUser() throws Exception {
    
    final User user3 = new User("bruker3","Password123","Harald",new ArrayList<>(),
        new ArrayList<>());


    String json = objectMapper.writeValueAsString(user3);

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("newuser"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    String actualString = mockMvc.perform(MockMvcRequestBuilders.get(getUrl("users"))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();
    try {
      List<String> listUsers = Arrays.asList(actualString.split("},"));
      Assertions.assertEquals(2, listUsers.size());
    } catch (Exception e) {
      fail("Couldnt split, so there are less than 2 users, or its the wrong format");
    }
  }

  @Order(6)
  @Test
  public void testUpdateAd() throws Exception {
    ad1.setIsSold(true);

    String json = objectMapper.writeValueAsString(ad1);

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("updateAd"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    String actualString = mockMvc.perform(MockMvcRequestBuilders.get(getUrl("allAds"))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    String expectedString =
        "[{\"adTitle\":\"Volvo family car\",\"product\":{\"@type\":\"vehicles\"," 
        + "\"price\":30000,\"condition\":\"Used\",\"brand\":\"Volvo\",\"modelName\":\"sedan\"," 
        + "\"modelYear\":2000,\"color\":\"Brown\"},\"date\":\"2022-11-16\",\"description\":" 
        + "\"Well used family car\",\"adId\":\"1\",\"isSold\":true},{\"adTitle\":" 
        + "\"Mercedes sports car\",\"product\":{\"@type\":\"vehicles\",\"price\":55000," 
        + "\"condition\":\"New\",\"brand\":\"Mercedes\",\"modelName\":\"c-class\"," 
        + "\"modelYear\":2010,\"color\":\"Blue\"},\"date\":\"2022-11-16\"," 
        + "\"description\":\"New sportscar\",\"adId\":\"2\",\"isSold\":true}]";

    assertEquals(expectedString, actualString);
  }
  

  @Order(7)
  @Test
  public void testNewAd() throws Exception {
    final Electronics product3 = new Electronics(1200, "New", "Apple", "Airpods");
    final Ad ad3 = new Ad("New airpods!", product3, "2022-11-16", "Works fine", 
        "3", false);
    // this.user1.addAdToList(ad1.getAdId());
    // this.service.updateUser(user1);
    ad1.setIsSold(true);

    String jsonAd1 = objectMapper.writeValueAsString(ad1);

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("updateAd"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(jsonAd1).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    String json = objectMapper.writeValueAsString(ad3);

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("newAd"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    
    String actualString = mockMvc.perform(MockMvcRequestBuilders.get(getUrl("ads"))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    String expectedString =
        "[{\"adTitle\":" 
        + "\"New airpods!\",\"product\":{\"@type\":\"electronics\",\"price\":1200," 
        + "\"condition\":\"New\",\"brand\":\"Apple\",\"type\":\"Airpods\"}," 
        + "\"date\":\"2022-11-16\"," 
        + "\"description\":\"Works fine\",\"adId\":\"3\",\"isSold\":false}]";
    
    assertEquals(expectedString, actualString);
    
  }

  @Order(8)
  @Test
  public void testSetFilename() throws Exception {
    String filename = "..//ui/src/test/resources/ui/uiTest.json";

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("setFilename"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(filename).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    
    assertEquals(filename, this.service.getFilename());
  }

  /*
   * clears all users from testfile
   */
  @AfterAll
  public void clearTestFile() {
    FileOperator fileOperator = new FileOperator();
    fileOperator.removeAllDataFromFile("..//ui/src/test/resources/ui/uiTest.json");
  }

}
