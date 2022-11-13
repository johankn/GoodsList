package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import json.User;

public class CategoriesController extends AbstractController{

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
  private Button exitButton;

  private User user;
  private String filename;

  public void setUser(User user) {
    this.user = user;
    super.setUser(user);
  }

  public void setFilename(String filename) {
    this.filename = filename;
    super.setFilename(filename);
  }


    /**
   * Method for handling the different outcomes of the chooseable categories you
   * get when you want
   * to make a new ad. Each category has a case with different panes.
   *
   * @param event event
   */
  @FXML
  private void handleCategory(ActionEvent event) {
    Button activatedButton = (Button) event.getSource();
    String category = activatedButton.getId();
    switch (category) {
      case "electronicsButton":
        Stage stage1 = (Stage) electronicsButton.getScene().getWindow();
        setScene(Controllers.ELECTRONICS, stage1, getDataAccess());
        break;
      case "clothingButton":
        Stage stage2 = (Stage) clothingButton.getScene().getWindow();
        setScene(Controllers.CLOTHING, stage2, getDataAccess());
        break;
      case "propertyButton":
        Stage stage3 = (Stage) propertyButton.getScene().getWindow();
        setScene(Controllers.PROPERTY, stage3, getDataAccess());
        break;
      case "vehiclesButton":
        Stage stage4 = (Stage) vehiclesButton.getScene().getWindow();
        setScene(Controllers.VEHICLES, stage4, getDataAccess());
        break;
      case "booksButton":
        Stage stage5 = (Stage) activatedButton.getScene().getWindow();
        setScene(Controllers.BOOKS, stage5, getDataAccess());
        break;

      default:
        break;
    }
  }

  /*
   * This method is called on when you press exit after you press make ad.
   * This is if you dont want to make an ad afterall.
   */
  @FXML
  private void handleExitButton() {
    Stage stage = (Stage) exitButton.getScene().getWindow();
    super.setScene(Controllers.APP, stage, getDataAccess());
  }


}
