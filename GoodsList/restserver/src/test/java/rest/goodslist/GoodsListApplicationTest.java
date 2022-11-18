package rest.goodslist;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import json.Ad;
import json.Electronics;
import json.FileOperator;
import json.User;
import json.Vehicles;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
  @DisplayName("Test that service is not null.")
  public void contextLoads() throws Exception {
    assertNotNull(service);
  }

  /** 
   * BeforeAll test is run we add user1, ad1 and ad2 to file. 
   * The filename for testfile is set in the RestController and service.
   * User1 and ad1 is private fields outside this method so they can be edited when checking
   * updateUser and updateAd.
   *
   */
  @BeforeAll
  public void setup() throws Exception {

    String json = "..//ui/src/test/resources/ui/uiTest.json";
  
    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("setFilename"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

    objectMapper = new ObjectMapper();
    service = new GoodsListService("..//ui/src/test/resources/ui/uiTest.json");

    final Vehicles product2 = new Vehicles(55000, "New", "Mercedes", "c-class", 2010, "Blue");
    final Ad ad2 = new Ad("Mercedes sports car", product2, "2022-11-16", "New sportscar", 
        "2", true);

    this.service.addUser(user1);
    this.service.addAd(ad1);
    this.service.addAd(ad2);
  }

  /** 
   * Private method for getting the url.
   *
   * @param segments string segments
   * @return String
   */
  private String getUrl(String... segments) {
    String url = "http://localhost:8080/";
    for (String segment : segments) {
      url = url + segment;
    }
    return url;
  }

  /** 
   * First test if http resonse is ok, then checks assertEquals the actual output with expected.
   */
  @Order(1)
  @Test
  @DisplayName("Test for get all users in file.")
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

  /** 
   * First test if http resonse is ok, then checks assertEquals the actual output with expected.
   */
  @Order(2)
  @Test
  @DisplayName("Test for getting all active ads in file.")
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

  /** 
   * First test if http resonse is ok, then checks assertEquals the actual output with expected.
   */
  @Order(3)
  @Test
  @DisplayName("Test for get all ads in file, active and sold.")
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

  /** 
   * First test if http resonse is ok, then checks assertEquals the actual output with expected.
   */
  @Order(4)
  @Test
  @DisplayName("Test for updating the user after posting an ad.")
  public void testUpdateUser() throws Exception {
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

  /** 
   * First test if http resonse is ok, then checks assertEquals the actual output with expected.
   */
  @Order(5)
  @Test
  @DisplayName("test for checking if new user is added to file.")
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

  /** 
   * First test if http resonse is ok, then checks assertEquals the actual output with expected.
   */
  @Order(6)
  @Test
  @DisplayName("Test for updating ad to the file when marked as sold.")
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

    Assertions.assertEquals(expectedString, actualString);
  }
  
  /** 
   * First test if http resonse is ok, then checks assertEquals the actual output with expected.
   */
  @Order(7)
  @Test
  @DisplayName("Test for posting a new ad to file.")
  public void testNewAd() throws Exception {
    final Electronics product3 = new Electronics(1200, "New", "Apple", "Airpods");
    final Ad ad3 = new Ad("New airpods!", product3, "2022-11-16", "Works fine", 
        "3", false);

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
    
    Assertions.assertEquals(expectedString, actualString);
    
  }

  /** 
   * First test if http resonse is ok, then checks assertEquals the actual output with expected.
   */
  @Order(8)
  @Test
  @DisplayName("Test if filename set in RestController is the same as in service.")
  public void testSetFilename() throws Exception {
    String filename = "..//ui/src/test/resources/ui/uiTest.json";

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("setFilename"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(filename).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    
    Assertions.assertEquals(filename, this.service.getFilename());
  }

  /*
   * Clears all users from testfile after tests is run.
   *
   */
  @AfterAll
  public void clearTestFile() {
    FileOperator fileOperator = new FileOperator();
    fileOperator.removeAllDataFromFile("..//ui/src/test/resources/ui/uiTest.json");
  }

}
