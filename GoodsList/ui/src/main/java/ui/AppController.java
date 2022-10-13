package ui;


import core.Ad;
import core.Books;
import core.Clothing;
import core.Electronics;
import core.Property;
import core.User;
import core.Vehicles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


public class AppController {

    @FXML private Label WelcomeText, titlePreview, pricePreview, conditionPreview, descriptionPreview, label1, label2, label3, label4, label5;
    @FXML private AnchorPane homePage, newAdPage, adPreview, categoriesPane, electronicsAd, clothingAd, propertyAd, vehiclesAd, booksAd;
    @FXML private ChoiceBox<String> colourChoiceVehicles, colourChoiceClothing;
    @FXML private TextArea descriptionArea;
    @FXML private CheckBox conditionField;
    @FXML private Ad ad;

    //electronics
    @FXML private TextField titleField1, priceField1, brandField1, typeField1;
    @FXML private TextArea descriptionArea1;
    @FXML private CheckBox conditionField1;

    //clothing
    @FXML private TextField titleField2, priceField2, brandField2, typeField2, sizeField2;
    @FXML private TextArea descriptionArea2;
    @FXML private CheckBox conditionField2;

    //property
    @FXML private TextField titleField3, priceField3, typeField3, areaField3, yearBuiltField3, bedroomsField3;
    @FXML private TextArea descriptionArea3;
    @FXML private CheckBox conditionField3;


    //vehicles
    @FXML private TextField titleField4, priceField4, brandField4, typeField4, yearField4;
    @FXML private TextArea descriptionArea4;
    @FXML private CheckBox conditionField4;

    //books
    @FXML private TextField titleField5, priceField5, genreField5, pagesField5, yearField5, authorField5;
    @FXML private TextArea descriptionArea5;
    @FXML private CheckBox conditionField5;

    //which category ad is in the making
    private int categoryId;

    private User user;


    @FXML
    public void setUsername(User user){
        WelcomeText.setText("Welcome, " + user.getFullname());
        this.user = user;
    }
    @FXML
    private void handleNewAd(){
        homePage.setDisable(true);
        homePage.setVisible(false);
        categoriesPane.setDisable(false);
        categoriesPane.setVisible(true);

    }

    @FXML
    private void handleCategory(ActionEvent event) {
        Button activatedButton = (Button) event.getSource();
        String category = activatedButton.getId();

        switch (category) {
            case "ElectronicsButton1":
                categoriesPane.setDisable(true);
                categoriesPane.setVisible(false);
                electronicsAd.setDisable(false);
                electronicsAd.setVisible(true);

                categoryId = 1;

                
                break;
            case "ClothesButton1":
                categoriesPane.setDisable(true);
                categoriesPane.setVisible(false);
                clothingAd.setDisable(false);
                clothingAd.setVisible(true);
                
                categoryId = 2;

                break;
            case "PropertyButton1":
                categoriesPane.setDisable(true);
                categoriesPane.setVisible(false);
                propertyAd.setDisable(false);
                propertyAd.setVisible(true);

                categoryId = 3;
                
                break;
            case "VehiclesButton1":
                categoriesPane.setDisable(true);
                categoriesPane.setVisible(false);
                vehiclesAd.setDisable(false);
                vehiclesAd.setVisible(true);
                
                categoryId = 4;

                break;
            case "BooksButton1":
                categoriesPane.setDisable(true);
                categoriesPane.setVisible(false);
                booksAd.setDisable(false);
                booksAd.setVisible(true);

                categoryId = 5;
                
                break;

        
            default:
                break;
        }

    }
    @FXML
    private void handleExitButton(){
        homePage.setDisable(false);
        homePage.setVisible(true);
        categoriesPane.setDisable(true);
        categoriesPane.setVisible(false);
    }

