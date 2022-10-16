package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import core.User;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage mainStage;

    
    /** 
     * Start method for the app. We have added a method for setting the filepath we are using in controller.
     * If the param for setFilePath is false, we are running the app  normally, and true means its a test. 
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
        Parent parent = fxmlLoader.load();
        LoginController li = fxmlLoader.getController();
        li.setFilepath(false);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    
    /** 
     * Launching the app
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    
    /** 
     * @return Stage
     */
    public Stage getStage() {
        return mainStage;
    }

    // changes scene to the new homescene for given loginUser
    public void setHomePage(String fxml) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxml));
        mainStage.getScene().setRoot(parent);
    }

    
    /** 
     * Method for bringing the userinfo and filepath boolean tho the new controller and fxml file. 
     * Also changes the fxml scene. 
     * @param user
     * @throws IOException
     */
    public void bringUserInfo(User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent parent = fxmlLoader.load();
        AppController appController = fxmlLoader.getController();
        appController.setUsername(user);
        appController.setChoiceBox();
        appController.setFilepath(false);
        if (mainStage != null) {
            mainStage.getScene().setRoot(parent);
        }
    }
}