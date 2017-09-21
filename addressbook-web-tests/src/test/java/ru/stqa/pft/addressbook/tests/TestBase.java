package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {
  Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);
  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {

    app.stop();
  }

  @BeforeMethod
  public void logTestStar(Method m, Object[] p) {
    logger.info("start test " + m.getName() + " par " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m, Object[] p) {
    logger.info("start test " + m.getName() + " par " + Arrays.asList(p));
  }

  public void verifyGroupListInUI() {
    Groups dbGroups = app.db().groups();
    Groups uiGroups = app.group().all();
 if (Boolean.getBoolean("verifyUI")) {
   assertThat(uiGroups, equalTo(dbGroups.stream()
           .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
           .collect(Collectors.toSet())));

 }}
 public void verifyContactListInUI() {
    Contacts dbContacts = app.db().contacts();
    Contacts uiContacts = app.contact().all();
 if (Boolean.getBoolean("verifyUI")) {
   assertThat(uiContacts, equalTo(dbContacts.stream()
           .map((g) -> new ContactData().withId(g.getId()).withFirstname(g.getFirstname())
                   .withLastname(g.getLastname()))
           .collect(Collectors.toSet())));
 }

  }
}