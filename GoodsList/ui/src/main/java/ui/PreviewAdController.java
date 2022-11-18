package ui;

import javafx.fxml.FXML;
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

/**
 * Controller for the preview of an ad. 
 */
public class PreviewAdController extends AbstractController {

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
  private Label label3;
  @FXML
  private Label label4;
  @FXML
  private Button postButton;
  @FXML
  private Button editButton;

  private Ad ad;
  private User user;
  private AbstractController previousController;

  public void setUser(User user) {
    super.setUser(user);
    this.user = user;
  }

  public void setAd(Ad ad) {
    this.ad = ad;
    super.setAd(ad);
  }

  public void setFilename(String filename) {
    super.setFilename(filename);
  }

  public void setPreviousController(AbstractController controller) {
    this.previousController = controller;
    super.setPreviousController(controller);
  }

  /**
   * Method for setting the info of the previewed ad. 
   */
  public void setPreview() {
    titlePreview.setText(ad.getAdTitle());
    conditionPreview.setText("Condition: " + ad.getProduct().getCondition());
    pricePreview.setText("Price: " + String.valueOf(ad.getProduct().getPrice()));
    descriptionPreview.setText(ad.getDescription());
    if (ad.getProduct() instanceof Electronics) {
      label2.setText(((Electronics) ad.getProduct()).getType());
      label1.setText(((Electronics) ad.getProduct()).getBrand());
      label4.setVisible(false);
      label3.setVisible(false);
    }
    if (ad.getProduct() instanceof Books) {
      label2.setText("Pages: " + ((Books) ad.getProduct()).getPages());
      label1.setText("Author: " + ((Books) ad.getProduct()).getAuthor());
      label3.setText("Genre: " + ((Books) ad.getProduct()).getGenre());
      label4.setText("Released in " + ((Books) ad.getProduct()).getReleaseYear());
    }
    if (ad.getProduct() instanceof Clothing) {
      label2.setText(((Clothing) ad.getProduct()).getType());
      label1.setText(((Clothing) ad.getProduct()).getBrand());
      label3.setText("Color: " + ((Clothing) ad.getProduct()).getColor());
      label4.setText("Size: " + ((Clothing) ad.getProduct()).getSize());
    }
    if (ad.getProduct() instanceof Property) {
      label2.setText(((Property) ad.getProduct()).getPropertyType());
      label1.setText("Bedrooms: " + ((Property) ad.getProduct()).getBedrooms());
      label3.setText("Built in " + ((Property) ad.getProduct()).getYearBuilt());
      label4.setText("Area: " + ((Property) ad.getProduct()).getArea() + "m^2");
    }
    if (ad.getProduct() instanceof Vehicles) {
      label2.setText(((Vehicles) ad.getProduct()).getModelName());
      label1.setText(((Vehicles) ad.getProduct()).getBrand());
      label3.setText("From " + ((Vehicles) ad.getProduct()).getModelYear());
      label4.setText("Color: " + ((Vehicles) ad.getProduct()).getColor());
    }
  }

  @FXML
  private void handlePostAd() {
    this.user.addAdToList(ad.getAdId());
    getDataAccess().newAd(ad);
    getDataAccess().updateUser(this.user);
    Stage stage = (Stage) postButton.getScene().getWindow();
    super.setScene(Controllers.APP, stage, getDataAccess());
  }

  @FXML
  private void handleEdit() {
    if (this.previousController instanceof ElectronicsController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.ELECTRONICS, stage, getDataAccess());
    } else if (this.previousController instanceof BooksController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.BOOKS, stage, getDataAccess());
    } else if (this.previousController instanceof VehiclesController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.VEHICLES, stage, getDataAccess());
    } else if (this.previousController instanceof PropertyController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.PROPERTY, stage, getDataAccess());
    } else if (this.previousController instanceof ClothingController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.CLOTHING, stage, getDataAccess());
    }
  }
}
