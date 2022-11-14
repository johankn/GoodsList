package ui;

import core.AdValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import json.Ad;
import json.Clothing;
import json.Electronics;
import json.FileOperator;
import json.User;

public class ClothingController extends AbstractController{

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
    private ChoiceBox colourChoiceClothing;
    @FXML
    private Button goBackFromAd3;
    @FXML
    private Button makeAd2;
  
    private Ad ad;
    private User user;
    private String filename;

  public void setUser(User user) {
    this.user = user;
    super.setUser(user);
  }

  public void setAd(Ad ad) {
    this.ad = ad;
  }

  public void setFilename(String filename) {
    this.filename = filename;
    super.setFilename(filename);
  }
    


  public void setChoiceBox() {
    colourChoiceClothing.getItems().add("Black");
    colourChoiceClothing.getItems().add("White");
    colourChoiceClothing.getItems().add("Red");
    colourChoiceClothing.getItems().add("Blue");
    colourChoiceClothing.getItems().add("Brown");
  }

  public void setOldInfo() {
    titleField2.setText(ad.getAdTitle());
    colourChoiceClothing.setValue(((Clothing)ad.getProduct()).getColor());
    descriptionArea2.setText(ad.getDescription());
    priceField2.setText(String.valueOf(ad.getProduct().getPrice()));
    brandField2.setText(((Clothing) ad.getProduct()).getBrand());
    typeField2.setText(((Clothing) ad.getProduct()).getType());
    sizeField2.setText(((Clothing)ad.getProduct()).getSize());
  }
  
  /*
   * The necessary field for making an ad.
   */

  /*
   * One of the five methods for making an ad. This method validates all the input
   * field with an advalidator.
   * It also sends you to a preview state of your ad, and asks if you want to
   * change anything.
   * This one is for the clothing
   */
  @FXML
  private void makeAd2() {
    AdValidator adValidator = new AdValidator();
    String date = java.time.LocalDate.now().toString();
    FileOperator fileOperator = new FileOperator();
    String adID = String.valueOf(fileOperator.getAllAdsInFile(filename).size() + 1);
    if (colourChoiceClothing.getValue() != null) {
      try {
        adValidator.validateClothing(
            titleField2.getText(),
            descriptionArea2.getText(),
            priceField2.getText(),
            brandField2.getText(),
            typeField2.getText(),
            sizeField2.getText());

        Clothing product2 = new Clothing(
            Integer.parseInt(priceField2.getText()),
            setCondition(conditionField2),
            brandField2.getText(),
            typeField2.getText(),
            colourChoiceClothing.getValue().toString(),
            sizeField2.getText());
        ad = new Ad(titleField2.getText(), product2, date, descriptionArea2.getText(), adID, false);
        super.setAd(ad);
        super.setPreviousController(this);
        Stage stage = (Stage) makeAd2.getScene().getWindow();
        super.setScene(Controllers.PREVIEW, stage);
      } catch (IllegalArgumentException e) {
        displayError(e.getMessage());
      }
    } else {
      displayError("You have to fill out all of the input fields");
    }
  }

  @FXML
  private void goBack() {
    Stage stage = (Stage) goBackFromAd3.getScene().getWindow();
    super.setScene(Controllers.CATEGORIES, stage);
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

  private String setCondition(CheckBox field) {
    if (field.isSelected()) {
      return "New";
    }
    return "Used";
  }
}
