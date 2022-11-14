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
import json.Electronics;
import json.FileOperator;
import json.User;

public class ElectronicsController extends AbstractController {
    
  @FXML
  private Button goBackFromAd4;
  @FXML
  private TextField titleField1;
  @FXML
  private TextField priceField1;
  @FXML
  private TextField brandField1;
  @FXML
  private TextField typeField1;
  @FXML
  private TextArea descriptionArea1;
  @FXML
  private CheckBox conditionField1;

  @FXML
  private Label titlePreview;
  @FXML
  private Label pricePreview;
  @FXML
  private Label conditionPreview;
  @FXML
  private Label descriptionPreview;
  @FXML
  private Label label1;
  @FXML
  private Label label2;
  @FXML
  private Button makeAd1;

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

  public void setOldInfo() {
    titleField1.setText(ad.getAdTitle());;
    descriptionArea1.setText(ad.getDescription());
    priceField1.setText(String.valueOf(ad.getProduct().getPrice()));
    brandField1.setText(((Electronics) ad.getProduct()).getBrand());
    typeField1.setText(((Electronics) ad.getProduct()).getType());
  }
  
  /*
   * One of the five methods for making an ad. This method validates all the input
   * field with an advalidator.
   * It also sends you to a preview state of your ad, and asks if you want to
   * change anything.
   * This one is for the electronics
   */
  @FXML
  private void makeAd1() {
    AdValidator adValidator = new AdValidator();
    String date = java.time.LocalDate.now().toString();
    FileOperator fileOperator = new FileOperator();
    String adID = String.valueOf(fileOperator.getAllAdsInFile(filename).size() + 1);
    try {
      adValidator.validateElectronics(
          titleField1.getText(),
          descriptionArea1.getText(),
          priceField1.getText(),
          brandField1.getText(),
          typeField1.getText());

      Electronics product1 = new Electronics(
          Integer.parseInt(priceField1.getText()),
          setCondition(conditionField1),
          brandField1.getText(),
          typeField1.getText());
      ad = new Ad(titleField1.getText(), product1, date, descriptionArea1.getText(), adID, false);
      super.setAd(ad);

      // titlePreview.setText(titleField1.getText());
      // conditionPreview.setText(setCondition(conditionField1));
      // pricePreview.setText(priceField1.getText() + "Kr");
      // descriptionPreview.setText(descriptionArea1.getText());
      // label1.setText("Brand: " + brandField1.getText());
      // label2.setText("Type: " + typeField1.getText());
      super.setPreviousController(this);
      Stage stage = (Stage) makeAd1.getScene().getWindow();
      super.setScene(Controllers.PREVIEW, stage);


    } catch (IllegalArgumentException e) {
      displayError(e.getMessage());
    }
  }

  @FXML
  private void goBack() {
    Stage stage = (Stage) makeAd1.getScene().getWindow();
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
