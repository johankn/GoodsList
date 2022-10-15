package ui;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * TestFX App test
 */
@TestMethodOrder(OrderAnnotation.class)
public class UiSecondTest extends ApplicationTest {

    // private FxRobot robot = new FxRobot();

    // @Override
    // public void start(Stage stage) throws IOException {
    //         FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("App.fxml"));
    //         final Parent root = fxmlLoader.load();
    //         //this.loginController = fxmlLoader.getController();
    //         stage.setScene(new Scene(root));
    //         stage.show();
    // }

    // private void makeAd() {
    //     robot.clickOn("#newAdButton");
    // }

    // @Test
    // @Order(1)
    // public void TestMakeElectronicsAd() {
    //     this.makeAd();
    //     robot.clickOn("#ElectronicsButton1");
    //     sleep(3000);
    // }

    // @Test
    // @DisplayName("Test create ads")
    // public void testCreateAd() throws IOException {
    //     //ELECTRONICS
    //     String adTitle1 = "MacBook Pro 2022";
    //     String adDescription1 = "Brand new MacBook Pro 2022 with M1 chip for sale! Price can be discussed in dm.";
    //     String invalidAdPrice1 = "Twenty thousand";
    //     String validAdPrice1 = "20000";
    //     String adBrand1 = "Apple";
    //     String adType1 = "Laptop";

    //     robot.clickOn("#newAdButton");
    //     robot.clickOn("#ElectronicsButton1");
    //     robot.clickOn("#titleField1").write(adTitle1);
    //     robot.clickOn("#descriptionArea1").write(adDescription1);
    //     robot.clickOn("#conditionField1");
    //     robot.clickOn("#priceField1").write(invalidAdPrice1);
    //     robot.clickOn("#brandField1").write(adBrand1);
    //     robot.clickOn("#typeField1").write(adType1);
    //     robot.clickOn("#makeAd1");
    //     robot.clickOn(850, 390);
    //     robot.doubleClickOn("#priceField1").write(validAdPrice1);
    //     robot.clickOn("#makeAd1");

    //     //CLOTHES
    //     String adTitle2 = "Red Gucci sweater";
    //     String adDescription2 = "Carefully used Red Gucci sweater bought in Milano for sale with receipt!";
    //     String adPrice2 = "5000";
    //     String adType2 = "Sweater";
    //     String invalidAdSize2 = "";
    //     String validAdSize2 = "M";
    //     String adBrand2 = "Gucci";
    //     robot.clickOn("#newAdButton");
    //     robot.clickOn("#ClothesButton1");
    //     robot.clickOn("#titleField2").write(adTitle2);
    //     robot.clickOn("#descriptionArea2").write(adDescription2);
    //     robot.clickOn("#priceField2").write(adPrice2);
    //     robot.clickOn("#typeField2").write(adType2);
    //     robot.clickOn("#sizeField2").write(invalidAdSize2);
    //     robot.clickOn("#brandField2").write(adBrand2);
    //     robot.clickOn("#makeAd2");
    //     robot.clickOn(850, 390);
    //     robot.clickOn("#sizeField2").write(validAdSize2);
    //     robot.clickOn("#makeAd2");

    //     //PROPERTY
    //     String adTitle3 = "House for sale";
    //     String adDescription3 = "Cozy 4 bedroom house in the outskirts of Boston.";
    //     String adPrice3 = "4250000";
    //     String invalidType3 = "100110";
    //     String validType3 = "House";
    //     String adYearBuilt3 = "2004";
    //     String adBedrooms3 = "4";
    //     String adArea3 = "Trondheim";
    //     robot.clickOn("#newAdButton");
    //     robot.clickOn("#PropertyButton1");
    //     robot.clickOn("#titleField3").write(adTitle3);
    //     robot.clickOn("#descriptionArea3").write(adDescription3);
    //     robot.clickOn("#priceField3").write(adPrice3);
    //     robot.clickOn("#typeField3").write(invalidType3);
    //     robot.clickOn("#yearBuiltField3").write(adYearBuilt3);
    //     robot.clickOn("#bedroomsField3").write(adBedrooms3);
    //     robot.clickOn("#areaField3").write(adArea3);
    //     robot.clickOn("#makeAd21");
    //     robot.clickOn(850, 390);
    //     robot.clickOn("#typeField3").write(validType3);
    //     robot.clickOn("#makeAd21");

    //     //VEHICLES
    //     String adTitle4 = "Volvo XC90 hybrid 2022";
    //     String adDescription4 = "Brand new Volvo XC90 hybrid for sale, fully specced.";
    //     String adPrice4 = "900000";
    //     String adType4 = "XC 90";
    //     String adYear4 = "2022";
    //     String adBrand4 = "Volvo";
    //     robot.clickOn("#newAdButton");
    //     robot.clickOn("#VehiclesButton1");
    //     robot.clickOn("#titleField4").write(adTitle4);
    //     robot.clickOn("#descriptionArea4").write(adDescription4);
    //     robot.clickOn("#priceField4").write(adPrice4);
    //     robot.clickOn("#conditionField4");
    //     robot.clickOn("#typeField4").write(adType4);
    //     robot.clickOn("#yearField4").write(adYear4);
    //     robot.clickOn("#brandField4").write(adBrand4);
    //     robot.clickOn("#makeAd22");
    //     robot.clickOn(850, 390);
    //     robot.clickOn("#colourChoiceVehicles");
    //     robot.clickOn("#makeAd22");

    //     //BOOKS
    //     String adTitle5 = "George Orwell 1984";
    //     String adDescription5 = "The best-selling classic from George Orwell.";
    //     String adPrice5 = "149";
    //     String invalidPages5 = "100000+";
    //     String validPages5 = "328";
    //     String adAuthor5 = "George Orwell";
    //     String adGenre5 = "Sci-fi";
    //     String adYear5 = "1949";
    //     robot.clickOn("#newAdButton");
    //     robot.clickOn("#BooksButton5");
    //     robot.clickOn("#titleField5").write(adTitle5);
    //     robot.clickOn("#descriptionArea5").write(adDescription5);
    //     robot.clickOn("#priceField5").write(adPrice5);
    //     robot.clickOn("#pagesField5").write(invalidPages5);
    //     robot.clickOn("#genreField5").write(adGenre5);
    //     robot.clickOn("#authorField5").write(adAuthor5);
    //     robot.clickOn("#yearField5").write(adYear5);
    //     robot.clickOn("#makeAd23");
    //     robot.clickOn(850, 390);
    //     robot.clickOn("#pagesField5").write(validPages5);
    //     robot.clickOn("#makeAd23");
    // }
}
