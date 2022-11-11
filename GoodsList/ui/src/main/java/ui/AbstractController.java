package ui;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import json.Ad;
import json.User;

/**
 * This is an abstract class that handles setting scenes and
 * setting AnchorPane. The abstract class is used to minimize copies
 * of methods.
 */
public abstract class AbstractController {

  private User user;
  private String filename;
  private Ad ad;

  /**
   * Enum for controllers with their accosiated fxml-files.
   */
  public enum Controllers {
    LOGIN("Login.fxml", new LoginController()),
    APP("App.fxml", new AppController()),
    CATEGORIES("Categories.fxml", new CategoriesController()),
    ELECTRONICS("Electronics.fxml", new ElectronicsController()),
    PROPERTY("Property.fxml", new PropertyController()),
    CLOTHING("Clothing.fxml", new ClothingController()),
    BOOKS("Books.fxml", new BooksController());

    private final String fxml;
    private final AbstractController abstractController;

    Controllers(String fxml, AbstractController abstractController) {
      this.fxml = fxml;
      this.abstractController = abstractController;
    }

    public AbstractController getControllerInstance() {
      return this.abstractController;
    }

    public String getFxmlString() {
      return this.fxml;
    }
  }

  /**
   * Method for setting a scene and pass data between the controllers.
   *
   * @param type  of wanted scene. Only need to give wanted CONTROLLER type.
   * @param stage when user clicks on a button on existing scene.
   */
  public void setScene(Controllers type, Stage stage) {
    try {
      AbstractController controller = type.getControllerInstance();
      FXMLLoader loader = new FXMLLoader();
      loader.setController(controller);
      loader.setLocation(App.class.getResource(type.getFxmlString()));
      Parent parent = loader.load();
      if (controller instanceof AppController) {
        ((AppController) controller).setUsername(this.user);
        ((AppController) controller).setChoiceBox();
      } else if (controller instanceof CategoriesController) {
        ((CategoriesController) controller).setUser(user);
      } else if (controller instanceof ElectronicsController) {
        ((ElectronicsController) controller).setUser(user);
      } else if (controller instanceof BooksController) {
        ((BooksController) controller).setUser(user);
      }
      else if (controller instanceof ClothingController) {
        ((ClothingController) controller).setUser(user);
        ((ClothingController) controller).setChoiceBox();
      }
      else if (controller instanceof PropertyController) {
        ((PropertyController) controller).setUser(user);
      }
    
      Scene newScene = new Scene(parent);
      stage.setScene(newScene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getFilename() {
    return this.filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public Ad getAd() {
    return this.ad;
  }

  public void setAd(Ad ad) {
    this.ad = ad;
  }

  /**
   * Method that switches out existing AnchorPane with new AnchorPane.
   * Our HomePage.fxml contains Sidebar and Header. To make navigation
   * smoother we have determined to just switch out the content. This method
   * does that by getting the new pane from the given type.
   *
   * @param type of wanted scene. Only need to give wanted CONTROLLER type.
   * @param pane that will be switched out with new AnchorPane.
   */
  // public void setAnchorPane(
  // Controllers type, AnchorPane pane, SalaryCheckerAccess dataAccess) {
  // try {
  // AbstractController controller = type.getControllerInstance();
  // FXMLLoader loader = new FXMLLoader();
  // loader.setLocation(SalaryCheckerApp.class.getResource(type.getFxmlString()));
  // loader.setController(controller);
  // controller.setDataAccess(dataAccess);
  // AnchorPane anchorPane = loader.load();
  // pane.getChildren().clear();
  // pane.getChildren().setAll(anchorPane);
  // if (controller instanceof ProfileController) {
  // ((ProfileController) controller).loadProfileInfo();
  // } else if (controller instanceof SettingsController) {
  // ((SettingsController) controller).loadSettingsInfo();
  // } else if (controller instanceof SalariesController) {
  // ((SalariesController) controller).loadTableView();
  // } else if (controller instanceof AdminUserOverviewController) {
  // ((AdminUserOverviewController) controller).loadTableView();
  // } else if (controller instanceof CreateUserController) {
  // ((CreateUserController) controller).loadUserAndAccount();
  // }
  // } catch (IOException e) {
  // e.printStackTrace();
  // }

  // }

  public static void main(String[] args) {
    System.out.println(Controllers.APP.getControllerInstance());
  }

}
