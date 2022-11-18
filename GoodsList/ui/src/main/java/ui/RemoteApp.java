package ui;

import dataaccess.RemoteGoodsListAccess;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class that launches remoteapplication. 
 */
public class RemoteApp extends Application {
  
  private static Stage mainStage;

  /**
   * Start method for the RemoteApp. We have added a method for setting the filepath we
   * are using in
   * controller. If the param for setFilePath is false, we are running the app
   * normally, and true.
   * means its a test.
   *
   * @param stage stage
   * @throws IOException Exception
   * @throws URISyntaxException exception
   */
  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    URI uri = new URI("http://localhost:8080/");
    setMainStage(stage);
    FXMLLoader fxmlLoader = new FXMLLoader();
    LoginController li = new LoginController();
    li.setFilepath(false);
    li.setDataAccess(new RemoteGoodsListAccess(uri, li.getFilename()));
    fxmlLoader.setController(li);
    fxmlLoader.setLocation(App.class.getResource("Login.fxml"));
    Parent parent = fxmlLoader.load();
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
   * method for setting the new stage.
   *
   * @param Stage stage
   */
  private static void setMainStage(Stage mainStage) {
    RemoteApp.mainStage = mainStage;
  }
  
}
