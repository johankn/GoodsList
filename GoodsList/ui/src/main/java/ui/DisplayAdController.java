package ui;

import java.lang.annotation.AnnotationTypeMismatchException;

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
import json.Vehicles;
import ui.AbstractController.Controllers;
import json.Electronics;
import json.FileOperator;
import json.User;

public class DisplayAdController extends AbstractController {
  @FXML
  private Button buyButton;
  @FXML
  private Button cancel;
  @FXML
  private Button accept;
  @FXML
  private Button goBack;
  @FXML
  private Label areYouSure;
  @FXML
  private Label ifOwner;

  private Ad ad;
  private User user;
  private String filename;
  private AbstractController previousController;

  public void setUser(User user) {
    this.user = user;
    super.setUser(user);
  }

  public void setAd(Ad ad) {
    this.ad = ad;
    super.setAd(ad);
  }

  public void setFilename(String filename) {
    this.filename = filename;
    super.setFilename(filename);
  }

  public void setBuyPossible() {
    if (user.getBoughtAds().contains(ad.getAdID())) {
      buyButton.setDisable(true);
      buyButton.setVisible(false);
      ifOwner.setText("You have bought this ad");
    } else if (user.getMyAds().contains(ad.getAdID())) {
      buyButton.setDisable(true);
      buyButton.setVisible(false);
      if (ad.getIsSold()) {
        ifOwner.setText("You have sold this ad");
      } else {
        ifOwner.setText("You own this ad and it is still active");
      }
    } else {
      ifOwner.setDisable(true);
      ifOwner.setVisible(false);
      buyButton.setDisable(false);
      buyButton.setVisible(true);
    }
    cancel.setVisible(false);
    cancel.setDisable(true);
    accept.setVisible(false);
    accept.setDisable(true);
    areYouSure.setDisable(true);
    areYouSure.setVisible(false);
  }

  private void displayMessage(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void displayError(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("ERROR");
    alert.setContentText(message);
    alert.showAndWait();
  }

  /*
   * Method to go back to the home page.
   */
  @FXML
  private void handleGoBack() {
    if (this.previousController instanceof AppController) {
      Stage stage = (Stage) buyButton.getScene().getWindow();
      super.setScene(Controllers.APP, stage);
    }
    if (this.previousController instanceof ProfileController) {
      Stage stage = (Stage) buyButton.getScene().getWindow();
      super.setScene(Controllers.PROFILE, stage);
    }

  }

  public void setPreviousController(AbstractController controller) {
    this.previousController = controller;
  }

  /*
   * Method to buy an ad.
   */
  @FXML
  private void handleBuyAd() {
    buyButton.setDisable(true);
    buyButton.setVisible(false);
    cancel.setVisible(true);
    cancel.setDisable(false);
    accept.setVisible(true);
    accept.setDisable(false);
    areYouSure.setDisable(false);
    areYouSure.setVisible(true);
  }

  /*
   * Method to cancel the process of buying an ad.
   */
  @FXML
  private void handleCancel() {
    buyButton.setDisable(false);
    buyButton.setVisible(true);
    cancel.setVisible(false);
    cancel.setDisable(true);
    accept.setVisible(false);
    accept.setDisable(true);
    areYouSure.setDisable(true);
    areYouSure.setVisible(false);
  }

  /*
   * Method to actually buy an ad.
   */
  @FXML
  private void handleAccept() {
    try {
      ad.setIsSold(true);
      user.buyAdd(ad.getAdID());
      FileOperator fileOperator = new FileOperator();
      fileOperator.updateUserObjectJsonFile(filename, user);
      fileOperator.updateAdObjectJsonFile(filename, ad);
      handleCancel();
      displayMessage(ad.getAdTitle() + " was succesfully purchased");
      handleGoBack(); 
    } catch (IllegalArgumentException e) {
      // TODO: handle exception
      handleCancel();
      displayError(e.getMessage());
    }
  }
}
