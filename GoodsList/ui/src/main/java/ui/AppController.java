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

    @FXML private Label WelcomeText, titlePreview, pricePreview, conditionPreview, descriptionPreview;
    @FXML private AnchorPane homePage, newAdPage, adPreview;
    @FXML private ChoiceBox<String> categoryBox;
    @FXML private TextArea descriptionArea;
    @FXML private TextField titleField, priceField;
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
            newAdPage.setDisable(true);
            newAdPage.setVisible(false);
            adPreview.setDisable(false);
            adPreview.setVisible(true);
            titlePreview.setText(titleField.getText());
            conditionPreview.setText(setCondition());
            pricePreview.setText(priceField.getText()+"Kr");
            descriptionPreview.setText(descriptionArea.getText());

            
            // ny scene etter det, med oversikt over ad'en du nettopp laget.
        }


    }
    @FXML
    private void handlePostAd(){
        adPreview.setDisable(true);
        adPreview.setVisible(false);
        homePage.setDisable(false);
        homePage.setVisible(true);
        // lage et ad objekt her
        // legge det til i JSON-fil med bruker-id og kategori-id

        priceField.setText("");
        titleField.setText("");
        descriptionArea.setText("");
        conditionField.setSelected(false);
        categoryBox.setValue(null);

    }
    @FXML
    private void handleEdit(){
        adPreview.setDisable(true);
        adPreview.setVisible(false);
        newAdPage.setDisable(false);
        newAdPage.setVisible(true); 
        
    }

    private void displayError(String message){

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(message);
        alert.showAndWait();
    
    }
    private String setCondition(){
        if (conditionField.isSelected()){
            return "New";
        }
        return "Used";
    }
    
}
