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
        setMainStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
        Parent parent = fxmlLoader.load();
        LoginController li = fxmlLoader.getController();
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

    private static void setMainStage(Stage mainStage) {
        App.mainStage = mainStage;
    }
}