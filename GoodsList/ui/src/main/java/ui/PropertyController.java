package ui;

import core.AdValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import json.Ad;
import json.Property;
import json.FileOperator;
import json.User;

public class PropertyController extends AbstractController {

  @FXML
  private Button goBackFromAd2;
  @FXML
  private TextField titleField3;
  @FXML
  private TextField priceField3;
  @FXML
  private TextField typeField3;
  @FXML
  private TextField areaField3;
  @FXML
  private TextField yearBuiltField3;
  @FXML
  private TextField bedroomsField3;
  @FXML
  private TextArea descriptionArea3;
  @FXML
  private CheckBox conditionField3;

  // @FXML
  // private Label titlePreview;
  // @FXML
  // private Label pricePreview;
  // @FXML
  // private Label conditionPreview;
  // @FXML
  // private Label descriptionPreview;
  // @FXML
  // private Label label1;
  // @FXML
  // private Label label2;
  // @FXML
  // private Label label3;
  // @FXML
  // private Label label4;



  private Ad ad;
  private User user;

  public void setUser(User user) {
    this.user = user;
    super.setUser(user);
  }

  /*
   * One of the five methods for making an ad. This method validates all the input
   * field with an advalidator.
   * It also sends you to a preview state of your ad, and asks if you want to
   * change anything.
   * This one is for the Property
   */
  @FXML
  private void makeAd3() {
    AdValidator adValidator = new AdValidator();
    String date = java.time.LocalDate.now().toString();
    FileOperator fileOperator = new FileOperator();
    String adID = String.valueOf(fileOperator.getAllAdsInFile(super.getFilename()).size() + 1);
    try {
      adValidator.validateProperty(
          titleField3.getText(),
          descriptionArea3.getText(),
          priceField3.getText(),
          typeField3.getText(),
          yearBuiltField3.getText(),
          bedroomsField3.getText(),
          areaField3.getText());

      Property product3 = new Property(
          Integer.parseInt(priceField3.getText()),
          setCondition(conditionField3),
          typeField3.getText(),
          Integer.parseInt(yearBuiltField3.getText()),
          Integer.parseInt(bedroomsField3.getText()),
          Integer.parseInt(areaField3.getText()));
      ad = new Ad(titleField3.getText(), product3, date, descriptionArea3.getText(), adID, false);
      super.setAd(ad);

      // titlePreview.setText(titleField3.getText());
      // conditionPreview.setText(setCondition(conditionField3));
      // pricePreview.setText(priceField3.getText() + "Kr");
      // descriptionPreview.setText(descriptionArea3.getText());
      // label1.setText("Area: " + areaField3.getText());
      // label2.setText("Type: " + typeField3.getText());
      // label3.setText("Bedrooms: " + bedroomsField3.getText());
      // label4.setText("Year: " + yearBuiltField3.getText());


    } catch (IllegalArgumentException e) {
      displayError(e.getMessage());
    }
  }

  @FXML
  private void goBack() {
    Stage stage = (Stage) titleField3.getScene().getWindow();
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
    //  // erase property
    //  priceField3.setText("");
    //  titleField3.setText("");
    //  descriptionArea3.setText("");
    //  conditionField3.setSelected(false);
    //  typeField3.setText("");
    //  areaField3.setText("");
    //  yearBuiltField3.setText("");
    //  bedroomsField3.setText("");

}