    @FXML
    private void makeAd1(){
        if (validateStringField(brandField1, label1, "Brand-field can't be empty!", "") &&
        validateStringField(typeField1, label2, "Type-field can't be empty!", "")){

        validateAdFinal(titleField1, priceField1, descriptionArea1, electronicsAd, conditionField1);}
    }
    @FXML
    private void makeAd2(){
        if (validateStringField(brandField2, label1, "Brand-field can't be empty!", "") &&
        validateStringField(typeField2, label2, "Type-field can't be empty!", "") &&
        validateStringField(sizeField2, label3, "Size-field can't be empty!", "Size: ") &&

        validateColourChoice(colourChoiceClothing, label4, "Colour-field can't be empty!")){

        validateAdFinal(titleField2, priceField2, descriptionArea2, clothingAd, conditionField2);}
        
    }
    @FXML
    private void makeAd3(){
        if (validateStringField(typeField3, label2, "Type-field can't be empty!", "") &&

        validateIntField(areaField3, label1, "Area-field can't be empty!", "Area-field must only contain numbers!", "Area: ") &&
        validateIntField(yearBuiltField3, label3, "Year-field can't be empty!", "Year-field must only contain numbers!", "Year: ") &&
        validateIntField(bedroomsField3, label4, "Bedrooms-field can't be empty!", "Bedrooms-field must only contain numbers!", "Bedrooms: ")){

        validateAdFinal(titleField3, priceField3, descriptionArea3, propertyAd, conditionField3);}
        
    }
    @FXML
    private void makeAd4(){
        if (validateStringField(brandField4, label1, "Brand-field can't be empty!", "") &&
        validateStringField(typeField4, label2, "Type-field can't be empty!", "") &&

        validateIntField(yearField4, label3, "Year-field can't be empty!", "Year-field must only contain numbers!", "Year: ") &&

        validateColourChoice(colourChoiceVehicles, label4, "Colour-field can't be empty!")){

        validateAdFinal(titleField4, priceField4, descriptionArea4, vehiclesAd, conditionField4);
        }
        
    }
    @FXML
    private void makeAd5(){
        if (validateStringField(authorField5, label1, "Author-field can't be empty!", "Author: ") &&
        validateStringField(genreField5, label2, "Genre-field can't be empty!", "Genre: ") &&
        validateIntField(pagesField5, label3, "Pages-field can't be empty!", "Pages-field must only contain numbers!", "Pages: ") &&
        validateIntField(yearField5, label4, "Year-field can't be empty!", "Year-field must only contain numbers!", "Year: ")){

            validateAdFinal(titleField5, priceField5, descriptionArea5, booksAd, conditionField5);
        }
        
    }

    @FXML
    public void setChoiceBox(){
        colourChoiceClothing.getItems().add("Black");
        colourChoiceClothing.getItems().add("White");
        colourChoiceClothing.getItems().add("Red");
        colourChoiceClothing.getItems().add("Blue");
        colourChoiceClothing.getItems().add("Brown");

        colourChoiceVehicles.getItems().add("Black");
        colourChoiceVehicles.getItems().add("White");
        colourChoiceVehicles.getItems().add("Red");
        colourChoiceVehicles.getItems().add("Blue");
        colourChoiceVehicles.getItems().add("Brown");
    }

    private boolean validateIntField(TextField field, Label label, String text, String text2, String previewText){
        int okPublish = 1;
        if (field.getText().isEmpty()){
            displayError(text);
            okPublish = 0;
            return false;
        }

        else if (!(field.getText().matches("[0-9]+"))){
            displayError(text2);
            okPublish = 0;
            return false;

        }
        if (okPublish == 1){
            label.setText(previewText+field.getText());
            return true;

        }
        return false;
    }
    private boolean validateStringField(TextField field, Label label, String text, String previewText){
        int okPublish = 1;
        if (field.getText().isEmpty()){
            displayError(text);
            okPublish = 0;
            return false;
        }
        if (okPublish == 1){
            label.setText(previewText+field.getText());
            return true;

        }
        return false;

    }
    private boolean validateColourChoice(ChoiceBox<String> choice, Label label, String text) {
        int okPublish = 1;
        if (choice.getValue() == null){
            displayError(text);
            okPublish = 0;
            return false;
        }
        if (okPublish == 1){
            label.setText("Colour: "+ choice.getValue().toString());
            return true;
        }
        return false;
    }

