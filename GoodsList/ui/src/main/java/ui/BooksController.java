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
import json.Books;
import json.FileOperator;
import json.User;

public class BooksController extends AbstractController {

  @FXML
  private TextField titleField5;
  @FXML
  private TextField priceField5;
  @FXML
  private TextField genreField5;
  @FXML
  private TextField pagesField5;
  @FXML
  private TextField yearField5;
  @FXML
  private TextField authorField5;
  @FXML
  private TextArea descriptionArea5;
  @FXML
  private CheckBox conditionField5;
  @FXML
  private Button goBack;

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
   * This one is for the Books
   */
  @FXML
  private void makeAd5() {
    AdValidator adValidator = new AdValidator();
    String date = java.time.LocalDate.now().toString();
    FileOperator fileOperator = new FileOperator();
    String adID = String.valueOf(fileOperator.getAllAdsInFile(super.getFilename()).size() + 1);
    try {
      adValidator.validateBooks(
          titleField5.getText(),
          descriptionArea5.getText(),
          priceField5.getText(),
          authorField5.getText(),
          genreField5.getText(),
          yearField5.getText(),
          pagesField5.getText());

      Books product5 = new Books(
          Integer.parseInt(priceField5.getText()),
          setCondition(conditionField5),
          authorField5.getText(),
          genreField5.getText(),
          Integer.parseInt(yearField5.getText()),
          Integer.parseInt(pagesField5.getText()));
      ad = new Ad(titleField5.getText(), product5, date, descriptionArea5.getText(), adID, false);
      super.setAd(ad);
      //Stage stage = (Stage) makeAd5.getScene().getWindow();
      //super.setScene(Controllers.PREVIEW, stage);

      // preview
      // titlePreview.setText(titleField5.getText());
      // conditionPreview.setText(setCondition(conditionField5));
      // pricePreview.setText(priceField5.getText() + "Kr");
      // descriptionPreview.setText(descriptionArea5.getText());
      // label1.setText("Author: " + authorField5.getText());
      // label2.setText("Genre: " + genreField5.getText());
      // label3.setText("Pages: " + pagesField5.getText());
      // label4.setText("Year: " + yearField5.getText());

      // booksAd.setDisable(true);
      // booksAd.setVisible(false);
      // adPreview.setDisable(false);
      // adPreview.setVisible(true);

    } catch (IllegalArgumentException e) {
      displayError(e.getMessage());
    }
  }

  @FXML
  private void goBack() {
    Stage stage = (Stage) titleField5.getScene().getWindow();
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
      // // erase books
      // priceField5.setText("");
      // titleField5.setText("");
      // descriptionArea5.setText("");
      // conditionField5.setSelected(false);
      // genreField5.setText("");
      // pagesField5.setText("");
      // yearField5.setText("");
      // authorField5.setText("");
}
