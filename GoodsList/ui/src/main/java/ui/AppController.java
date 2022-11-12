package ui;

import java.util.List;
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
import json.Property;
import json.User;
import json.UserInfoCollector;
import json.Vehicles;

/**
 * Controller for the app fxml file.
 */

public class AppController extends AbstractController {

  @FXML
  private Button electronicsButton;
  @FXML
  private Button clothingButton;
  @FXML
  private Button vehiclesButton;
  @FXML
  private Button booksButton;
  @FXML
  private Button propertyButton;
  @FXML
  private Button postButton;
  @FXML
  private Button newAdButton;
  @FXML
  private Button goBackFromAd;
  @FXML
  private Button goBackFromAd1;
  @FXML
  private Button goBackFromAd2;
  @FXML
  private Button goBackFromAd3;
  @FXML
  private Button goBackFromAd4;
  @FXML
  private Button goBack1;
  @FXML
  private Button yourProfile;
  @FXML
  private Button logoutButton;
  @FXML
  private Label welcomeText;

  @FXML
  private Label titleBuy;
  @FXML
  private Label priceBuy;
  @FXML
  private Label conditionBuy;
  @FXML
  private Label descriptionBuy;
  @FXML
  private Label label1;
  @FXML
  private Label label2;
  @FXML
  private Label label3;
  @FXML
  private Label label4;
  @FXML
  private Label label5;
  @FXML
  private Label label11;
  @FXML
  private Label label21;
  @FXML
  private Label label31;
  @FXML
  private Label label41;
  @FXML
  private Label label51;
  @FXML
  private AnchorPane homePage;
  @FXML
  private AnchorPane newAdPage;
  @FXML
  private AnchorPane adPreview;
  @FXML
  private AnchorPane categoriesPane;
  @FXML
  private AnchorPane electronicsAd;
  @FXML
  private AnchorPane clothingAd;
  @FXML
  private AnchorPane propertyAd;
  @FXML
  private AnchorPane vehiclesAd;
  @FXML
  private AnchorPane booksAd;
  @FXML
  private AnchorPane displayAd;
  @FXML
  private AnchorPane profilePage;
  @FXML
  private ListView<Ad> listOfAds;
  @FXML
  private ListView<Ad> listActiveAds;
  @FXML
  private ListView<Ad> listBoughtAds;
  @FXML
  private ListView<Ad> listSoldAds;

  private Ad ad;

  @FXML
  private Label titlePreview;
  @FXML
  private Label pricePreview;
  @FXML
  private Label conditionPreview;
  @FXML
  private Label descriptionPreview;


  @FXML
  private TextField titleField2;
  @FXML
  private TextField priceField2;
  @FXML
  private TextField brandField2;
  @FXML
  private TextField typeField2;
  @FXML
  private TextField sizeField2;
  @FXML
  private TextArea descriptionArea2;
  @FXML
  private CheckBox conditionField2;

  @FXML
  private TextField titleField4;
  @FXML
  private TextField priceField4;
  @FXML
  private TextField brandField4;
  @FXML
  private TextField typeField4;
  @FXML
  private TextField yearField4;
  @FXML
  private TextArea descriptionArea4;
  @FXML
  private CheckBox conditionField4;

