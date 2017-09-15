package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(app.properties.getProperty("web.groupName")));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      File photo = new File(app.properties.getProperty("web.contactPhoto"));
      app.contact().create
              (new ContactData().withFirstname(app.properties.getProperty("web.contactFirstname"))
                      .withLastname(app.properties.getProperty("web.contactLastname"))
                      .withEmail(app.properties.getProperty("web.contactEmail"))
                      .withPhoneHome(app.properties.getProperty("web.contactHome"))
                      .withPhoneMobile(app.properties.getProperty("web.contactMobile"))
                      .withPhoneWork(app.properties.getProperty("web.contactWork"))
                      .withAddress(app.properties.getProperty("web.contactAddress"))
                      .withEmail2(app.properties.getProperty("web.contactEmail2"))
                      .withEmail3(app.properties.getProperty("web.contactEmail3"))
                      .withPhoto(photo), true);
    }
  }

  @Test
  public void testAddContactToGroup() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    GroupData groupbefore = groups.iterator().next();
    ContactData contact = before.iterator().next().inGroup(groupbefore);
    app.goTo().homePage();
    app.contact().selectContactById(contact.getId());
    app.contact().chooseGroup(contact);
    app.contact().addContacttoGroup();
    Assert.assertTrue(app.contact().isElementPresent(By.id(String.valueOf(contact.getId()))));
    GroupData groupafter = app.db().groups().iterator().next();
    Contacts dbContacts = groupafter.getContacts();
    Contacts uiContacts = app.contact().all();
    assertThat(uiContacts, equalTo(dbContacts.stream()
            .map((g) ->  new ContactData().withId(g.getId()).withFirstname(g.getFirstname())
                    .withLastname(g.getLastname()).withAddress(g.getAddress()))
            .collect(Collectors.toSet())));
  }
}