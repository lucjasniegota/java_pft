package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
                              .withGroup(app.properties.getProperty("web.groupName"))
                              .withPhoneHome(app.properties.getProperty("web.contactHome"))
                              .withPhoneMobile(app.properties.getProperty("web.contactMobile"))
                              .withPhoneWork(app.properties.getProperty("web.contactWork"))
                              .withAddress(app.properties.getProperty("web.contactAddress"))
                              .withEmail2(app.properties.getProperty("web.contactEmail2"))
                              .withEmail3(app.properties.getProperty("web.contactEmail3"))
                              .withPhoto(photo),
                      true);
    }
  }
  @Test
  public void testContactModification() {
    File photoModify = new File(app.properties.getProperty("web.contactPhotoModify"));
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact =
            new ContactData().withId(modifiedContact.getId()).withFirstname(app.properties.getProperty("web.contactFirstnameModify"))
                    .withLastname(app.properties.getProperty("web.contactLastnameModify"))
                    .withEmail(app.properties.getProperty("web.contactEmailModify"))
                    .withGroup(app.properties.getProperty("web.groupNameModify"))
                    .withPhoneHome(app.properties.getProperty("web.contactHomeModify"))
                    .withPhoneMobile(app.properties.getProperty("web.contactMobileModify"))
                    .withPhoneWork(app.properties.getProperty("web.contactWorkModify"))
                    .withAddress(app.properties.getProperty("web.contactAddressModify"))
                    .withEmail2(app.properties.getProperty("web.contactEmail2Modify"))
                    .withEmail3(app.properties.getProperty("web.contactEmail3Modify"))
                    .withPhoto(photoModify);
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
