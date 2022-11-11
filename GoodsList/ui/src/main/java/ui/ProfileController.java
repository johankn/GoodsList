package ui;

import java.util.List;

import core.AdSorter;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
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

  public void setUser(User user) {
    this.user = user;
    super.setUser(user);
  }

  public void setFilename(String filename) {
    this.filename = filename;
    super.setFilename(filename);
  }

  public void setDisplayAds() {
    List<Ad> ads = new FileOperator().getAllAdsInFile(filename);
    AdSorter adSorter = new AdSorter(ads);
    //missing to add Ads to listviews (activeAds, boughtAds, soldAds).
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

}
