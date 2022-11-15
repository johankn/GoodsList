package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import core.AdSorter;
import core.AdValidator;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import json.Ad;
import json.Books;
import json.Clothing;
import json.Electronics;
import json.FileOperator;
import json.Product;
import json.Property;
import json.User;
import json.UserInfoCollector;
import json.Vehicles;

/**
 * Controller for the app fxml file.
 */

public class AppController extends AbstractController {

  @FXML
  private CheckBox electronicsButton;
  @FXML
  private CheckBox clothingButton;
  @FXML
  private CheckBox propertyButton;
  @FXML
  private CheckBox booksButton;
  @FXML
  private CheckBox vehiclesButton;
  @FXML
  private Button newAdButton;
  @FXML
  private Button yourProfile;
  @FXML
  private Button logoutButton;
  @FXML
  private Label welcomeText;
  @FXML
  private AnchorPane homePage;
  @FXML
  private ListView<Ad> listOfAds;
  @FXML
  private TextField searchBar;

  private Ad ad;

  // which category ad is in the making
  private String filename;
  private User user;

  public void first() {
    listOfAds.getItems().clear();
    FileOperator fileoperator = new FileOperator();
    List<Ad> ads = fileoperator.getAllAdsInFile(filename);
    listOfAds
        .getItems()
        .addAll(ads.stream().filter(ad -> ad.getIsSold() == false).collect(Collectors.toList()));
  }

  /**
   * Method for setting the username after logging in, Shows welcome "fullname" in
   * display.
   *
   * @param user user
   */
  public void setUsername(User user) {
    this.user = user;
    welcomeText.setText("Welcome, " + user.getFullname());
    super.setUser(this.user);
  }

  public void setFilename(String filename) {
    this.filename = filename;
    super.setFilename(filename);
  }

  @FXML
  private void removeFilters() {
    electronicsButton.setSelected(false);
    clothingButton.setSelected(false);
    propertyButton.setSelected(false);
    vehiclesButton.setSelected(false);
    booksButton.setSelected(false);
    this.first();
  }



  /*
   * Method which is called when you press button new ad on the homepage.
   * It takes you to a selection of categories.
   */
  @FXML
  private void handleNewAd() {
    Stage stage = (Stage) newAdButton.getScene().getWindow();
    super.setScene(Controllers.CATEGORIES, stage);
  }





  /**
   * A method that makes it possible to click on an ad in the listview. When
   * clicked, the user should see a preview of the ad, and will have a choice to
   * buy the product. The user can also return to the home page.
   * 
   * @param event when the user clicks on an ad
   */
  @FXML
  private void displaySelected(MouseEvent event) {
    for (int i = 0; i < new FileOperator().getAllAdsInFile(filename).size(); i++) {
      if (this.listOfAds.getSelectionModel().isSelected(i)) {
        ad = this.listOfAds.getSelectionModel().getSelectedItem();
        super.setAd(ad);
        super.setPreviousController(this);
        Stage stage = (Stage) listOfAds.getScene().getWindow();
        super.setScene(Controllers.DISPLAYAD, stage);
      }
    }
  }

  /**
   * private method for displaying an error with the given param message Is used
   * when making an ad,
   * if something is wrong in the inout fields. It gets the message from the
   * exception that is
   * thrown The exceptions are thrown by advalidator class.
   *
   * @param message message
   */
  private void displayError(String message) {

    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("ERROR");
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Method for "checking" the checkbox, to set if something is used or new.
   *
   * @param field field
   * @return String
   */
  private String setCondition(CheckBox field) {
    if (field.isSelected()) {
      return "New";
    }
    return "Used";
    
  }

  /**
   * Method that sorts ads based on different events (which button is clicked)
   * 
   * @param event
   */
  @FXML
  private void sortAds(ActionEvent event) {
    FileOperator fileOperator = new FileOperator();
    List<Ad> ads = fileOperator.getAllAdsInFile(filename)
        .stream()
        .filter(ad -> ad.getIsSold() == false)
        .collect(Collectors.toList());
    AdSorter adsorter = new AdSorter(
        fileOperator.getAllAdsInFile(filename)
        .stream()
        .filter(ad -> ad.getIsSold() == false)
        .collect(Collectors.toList()));
    //Find checked boxes
    int size = ads.size();
    this.listOfAds.getItems().clear();

    List<CheckBox> checkBoxes = Arrays
        .asList(electronicsButton, clothingButton, vehiclesButton, propertyButton, booksButton);

    List<String> products = Arrays
        .asList("Electronics", "Clothing", "Vehicles", "Property", "Books");
    
    List<String> checkedProducts = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      if (checkBoxes.get(i).isSelected()) {
        checkedProducts.add(products.get(i));
      }
    }
    for (int i = 0; i < size; i++) {
      if (checkedProducts.size() == 0) {
        this.removeFilters();
      }
      else if (checkedProducts.contains(ads.get(i).getProduct().getClass().getSimpleName())) {
        this.listOfAds.getItems().add(ads.get(i));
      }
    }
  }

  /**
   * Method that sorts ads based on different events (which button is clicked)
   * 
   * @param event k
   */
  @FXML
  private void filterSearch() {
    FileOperator fileOperator = new FileOperator();
    AdSorter adsorter = new AdSorter(
        fileOperator.getAllAdsInFile(filename)
        .stream()
        .filter(ad -> ad.getIsSold() == false)
        .collect(Collectors.toList()));
    this.listOfAds.getItems().clear();
    this.listOfAds.getItems()
        .addAll(adsorter.sortAds(ad -> ad.getDescription()
        .toLowerCase().contains(searchBar.getText().toLowerCase()) || ad.getAdTitle()
        .toLowerCase().contains(searchBar.getText().toLowerCase())));

  }

  /*
   * When you click on your profile on homepage, you are taken to your profile page
   */
  @FXML
  private void handleYourProfile() {
    Stage stage = (Stage) yourProfile.getScene().getWindow();
    super.setScene(Controllers.PROFILE, stage);
  }
  /*
   * When you click on log out button, you should return to login page and the user should no longer be logged in.
   */
  @FXML
  private void handleLogout() {
    Stage stage = (Stage) logoutButton.getScene().getWindow();
    super.setScene(Controllers.LOGIN, stage);
  }
}