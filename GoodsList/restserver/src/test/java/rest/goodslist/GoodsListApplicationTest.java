package rest.goodslist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
@WebMvcTest
public class GoodsListApplicationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GoodsListService service;

  private ObjectMapper objectMapper;


  @Test
  public void contextLoads() throws Exception {
    assertNotNull(service);
  }

  @BeforeAll
  public void setup() {
    objectMapper = new ObjectMapper();
    service = new GoodsListService("..//ui/src/test/resources/ui/uiTest.json");

    final User user1 = new User("bruker1","Password123","Kjell",new ArrayList<>(),
        new ArrayList<>());
    final User user2 = new User("bruker2","Password123","Mari",new ArrayList<>(),
        new ArrayList<>());
    final Vehicles product1 = new Vehicles(30000, "Used", "Volvo", "sedan", 2000, "Brown");
    final Ad ad1 = new Ad("Volvo family car", product1, "2022-11-16", "Well used family car", 
        "1", false);

    this.service.addUser(user1);
    this.service.addUser(user2);


  }

  private String getUrl(String... segments) {
    String url = "http://localhost:8080/";
    for (String segment : segments) {
      url = url + segment;
    }
    return url;
  }

  @Test
  public void testGetAllUsers() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(getUrl("users"))
           .accept(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andReturn();
  }

  @Test
  public void testGetAllActiveAds() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(getUrl("ads"))
           .accept(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andReturn();
  }

  @Test
  public void testGetAllAds() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(getUrl("allAds"))
           .accept(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andReturn();
  }

  @Test
  public void newUser() throws Exception {
    final User user3 = new User("bruker3","Password123","Harald",new ArrayList<>(),
        new ArrayList<>());

    this.service.addUser(user3);

    String json = objectMapper.writeValueAsString(user3);

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("newuser"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void newAd() throws Exception {
    final Electronics product1 = new Electronics(1200, "new", "Apple", "Airpods");
    final Ad ad1 = new Ad("New airpods!", product1, "2022-11-16", "Works fine description", 
        "2", false);
    // this.user1.addAdToList(ad1.getAdId());
    // this.service.updateUser(user1);
    this.service.addAd(ad1);

    String json = objectMapper.writeValueAsString(ad1);

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("newAd"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void updateUser() throws Exception {
    final User user3 = new User("bruker3","Password123","Harald", new ArrayList<>(),
        new ArrayList<>());
    final Electronics product1 = new Electronics(1200, "new", "Apple", "Airpods");
    final Ad ad1 = new Ad("New airpods!", product1, "2022-11-16", "Works fine description", 
        "2", false);
    user3.addAdToList(ad1.getAdId());
    this.service.updateUser(user3);
    String json = objectMapper.writeValueAsString(user3);

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("updateUser"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void updateAd() throws Exception {
    // System.out.println(this.ad1.getAdId());
    // this.user1.buyAd(this.ad1.getAdId());
    // this.service.updateUser(user1);
    // this.service.updateAd(this.ad1);
    final Electronics product1 = new Electronics(1200, "new", "Apple", "Airpods");
    final Ad ad1 = new Ad("New airpods!", product1, "2022-11-16", "Works fine description", 
        "2", true);

    String json = objectMapper.writeValueAsString(ad1);

    mockMvc.perform(MockMvcRequestBuilders.put(getUrl("updateAd"))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(json).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
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
