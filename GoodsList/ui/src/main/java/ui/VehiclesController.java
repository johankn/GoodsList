package ui;

import core.AdValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import json.Ad;
import json.User;
import json.Vehicles;

/**
 * Controller for the vehicle ads. 
 */
public class VehiclesController extends AbstractController {

  @FXML private TextField titleField4;
  @FXML private TextField priceField4;
  @FXML private TextField brandField4;
  @FXML private TextField typeField4;
  @FXML private TextField yearField4;
  @FXML private TextField sizeField4;
  @FXML private TextArea descriptionArea4;
  @FXML private CheckBox conditionField4;
  @FXML private ChoiceBox<String> colourChoiceVehicles;
  @FXML private Button goBackFromAd1;
  @FXML private Button makeAd22;

  private Ad ad;
  private User user;

  public void setUser(User user) {
    this.user = user;
    super.setUser(this.user);
  }

  public void setAd(Ad ad) {
    this.ad = ad;
  }

  public void setFilename(String filename) {
    super.setFilename(filename);
  }

  /**
   * Method for setting the different colors in choicebox. 
   */
  public void setChoiceBox() {
    colourChoiceVehicles.getItems().add("Black");
    colourChoiceVehicles.getItems().add("White");
    colourChoiceVehicles.getItems().add("Red");
    colourChoiceVehicles.getItems().add("Blue");
    colourChoiceVehicles.getItems().add("Brown");
  }

  /**
   * Method for setting the old info, if you are coming back from the preview ad. 
   */
  public void setOldInfo() {
    titleField4.setText(ad.getAdTitle());
    colourChoiceVehicles.setValue(((Vehicles) ad.getProduct()).getColor());
    descriptionArea4.setText(ad.getDescription());
    priceField4.setText(String.valueOf(ad.getProduct().getPrice()));
    brandField4.setText(((Vehicles) ad.getProduct()).getBrand());
    typeField4.setText(((Vehicles) ad.getProduct()).getModelName());
    yearField4.setText(String.valueOf(((Vehicles) ad.getProduct()).getModelYear()));
  }

  @FXML
  private void makeAd4() {
    AdValidator adValidator = new AdValidator();
    String date = java.time.LocalDate.now().toString();
    String adId = String.valueOf(getDataAccess().getAllAdsInFile().size() + 1);

    if (colourChoiceVehicles.getValue() != null) {
      try {
        adValidator.validateVehicles(
            titleField4.getText(),
            descriptionArea4.getText(),
            priceField4.getText(),
            brandField4.getText(),
            typeField4.getText(),
            yearField4.getText());

        Vehicles product4 =
            new Vehicles(
                Integer.parseInt(priceField4.getText()),
                setCondition(conditionField4),
                brandField4.getText(),
                typeField4.getText(),
                Integer.parseInt(yearField4.getText()),
                colourChoiceVehicles.getValue().toString());
        ad = new Ad(titleField4.getText(), product4, date, descriptionArea4.getText(), adId, false);
        super.setAd(ad);
        super.setPreviousController(this);
        Stage stage = (Stage) makeAd22.getScene().getWindow();
        super.setScene(Controllers.PREVIEW, stage, getDataAccess());
      } catch (IllegalArgumentException e) {
        displayError(e.getMessage());
      }
    } else {
      displayError("You have to fill out all of the input fields");
    }
  }

  @FXML
  private void goBack() {
    Stage stage = (Stage) goBackFromAd1.getScene().getWindow();
    super.setScene(Controllers.CATEGORIES, stage, getDataAccess());
  }

  /**
   * private method for displaying an error with the given param message Is used when making an ad,
   * if something is wrong in the inout fields. It gets the message from the exception that is
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
