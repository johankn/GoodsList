package ui;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import json.Ad;
import json.Books;
import json.Clothing;
import json.Electronics;
import json.FileOperator;
import json.Property;
import json.User;
import json.Vehicles;
import ui.AbstractController.Controllers;

/**
 * Controller for displaying an ad. 
 */
public class DisplayAdController extends AbstractController {
  @FXML private Button buyButton;
  @FXML private Button cancel;
  @FXML private Button accept;
  @FXML private Button goBack;
  @FXML private Label areYouSure;
  @FXML private Label ifOwner;
  @FXML private Label titleBuy;
  @FXML private Label descriptionBuy;
  @FXML private Label conditionBuy;
  @FXML private Label priceBuy;
  @FXML private Label label21;
  @FXML private Label label11;
  @FXML private Label label31;
  @FXML private Label label51;
  @FXML private Label label41;

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

  /**
   * Method for setting the info of the ad that is displayed. 
   */
  public void setInfo() {
    this.titleBuy.setText(ad.getAdTitle());
    this.descriptionBuy.setText(ad.getDescription());
    this.conditionBuy.setText("Condition: " + ad.getProduct().getCondition());
    this.priceBuy.setText("Price: " + String.valueOf(ad.getProduct().getPrice()));
    if (ad.getProduct() instanceof Electronics) {
      label11.setText(((Electronics) ad.getProduct()).getBrand());
      label21.setText(((Electronics) ad.getProduct()).getType());
      label31.setVisible(false);
      label41.setVisible(false);
    }
    if (ad.getProduct() instanceof Books) {
      label21.setText("Pages: " + ((Books) ad.getProduct()).getPages());
      label11.setText("Author: " + ((Books) ad.getProduct()).getAuthor());
      label31.setText("Genre: " + ((Books) ad.getProduct()).getGenre());
      label41.setText("Released in " + ((Books) ad.getProduct()).getReleaseYear());
    }
    if (ad.getProduct() instanceof Clothing) {
      label21.setText(((Clothing) ad.getProduct()).getType());
      label11.setText(((Clothing) ad.getProduct()).getBrand());
      label31.setText("Color: " + ((Clothing) ad.getProduct()).getColor());
      label41.setText("Size: " + ((Clothing) ad.getProduct()).getSize());
    }
    if (ad.getProduct() instanceof Property) {
      label21.setText(((Property) ad.getProduct()).getPropertyType());
      label11.setText("Bedrooms: " + ((Property) ad.getProduct()).getBedrooms());
      label31.setText("Built in " + ((Property) ad.getProduct()).getYearBuilt());
      label41.setText("Area: " + ((Property) ad.getProduct()).getArea() + "m^2");
    }
    if (ad.getProduct() instanceof Vehicles) {
      label21.setText(((Vehicles) ad.getProduct()).getModelName());
      label11.setText(((Vehicles) ad.getProduct()).getBrand());
      label31.setText("From " + ((Vehicles) ad.getProduct()).getModelYear());
      label41.setText("Color: " + ((Vehicles) ad.getProduct()).getColor());
    }
  }

  /**
   * Method for setting different info. 
   * The info depends wether the user is able to buy, or owns the ad. 
   */
  public void setBuyPossible() {
    if (user.getBoughtAds().contains(ad.getAdId())) {
      buyButton.setDisable(true);
      buyButton.setVisible(false);
      ifOwner.setText("You have bought this ad");
    } else if (user.getMyAds().contains(ad.getAdId())) {
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
      super.setScene(Controllers.APP, stage, getDataAccess());
    }
    if (this.previousController instanceof ProfileController) {
      Stage stage = (Stage) buyButton.getScene().getWindow();
      super.setScene(Controllers.PROFILE, stage, getDataAccess());
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
      user.buyAd(ad.getAdId());
      getDataAccess().updateAd(ad);
      getDataAccess().updateUser(user);
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
