package ui;

import java.util.List;
import java.util.stream.Collectors;

import core.AdSorter;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import json.Ad;
import json.User;

/**
 * Controller for the profile page. 
 */
public class ProfileController extends AbstractController {

  @FXML
  private ListView<Ad> listActiveAds;
  @FXML
  private ListView<Ad> listBoughtAds;
  @FXML
  private ListView<Ad> listSoldAds;
  @FXML
  private Label fullName;
  @FXML
  private Label username;
  @FXML
  private Label money;

  private User user;

  private Ad ad;

  public void setUser(User user) {
    this.user = user;
    super.setUser(user);
  }

  public void setFilename(String filename) {
    super.setFilename(filename);
  }

  public void setAd(Ad ad) {
    this.ad = ad;
    super.setAd(ad);
  }

  public void setInfo(User user) {
    this.fullName.setText("Full name: " + user.getFullname());
    this.username.setText("Username: @" + user.getUsername());
    int sum = 0;
    String word = "earned";
    List<Ad> allAds = new AdSorter()
        .getListofAdsFromId(this.user.getMyAds(), dataAccess.getAllAdsInFile());
    List<Ad> sold = new AdSorter(allAds).sortAds(ad -> ad.getIsSold() == true);
    List<Ad> boughtAds = new AdSorter()
        .getListofAdsFromId(this.user.getBoughtAds(), 
        dataAccess.getAllAdsInFile());
    for (int i = 0; i < sold.size(); i++) {
      sum += sold.get(i).getProduct().getPrice();
    }
    for (int j = 0; j < boughtAds.size(); j++) {
      sum -= boughtAds.get(j).getProduct().getPrice();
    }
    if (sum < 0) {
      word = "spent";
      sum *= (-1);
    }
    this.money.setText("Total money " + word + ": " + sum);
  }

  /**
   * MEthod for displaying the ads you own. 
   * Bought ads, sold ads and active listed ads. 
   */
  public void setDisplayAds() {
    List<Ad> ads = dataAccess.getAllAdsInFile();
    AdSorter adSorter = new AdSorter(ads);
    AdSorter sorterBoughtorSold =
        new AdSorter(adSorter.getListofAdsFromId(this.user.getMyAds(), ads));
    listActiveAds.getItems().addAll(sorterBoughtorSold.sortAds(ad -> ad.getIsSold() == false));
    listSoldAds.getItems().addAll(sorterBoughtorSold.sortAds(ad -> ad.getIsSold() == true));
    listBoughtAds.getItems().addAll(adSorter.getListofAdsFromId(this.user.getBoughtAds(), ads));
  }

  @FXML
  private void goBack() {
    Stage stage = (Stage) listBoughtAds.getScene().getWindow();
    super.setScene(Controllers.APP, stage, getDataAccess());
  }

  /**
   * A method that makes it possible to click on an ad in the listview. When clicked, the user
   * should see a preview of the ad, and will have a choice to buy the product. The user can also
   * return to the home page.
   *
   * @param event when the user clicks on an ad
   */
  @FXML
  private void displaySelected(MouseEvent event) {
    List<Ad> allAds = dataAccess.getAllAdsInFile();
    AdSorter adSorter = new AdSorter(allAds);
    AdSorter sorterBoughtorSold =
        new AdSorter(adSorter.getListofAdsFromId(this.user.getMyAds(), allAds));
    List<Ad> yourAds = sorterBoughtorSold.getAds();
    super.setPreviousController(this);
    for (int i = 0; i < yourAds.size(); i++) {
      if (this.listActiveAds.getSelectionModel().isSelected(i)) {
        ad = this.listActiveAds.getSelectionModel().getSelectedItem();
        super.setAd(ad);
        Stage stage = (Stage) listActiveAds.getScene().getWindow();
        super.setScene(Controllers.DISPLAYAD, stage, getDataAccess());
      } else if (this.listSoldAds.getSelectionModel().isSelected(i)) {
        ad = this.listSoldAds.getSelectionModel().getSelectedItem();
        super.setAd(ad);
        Stage stage = (Stage) listSoldAds.getScene().getWindow();
        super.setScene(Controllers.DISPLAYAD, stage, getDataAccess());
      }
    }
    List<Ad> boughtAds = adSorter.getListofAdsFromId(this.user.getBoughtAds(), allAds);
    for (int i = 0; i < boughtAds.size(); i++) {
      if (this.listBoughtAds.getSelectionModel().isSelected(i)) {
        ad = this.listBoughtAds.getSelectionModel().getSelectedItem();
        super.setAd(ad);
        Stage stage = (Stage) listBoughtAds.getScene().getWindow();
        super.setScene(Controllers.DISPLAYAD, stage, getDataAccess());
      }
    }
  }
}
