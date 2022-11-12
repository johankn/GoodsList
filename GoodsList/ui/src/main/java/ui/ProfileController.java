package ui;

import java.util.List;

import core.AdSorter;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import json.Ad;
import json.FileOperator;
import json.User;

public class ProfileController extends AbstractController {

  @FXML
  private ListView<Ad> listActiveAds;
  @FXML
  private ListView<Ad> listBoughtAds;
  @FXML
  private ListView<Ad> listSoldAds;

  private User user;

  private String filename;

  private Ad ad;

  public void setUser(User user) {
    this.user = user;
    super.setUser(user);
  }

  public void setFilename(String filename) {
    this.filename = filename;
    super.setFilename(filename);
  }

  public void setAd(Ad ad) {
    this.ad = ad;
    super.setAd(ad);
  }

  public void setDisplayAds() {
    List<Ad> ads = new FileOperator().getAllAdsInFile(filename);
    AdSorter adSorter = new AdSorter(ads);
    AdSorter sorterBoughtorSold = new AdSorter(adSorter.getListofAdsFromId(this.user.getMyAds(),
         ads));
    listActiveAds.getItems().addAll(sorterBoughtorSold.sortAds(ad -> ad.getIsSold() == false));
    listSoldAds.getItems().addAll(sorterBoughtorSold.sortAds(ad -> ad.getIsSold() == true));
    listBoughtAds.getItems().addAll(adSorter.getListofAdsFromId(this.user.getBoughtAds(), 
        ads));
  }

  @FXML
  private void goBack() {
    Stage stage = (Stage) listBoughtAds.getScene().getWindow();
    super.setScene(Controllers.APP, stage);
  }

    /**
   * A method that makes it possible to click on an ad in the listview. When
   * clicked, the user should see a preview of the ad, and will have a choice to
   * buy the product. The user can also return to the home page.
   * 
   * @param event when the user clicks on an ad
   */
  @FXML
  private void displaySelected(MouseEvent event) {
    List<Ad> allAds = new FileOperator().getAllAdsInFile(filename);
    AdSorter adSorter = new AdSorter(allAds);
    AdSorter sorterBoughtorSold = new AdSorter(adSorter.getListofAdsFromId(this.user.getMyAds(),
         allAds));
    List<Ad> yourAds = sorterBoughtorSold.getAds();
    for (int i = 0; i < yourAds.size(); i++) {
      if (this.listActiveAds.getSelectionModel().isSelected(i)) {
        ad = this.listActiveAds.getSelectionModel().getSelectedItem();
        super.setAd(ad);
        Stage stage = (Stage) listActiveAds.getScene().getWindow();
        super.setScene(Controllers.DISPLAYAD, stage);
      }
      else if (this.listSoldAds.getSelectionModel().isSelected(i)) {
        ad = this.listSoldAds.getSelectionModel().getSelectedItem();
        super.setAd(ad);
        Stage stage = (Stage) listSoldAds.getScene().getWindow();
        super.setScene(Controllers.DISPLAYAD, stage);
      }
    }
    List<Ad> boughtAds = adSorter.getListofAdsFromId(this.user.getBoughtAds(), allAds);
    for (int i = 0; i < boughtAds.size(); i++) {
      if (this.listBoughtAds.getSelectionModel().isSelected(i)) {
        ad = this.listBoughtAds.getSelectionModel().getSelectedItem();
        super.setAd(ad);
        Stage stage = (Stage) listBoughtAds.getScene().getWindow();
        super.setScene(Controllers.DISPLAYAD, stage);
      }
    }
  }
}
