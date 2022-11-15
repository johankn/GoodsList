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
  private boolean isTest;
  private Ad ad;
  private AbstractController previousController;

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
    BOOKS("Books.fxml", new BooksController()),
    VEHICLES("Vehicles.fxml", new VehiclesController()),
    PREVIEW("PreviewAd.fxml", new PreviewAdController()),
    DISPLAYAD("DisplayAd.fxml", new DisplayAdController()), 
    PROFILE("Profile.fxml", new ProfileController());

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
  public void setFilepathAbstract(boolean isTest) {
    this.isTest = isTest;
  }
  public void setPreviousController(AbstractController controller) {
    this.previousController = controller;
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
      // this.setUser(user);
      // this.setFilename(filename);
      System.out.println(controller.getClass().getSimpleName());
      if (controller instanceof AppController) {

        ((AppController) controller).setUsername(this.user);
        ((AppController) controller).setFilename(filename);
        ((AppController) controller).first();
      } 
      else if (controller instanceof CategoriesController) {
        ((CategoriesController) controller).setUser(user);
        ((CategoriesController) controller).setFilename(filename);
      } 
      else if (controller instanceof ElectronicsController) {
        ((ElectronicsController) controller).setUser(user);
        ((ElectronicsController) controller).setFilename(filename);
        if (this.previousController != null && 
            this.previousController instanceof PreviewAdController) {
          ((ElectronicsController) controller).setAd(ad);
          ((ElectronicsController) controller).setOldInfo();
        }

      } else if (controller instanceof CategoriesController) {
        ((CategoriesController) controller).setUser(user);

      } else if (controller instanceof ElectronicsController) {
        ((ElectronicsController) controller).setUser(user);

      } else if (controller instanceof BooksController) {
        ((BooksController) controller).setUser(user);
        ((BooksController) controller).setFilename(filename);
        if (this.previousController != null && 
            this.previousController instanceof PreviewAdController) {
          ((BooksController) controller).setAd(ad);
          ((BooksController) controller).setOldInfo();
        }
      }
      else if (controller instanceof ClothingController) {
        ((ClothingController) controller).setUser(user);
        ((ClothingController) controller).setChoiceBox();
        ((ClothingController) controller).setFilename(filename);
        if (this.previousController != null && 
            this.previousController instanceof PreviewAdController) {
          ((ClothingController) controller).setAd(ad);
          ((ClothingController) controller).setOldInfo();
        }
      }
      else if (controller instanceof PropertyController) {
        ((PropertyController) controller).setUser(user);
        ((PropertyController) controller).setFilename(filename);
        if (this.previousController != null && 
            this.previousController instanceof PreviewAdController) {
          ((PropertyController) controller).setAd(ad);
          ((PropertyController) controller).setOldInfo();
        }
      }
      else if (controller instanceof LoginController) {
        ((LoginController) controller).setFilepath(this.isTest);
      }

      else if (controller instanceof VehiclesController) {
        ((VehiclesController) controller).setUser(user);
        ((VehiclesController) controller).setChoiceBox();
        ((VehiclesController) controller).setFilename(filename);
        if (this.previousController != null && 
            this.previousController instanceof PreviewAdController) {
          ((VehiclesController) controller).setAd(ad);
          ((VehiclesController) controller).setOldInfo();
        }
      }
      else if (controller instanceof PreviewAdController) {
        ((PreviewAdController) controller).setUser(user);
        ((PreviewAdController) controller).setAd(ad);
        ((PreviewAdController) controller).setFilename(filename);
        ((PreviewAdController) controller).setPreview();
        ((PreviewAdController) controller).setPreviousController(previousController);
      }
      else if (controller instanceof DisplayAdController) {
        ((DisplayAdController) controller).setUser(user);
        ((DisplayAdController) controller).setAd(ad);
        ((DisplayAdController) controller).setPreviousController(this.previousController);
        ((DisplayAdController) controller).setFilename(filename);
        ((DisplayAdController) controller).setBuyPossible();
        ((DisplayAdController) controller).setInfo();
      }

      else if (controller instanceof ProfileController) {
        ((ProfileController) controller).setUser(user);
        ((ProfileController) controller).setAd(ad);
        ((ProfileController) controller).setFilename(filename);
        ((ProfileController) controller).setDisplayAds();
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



  // }

}
