package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.jayway.restassured.RestAssured;

import org.testng.SkipException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {

  public void skipIfNotFixed(int issueId) throws MalformedURLException, RemoteException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues/" +issueId +".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    String nameState =  parsed.getAsJsonObject().getAsJsonArray("issues").get(0).getAsJsonObject().get("state_name").getAsString();
    if (nameState.equals("Closed")) {
      return false;
    }
    if (nameState.equals("Resolved")) {
      return false;
    }
    return true;
  }

}