package ui;


import core.Ad;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


public class AppController {

    @FXML private Label WelcomeText;
    @FXML private AnchorPane homePage;
    @FXML private AnchorPane newAdPage;
    @FXML private ChoiceBox<String> categoryBox;
    @FXML private TextArea descriptionArea;
    @FXML private TextField titleField;
    @FXML private TextField priceField;
    @FXML private CheckBox conditionField;
    @FXML private Ad ad;


    @FXML
    public void setUsername(String username){
        WelcomeText.setText("Welcome, "+username);
    }
    @FXML
    private void handleNewAd(){
        homePage.setDisable(true);
        homePage.setVisible(false);
        newAdPage.setDisable(false);
        newAdPage.setVisible(true);

    }
    @FXML
    public void setChoiceBox(){
        categoryBox.getItems().add("Electronics");
        categoryBox.getItems().add("Clothes");
        categoryBox.getItems().add("Vehicles");
        categoryBox.getItems().add("Books");
        categoryBox.getItems().add("Property");
    }
    @FXML
    private void makeAd(){

        if (priceField.getText().isEmpty() || titleField.getText().isEmpty() || descriptionArea.getText().isEmpty() || categoryBox.getValue() == null){
            displayError("No fields can be empty!");
        }
        if (!(priceField.getText().matches("[0-9]+"))){
            displayError("Price must only contain numbers");
        }
    
        else{
            displayError("GOOD");
            // lage et ad objekt her
            // ny scene etter det, med oversikt over ad'en du nettopp laget.
        }


    }
    private void displayError(String message){

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(message);
        alert.showAndWait();
    
    }
    
}
