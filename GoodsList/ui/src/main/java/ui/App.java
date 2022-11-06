package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.User;

/**
 * JavaFX App.
*/
public class App extends Application {

  private static Stage mainStage;

  /**
   * Start method for the app. We have added a method for setting the filepath we
   * are using in
   * controller. If the param for setFilePath is false, we are running the app
   * normally, and true
   * means its a test.
   *
   * @param stage stage
   * @throws IOException Exception
   */
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

  /**
   * launching the app. 
   *
   * @param args args
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * return the current stage.
   *
   * @return stage
   */
  public Stage getStage() {
    return mainStage;
  }

  /**
   * Method for bringing the userinfo and filepath boolean tho the new controller
   * and fxml file.
   * Also changes the fxml scene.
   *
   * @param user user
   * @throws IOException Exception
   */
  public void bringUserInfo(User user) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("App.fxml"));
    Parent parent = fxmlLoader.load();
    AppController appController = fxmlLoader.getController();
    appController.setFilepath(false);
    appController.setUsername(user);
    appController.setChoiceBox();
    if (mainStage != null) {
      mainStage.getScene().setRoot(parent);
    }
  }

  
  /**
   * method for setting the new stage.
   *
   * @param Stage stage
   */
  private static void setMainStage(Stage mainStage) {
    App.mainStage = mainStage;
  }
}
