package dataaccess;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import dataaccess.RemoteGoodsListAccess;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import json.User;



public class RemoteGoodslistAccessTest {

  private WireMockServer wireMockServer;

  private RemoteGoodsListAccess remoteGoodsListAccess;


  @Test
  public void TestGetUsers() throws URISyntaxException {
    this.wireMockServer = new WireMockServer();
    this.wireMockServer.start();
    WireMock.configureFor("localhost", 8080);
    this.remoteGoodsListAccess = new RemoteGoodsListAccess(
      new URI("http://localhost:" + wireMockServer.port() + "/users"), 
      "ui/src/test/resources/dataaccess/RemoteAccessTest.json");
    stubFor(get(urlEqualTo("/users"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"users\": [ {\"username\": \"johangutt\", \"password\": \"stavanger\", \"fullname\": \"Johakn\", \"myAds\": [], \"boughtAds\": []} ]}")
    )
    );
    assertEquals(2, remoteGoodsListAccess.getAllUsers());
    this.wireMockServer.stop();
  }





}