  // which category ad is in the making
  private int categoryId;
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
    super.setUser(user);
  }

  public void setFilename(String filename) {
    this.filename = filename;
    super.setFilename(filename);
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

  // /**
  //  * Method for handling the different outcomes of the chooseable categories you
  //  * get when you want
  //  * to make a new ad. Each category has a case with different panes.
  //  *
  //  * @param event event
  //  */
  // @FXML
  // private void handleCategory(ActionEvent event) {
  //   Button activatedButton = (Button) event.getSource();
  //   String category = activatedButton.getId();

  //   switch (category) {
  //     case "ElectronicsButton1":
  //       categoriesPane.setDisable(true);
  //       categoriesPane.setVisible(false);
  //       electronicsAd.setDisable(false);
  //       electronicsAd.setVisible(true);

  //       categoryId = 1;

  //       break;
  //     case "ClothesButton1":
  //       categoriesPane.setDisable(true);
  //       categoriesPane.setVisible(false);
  //       clothingAd.setDisable(false);
  //       clothingAd.setVisible(true);

  //       categoryId = 2;

  //       break;
  //     case "PropertyButton1":
  //       categoriesPane.setDisable(true);
  //       categoriesPane.setVisible(false);
  //       propertyAd.setDisable(false);
  //       propertyAd.setVisible(true);

  //       categoryId = 3;

  //       break;
  //     case "VehiclesButton1":
  //       categoriesPane.setDisable(true);
  //       categoriesPane.setVisible(false);
  //       vehiclesAd.setDisable(false);
  //       vehiclesAd.setVisible(true);

  //       categoryId = 4;

  //       break;
  //     case "BooksButton1":
  //       categoriesPane.setDisable(true);
  //       categoriesPane.setVisible(false);
  //       booksAd.setDisable(false);
  //       booksAd.setVisible(true);

  //       categoryId = 5;

  //       break;

  //     default:
  //       break;
  //   }
  // }

  // /*
  //  * This method is called on when you press exit after you press make ad.
  //  * This is if you dont want to make an ad afterall.
  //  */
  // @FXML
  // private void handleExitButton() {
  //   homePage.setDisable(false);
  //   homePage.setVisible(true);
  //   categoriesPane.setDisable(true);
  //   categoriesPane.setVisible(false);
  // }

  // /*
  //  * The necessary field for making an ad.
  //  */

  // /*
  //  * One of the five methods for making an ad. This method validates all the input
  //  * field with an advalidator.
  //  * It also sends you to a preview state of your ad, and asks if you want to
  //  * change anything.
  //  * This one is for the clothing
  //  */
  // @FXML
  // private void makeAd2() {
  //   AdValidator adValidator = new AdValidator();
  //   String date = java.time.LocalDate.now().toString();
  //   FileOperator fileOperator = new FileOperator();
  //   String adID = String.valueOf(fileOperator.getAllAdsInFile(filename).size() + 1);
  //   if (colourChoiceClothing.getValue() != null) {
  //     try {
  //       adValidator.validateClothing(
  //           titleField2.getText(),
  //           descriptionArea2.getText(),
  //           priceField2.getText(),
  //           brandField2.getText(),
  //           typeField2.getText(),
  //           sizeField2.getText());

  //       Clothing product2 = new Clothing(
  //           Integer.parseInt(priceField2.getText()),
  //           setCondition(conditionField2),
  //           brandField2.getText(),
  //           typeField2.getText(),
  //           colourChoiceClothing.getValue().toString(),
  //           sizeField2.getText());
  //       ad = new Ad(titleField2.getText(), product2, date, descriptionArea2.getText(), adID, false);

  //       titlePreview.setText(titleField2.getText());
  //       conditionPreview.setText(setCondition(conditionField2));
  //       pricePreview.setText(priceField2.getText() + "Kr");
  //       descriptionPreview.setText(descriptionArea2.getText());
  //       label1.setText("Brand: " + brandField2.getText());
  //       label2.setText("Type: " + typeField2.getText());
  //       label3.setText("Colour: " + colourChoiceClothing.getValue().toString());
  //       label4.setText("Size: " + sizeField2.getText());

  //       clothingAd.setDisable(true);
  //       clothingAd.setVisible(false);
  //       adPreview.setDisable(false);
  //       adPreview.setVisible(true);
  //     } catch (IllegalArgumentException e) {
  //       displayError(e.getMessage());
  //     }
  //   } else {
  //     displayError("You have to fill out all of the input fields");
  //   }
  // }

  /*
   * One of the five methods for making an ad. This method validates all the input
   * field with an advalidator.
   * It also sends you to a preview state of your ad, and asks if you want to
   * change anything.
   * This one is for the vehicles
   */
  // @FXML
  // private void makeAd4() {
  //   AdValidator adValidator = new AdValidator();
  //   String date = java.time.LocalDate.now().toString();
  //   FileOperator fileOperator = new FileOperator();
  //   String adID = String.valueOf(fileOperator.getAllAdsInFile(filename).size() + 1);
  //   if (colourChoiceVehicles.getValue() != null) {
  //     try {
  //       adValidator.validateVehicles(
  //           titleField4.getText(),
  //           descriptionArea4.getText(),
  //           priceField4.getText(),
  //           brandField4.getText(),
  //           typeField4.getText(),
  //           yearField4.getText());

  //       Vehicles product4 = new Vehicles(
  //           Integer.parseInt(priceField4.getText()),
  //           setCondition(conditionField4),
  //           brandField4.getText(),
  //           typeField4.getText(),
  //           Integer.parseInt(yearField4.getText()),
  //           colourChoiceVehicles.getValue().toString());
  //       ad = new Ad(titleField4.getText(), product4, date, descriptionArea4.getText(), adID, false);

  //       titlePreview.setText(titleField4.getText());
  //       conditionPreview.setText(setCondition(conditionField4));
  //       pricePreview.setText(priceField4.getText() + "Kr");
  //       descriptionPreview.setText(descriptionArea4.getText());
  //       label1.setText("Brand: " + brandField4.getText());
  //       label2.setText("Type: " + typeField4.getText());
  //       label3.setText("Colour: " + colourChoiceVehicles.getValue().toString());
  //       label4.setText("Year: " + yearField4.getText());

  //       vehiclesAd.setDisable(true);
  //       vehiclesAd.setVisible(false);
  //       adPreview.setDisable(false);
  //       adPreview.setVisible(true);
  //     } catch (IllegalArgumentException e) {
  //       displayError(e.getMessage());
  //     }
  //   } else {
  //     displayError("You have to fill out all of the input fields");
  //   }
  // }

  

  /**
   * This methods just sets the colour choices in the to different dropdownboxes
   * for choosing colours in the clothing and
   * vehicles ad types.
   */


  // /*
  //  * This is the method you can call on when you see the preview of your ad.
  //  * After you see your preview you can either go back and edit, or press post ad.
  //  * If post ad i pressed this method runs, which ads the ad to the users list of
  //  * ads,
  //  * and updates the json file with the latest information
  //  */
  // @FXML
  // private void handlePostAd() {
  //   adPreview.setDisable(true);
  //   adPreview.setVisible(false);
  //   homePage.setDisable(false);
  //   homePage.setVisible(true);

  //   // skrive ad til fil her
  //   // gjøre det mulig å browse ad på hjemmesiden
  //   this.user.addAdToList(ad.getAdID());
  //   FileOperator fo = new FileOperator();
  //   fo.updateUserObjectJsonFile(filename, user);
  //   fo.addAdToFile(filename, ad, user);
  //   first();

    // // erase clothing
    // priceField2.setText("");
    // titleField2.setText("");
    // descriptionArea2.setText("");
    // conditionField2.setSelected(false);
    // colourChoiceClothing.setValue(null);
    // brandField2.setText("");
    // typeField2.setText("");
    // sizeField2.setText("");

  //   // erase vehicles
  //   priceField4.setText("");
  //   titleField4.setText("");
  //   descriptionArea4.setText("");
  //   conditionField4.setSelected(false);
  //   colourChoiceVehicles.setValue(null);
  //   brandField4.setText("");
  //   typeField4.setText("");
  //   yearField4.setText("");

  //   // erase books
  //   priceField5.setText("");
  //   titleField5.setText("");
  //   descriptionArea5.setText("");
  //   conditionField5.setSelected(false);
  //   genreField5.setText("");
  //   pagesField5.setText("");
  //   yearField5.setText("");
  //   authorField5.setText("");

  //   ad = new Ad();
  // }

  // /*
  //  * Method for editing after you see your preview. You can go back and change
  //  * before you can se a preview of your new ad.
  //  */
  // @FXML
  // private void handleEdit() {

  //   adPreview.setDisable(true);
  //   adPreview.setVisible(false);

  //   ad = new Ad();

  //   switch (categoryId) {
  //     case 1:
  //       electronicsAd.setDisable(false);
  //       electronicsAd.setVisible(true);
  //       break;
  //     case 2:
  //       clothingAd.setDisable(false);
  //       clothingAd.setVisible(true);
  //       break;
  //     case 3:
  //       propertyAd.setDisable(false);
  //       propertyAd.setVisible(true);
  //       break;
  //     case 4:
  //       vehiclesAd.setDisable(false);
  //       vehiclesAd.setVisible(true);
  //       break;
  //     case 5:
  //       booksAd.setDisable(false);
  //       booksAd.setVisible(true);
  //       break;

  //     default:
  //       break;
  //   }
  // }

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
    AdSorter adsorter = new AdSorter(fileOperator.getAllAdsInFile(filename));
    Button pressedButton = (Button) event.getSource();
    this.listOfAds.getItems().clear();
    this.listOfAds.getItems().addAll(adsorter
        .sortAds(ad -> ad.getProduct().getClass().getSimpleName().equals(pressedButton.getText())));
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