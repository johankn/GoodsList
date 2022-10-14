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

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
        Parent parent = fxmlLoader.load();
        LoginController li= fxmlLoader.getController();
        li.setFilepath(false);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getStage() {
        return mainStage;
    }

    //changes scene to the new homescene for given loginUser
    public void setHomePage(String fxml) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(fxml));
        mainStage.getScene().setRoot(parent);
    }

    public void bringUserInfo(User user) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent parent = fxmlLoader.load();
        AppController appController = fxmlLoader.getController();
        appController.setUsername(user);
        appController.setChoiceBox();
        try {
            if (!mainStage.equals(null)) {
                mainStage.getScene().setRoot(parent);
            }
        } catch (NullPointerException e) {
            // TODO: handle exception
        }
    }
}