    //@FXML
    private void validateAdFinal(TextField title, TextField price, TextArea description, AnchorPane pane, CheckBox checkBox){
        int okPublish = 1;
        if (price.getText().isEmpty() || title.getText().isEmpty() || description.getText().isEmpty()){
            displayError("No fields can be empty!");
            okPublish = 0;
        }

        else if (!(price.getText().matches("[0-9]+"))){
            displayError("Price must only contain numbers");
            okPublish = 0;

        }
    
        if (okPublish == 1){
            pane.setDisable(true);
            pane.setVisible(false);
            adPreview.setDisable(false);
            adPreview.setVisible(true);
            titlePreview.setText(title.getText());
            conditionPreview.setText(setCondition(checkBox));
            pricePreview.setText(price.getText()+"Kr");
            descriptionPreview.setText(description.getText());

            
            // ny scene etter det, med oversikt over ad'en du nettopp laget.
        }


    }
    @FXML
    private void handlePostAd(){
        adPreview.setDisable(true);
        adPreview.setVisible(false);
        homePage.setDisable(false);
        homePage.setVisible(true);
        // lage et ad objekt her, basert på hvilken categoryID som er
        // legge det til i JSON-fil med bruker-id og kategori-id
        // gjøre det mulig å browse ad på hjemmesiden

        String date = java.time.LocalDate.now().toString();

        /* skal ikke bruke categoryId som id siden alle ads skal ha unik id, men da må man 
        ha id lagret i fil. Ikke implementert enda, så per nå kan man kun lagre en annonse per kategori*/

        //skrive ad til fil i sprint2
        switch (categoryId) {
            case 1:
                Electronics product1 = new Electronics(Integer.parseInt(priceField1.getText()), setCondition(conditionField1), titleField1.getText(), brandField1.getText(), typeField1.getText());
                Ad ad1 = new Ad(product1, date, descriptionArea1.getText(), categoryId);
                break;
            case 2:
                Clothing product2 = new Clothing(Integer.parseInt(priceField2.getText()), setCondition(conditionField2), titleField2.getText(), brandField2.getText(), typeField2.getText(), colourChoiceClothing.getValue().toString(), sizeField2.getText());
                Ad ad2 = new Ad(product2, date, descriptionArea2.getText(), categoryId);
                break;
            case 3:
                Property product3 = new Property(Integer.parseInt(priceField3.getText()), setCondition(conditionField3), titleField3.getText(), typeField3.getText(), Integer.parseInt(yearBuiltField3.getText()), Integer.parseInt(bedroomsField3.getText()), Integer.parseInt(areaField3.getText()));
                Ad ad3 = new Ad(product3, date, descriptionArea3.getText(), categoryId);
                break;
            case 4:
                Vehicles product4 = new Vehicles(Integer.parseInt(priceField4.getText()), setCondition(conditionField4), titleField4.getText(), brandField4.getText(), typeField4.getText(), Integer.parseInt(yearField4.getText()));
                Ad ad4 = new Ad(product4, date, descriptionArea4.getText(), categoryId);
                break;
            case 5:
                Books product5 = new Books(Integer.parseInt(priceField5.getText()), setCondition(conditionField5), titleField5.getText(), authorField5.getText(), genreField5.getText(), Integer.parseInt(yearField5.getText()), Integer.parseInt(pagesField5.getText()));
                Ad ad5 = new Ad(product5, date, descriptionArea5.getText(), categoryId);
                break;
        
            default:
                break;
        }

        //erase electronics
        priceField1.setText("");
        titleField1.setText("");
        descriptionArea1.setText("");
        conditionField1.setSelected(false);
        brandField1.setText("");
        typeField1.setText("");
        
        //erase clothing
        priceField2.setText("");
        titleField2.setText("");
        descriptionArea2.setText("");
        conditionField2.setSelected(false);
        colourChoiceClothing.setValue(null);
        brandField2.setText("");
        typeField2.setText("");
        sizeField2.setText("");
        
        //erase property
        priceField3.setText("");
        titleField3.setText("");
        descriptionArea3.setText("");
        conditionField3.setSelected(false);
        typeField3.setText("");
        areaField3.setText(""); 
        yearBuiltField3.setText(""); 
        bedroomsField3.setText("");

        //erase vehicles
        priceField4.setText("");
        titleField4.setText("");
        descriptionArea4.setText("");
        conditionField4.setSelected(false);
        colourChoiceVehicles.setValue(null);
        brandField4.setText("");
        typeField4.setText("");
        yearField4.setText("");

        //erase books
        priceField5.setText("");
        titleField5.setText("");
        descriptionArea5.setText("");
        conditionField5.setSelected(false);
        genreField5.setText(""); 
        pagesField5.setText("");
        yearField5.setText("");
        authorField5.setText("");


    }
    @FXML
    private void handleEdit(){
        
        adPreview.setDisable(true);
        adPreview.setVisible(false);

        switch (categoryId) {
            case 1:
                electronicsAd.setDisable(false);
                electronicsAd.setVisible(true);
                break;
            case 2:
                clothingAd.setDisable(false);
                clothingAd.setVisible(true);
                break;
            case 3:
                propertyAd.setDisable(false);
                propertyAd.setVisible(true);
                break;
            case 4:
                vehiclesAd.setDisable(false);
                vehiclesAd.setVisible(true);
                break;
            case 5:
                booksAd.setDisable(false);
                booksAd.setVisible(true);
                break;
        
            default:
                break;
        }
        
        
        
    }

    private void displayError(String message){

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(message);
        alert.showAndWait();
    
    }
    private String setCondition(CheckBox field){
        if (field.isSelected()){
            return "New";
        }
        return "Used";
    }
    
}
