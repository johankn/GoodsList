package ui;

import core.Ad;
import core.AdValidator;
import core.Books;
import core.Clothing;
import core.Electronics;
import core.FileOperator;
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

    @FXML private Button postButton;
    @FXML private Label WelcomeText, titlePreview, pricePreview, conditionPreview, descriptionPreview, label1, label2, label3, label4, label5;
    @FXML private AnchorPane homePage, newAdPage, adPreview, categoriesPane, electronicsAd, clothingAd, propertyAd, vehiclesAd, booksAd;
    @FXML private ChoiceBox<String> colourChoiceVehicles, colourChoiceClothing;
    
    private Ad ad;

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

    AdValidator adValidator = new AdValidator();
    String date = java.time.LocalDate.now().toString();

    /* skal ikke bruke categoryId som id siden alle ads skal ha unik id, men da må man 
        ha id lagret i fil. Ikke implementert enda, så per nå kan man kun lagre en annonse per kategori*/

    @FXML
    private void makeAd1(){
        try {
            adValidator.validateElectronics(titleField1.getText(), descriptionArea1.getText(), priceField1.getText(), brandField1.getText(), typeField1.getText());
            
            Electronics product1 = new Electronics(Integer.parseInt(priceField1.getText()), setCondition(conditionField1), brandField1.getText(), typeField1.getText());
            ad = new Ad(titleField1.getText(), product1, date, descriptionArea1.getText());

            titlePreview.setText(titleField1.getText());
            conditionPreview.setText(setCondition(conditionField1));
            pricePreview.setText(priceField1.getText()+"Kr");
            descriptionPreview.setText(descriptionArea1.getText());
            label1.setText("Brand: "+brandField1.getText());
            label2.setText("Type: "+typeField1.getText());

            electronicsAd.setDisable(true);
            electronicsAd.setVisible(false);
            adPreview.setDisable(false);
            adPreview.setVisible(true);
            
        } 
        catch (IllegalArgumentException e) {
            displayError(e.getMessage());
        }
    }
    
    @FXML
    private void makeAd2(){
        if (colourChoiceClothing.getValue() != null) {
            try {
            adValidator.validateClothing(titleField2.getText(), descriptionArea2.getText(),priceField2.getText(), brandField2.getText(), typeField2.getText(), sizeField2.getText());
            
            Clothing product2 = new Clothing(Integer.parseInt(priceField2.getText()), setCondition(conditionField2), brandField2.getText(), typeField2.getText(), colourChoiceClothing.getValue().toString(), sizeField2.getText());
            ad = new Ad(titleField2.getText(), product2, date, descriptionArea2.getText());

            titlePreview.setText(titleField2.getText());
            conditionPreview.setText(setCondition(conditionField2));
            pricePreview.setText(priceField2.getText()+"Kr");
            descriptionPreview.setText(descriptionArea2.getText());
            label1.setText("Brand: "+brandField2.getText());
            label2.setText("Type: "+typeField2.getText());
            label3.setText("Colour: "+colourChoiceClothing.getValue().toString());
            label4.setText("Size: "+sizeField2.getText());

            clothingAd.setDisable(true);
            clothingAd.setVisible(false);
            adPreview.setDisable(false);
            adPreview.setVisible(true);
        }   
        catch (IllegalArgumentException e) {
            displayError(e.getMessage());
        }}
        else{
            displayError("You have to fill out all of the input fields");
        }
    }
        
    @FXML
    private void makeAd3(){
        try {
            adValidator.validateProperty(titleField3.getText(), descriptionArea3.getText(),priceField3.getText(), typeField3.getText(), yearBuiltField3.getText(), bedroomsField3.getText(), areaField3.getText());
            
            Property product3 = new Property(Integer.parseInt(priceField3.getText()), setCondition(conditionField3), typeField3.getText(), Integer.parseInt(yearBuiltField3.getText()), Integer.parseInt(bedroomsField3.getText()), Integer.parseInt(areaField3.getText()));
            ad = new Ad(titleField3.getText(), product3, date, descriptionArea3.getText());

            titlePreview.setText(titleField3.getText());
            conditionPreview.setText(setCondition(conditionField3));
            pricePreview.setText(priceField3.getText()+"Kr");
            descriptionPreview.setText(descriptionArea3.getText());
            label1.setText("Area: "+areaField3.getText());
            label2.setText("Type: "+typeField3.getText());
            label3.setText("Bedrooms: "+bedroomsField3.getText());
            label4.setText("Year: "+yearBuiltField3.getText());

            propertyAd.setDisable(true);
            propertyAd.setVisible(false);
            adPreview.setDisable(false);
            adPreview.setVisible(true);
        }   
        catch (IllegalArgumentException e) {
            displayError(e.getMessage());
        }
        
    }
    @FXML
    private void makeAd4(){
        if (colourChoiceVehicles.getValue() != null) {
            try {
            adValidator.validateVehicles(titleField4.getText(), descriptionArea4.getText(),priceField4.getText(), brandField4.getText(), typeField4.getText(), yearField4.getText());
                
            Vehicles product4 = new Vehicles(Integer.parseInt(priceField4.getText()), setCondition(conditionField4), brandField4.getText(), typeField4.getText(), Integer.parseInt(yearField4.getText()), colourChoiceVehicles.getValue().toString());
            ad = new Ad(titleField4.getText(), product4, date, descriptionArea4.getText());

            titlePreview.setText(titleField4.getText());
            conditionPreview.setText(setCondition(conditionField4));
            pricePreview.setText(priceField4.getText()+"Kr");
            descriptionPreview.setText(descriptionArea4.getText());
            label1.setText("Brand: "+brandField4.getText());
            label2.setText("Type: "+typeField4.getText());
            label3.setText("Colour: "+colourChoiceVehicles.getValue().toString());
            label4.setText("Year: "+yearField4.getText());

            vehiclesAd.setDisable(true);
            vehiclesAd.setVisible(false);
            adPreview.setDisable(false);
            adPreview.setVisible(true);
        }   
        catch (IllegalArgumentException e) {
            displayError(e.getMessage());
        }}
        else{
            displayError("You have to fill out all of the input fields");
        }
        
    }
    @FXML
    private void makeAd5(){
        try {
            adValidator.validateBooks(titleField5.getText(), descriptionArea5.getText(),priceField5.getText(), authorField5.getText(), genreField5.getText(), yearField5.getText(), pagesField5.getText());
            
            Books product5 = new Books(Integer.parseInt(priceField5.getText()), setCondition(conditionField5), authorField5.getText(), genreField5.getText(), Integer.parseInt(yearField5.getText()), Integer.parseInt(pagesField5.getText()));
            ad = new Ad(titleField5.getText(), product5, date, descriptionArea5.getText());

            //preview
        titlePreview.setText(titleField5.getText());
        conditionPreview.setText(setCondition(conditionField5));
        pricePreview.setText(priceField5.getText()+"Kr");
        descriptionPreview.setText(descriptionArea5.getText());
        label1.setText("Author: "+authorField5.getText());
        label2.setText("Genre: "+genreField5.getText());
        label3.setText("Pages: "+pagesField5.getText());
        label4.setText("Year: "+yearField5.getText());

            booksAd.setDisable(true);
            booksAd.setVisible(false);
            adPreview.setDisable(false);
            adPreview.setVisible(true);

        }   
        catch (IllegalArgumentException e) {
            displayError(e.getMessage());
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


    
    @FXML
    private void handlePostAd(){
        adPreview.setDisable(true);
        adPreview.setVisible(false);
        homePage.setDisable(false);
        homePage.setVisible(true);

        //skrive ad til fil her
        //gjøre det mulig å browse ad på hjemmesiden
        this.user.addAdToList(ad);
        FileOperator fo = new FileOperator();
        fo.updateUserObjectJsonFile("..//core/src/main/java/json/dataObjects.json", user);


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

        ad = new Ad();
    }
    @FXML
    private void handleEdit(){
        
        adPreview.setDisable(true);
        adPreview.setVisible(false);

        ad = new Ad();

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
