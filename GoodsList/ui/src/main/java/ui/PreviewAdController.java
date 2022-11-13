package ui;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import json.Ad;
import json.FileOperator;
import json.User;

public class PreviewAdController extends AbstractController{

  @FXML
  private Label titlePreview;
  @FXML
  private Label pricePreview;
  @FXML
  private Label conditionPreview;
  @FXML
  private Label descriptionPreview;
  @FXML
  private Button postButton;
  @FXML
  private Button editButton;

  private Ad ad;
  private User user;
  private String filename;
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
    this.filename = filename;
    super.setFilename(filename);
  }

  public void setPreviousController(AbstractController controller) {
    this.previousController = controller;
    super.setPreviousController(controller);
  }

  public void setPreview() {
    titlePreview.setText(ad.getAdTitle());
    conditionPreview.setText(ad.getProduct().getCondition());
    pricePreview.setText(String.valueOf(ad.getProduct().getPrice()));
    descriptionPreview.setText(ad.getDescription());
  }


  @FXML
  private void handlePostAd() {
    this.user.addAdToList(ad.getAdID());
    FileOperator fileOperator = new FileOperator();
    fileOperator.updateUserObjectJsonFile(filename, user);
    fileOperator.addAdToFile(filename, ad, user);
    Stage stage = (Stage) postButton.getScene().getWindow();
    super.setScene(Controllers.APP, stage, getDataAccess());
  }

  @FXML
  private void handleEdit() {
    if (this.previousController instanceof ElectronicsController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.ELECTRONICS, stage, getDataAccess());
    }
    else if (this.previousController instanceof BooksController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.BOOKS, stage, getDataAccess());
    }
    else if (this.previousController instanceof VehiclesController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.VEHICLES, stage, getDataAccess());
    }
    else if (this.previousController instanceof PropertyController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.PROPERTY, stage, getDataAccess());
    }
    else if (this.previousController instanceof ClothingController) {
      super.setPreviousController(this);
      Stage stage = (Stage) editButton.getScene().getWindow();
      super.setScene(Controllers.CLOTHING, stage, getDataAccess());
    }
  }

}