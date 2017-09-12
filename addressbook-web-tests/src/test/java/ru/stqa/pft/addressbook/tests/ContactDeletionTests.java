package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(app.properties.getProperty("web.groupName")));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create
              (new ContactData().withFirstname(app.properties.getProperty("web.contactFirstname"))
                              .withLastname(app.properties.getProperty("web.contactLastname"))
                              .withEmail(app.properties.getProperty("web.contactEmail"))
                              .withGroup(app.properties.getProperty("web.groupName"))
                              .withPhoneHome(app.properties.getProperty("web.contactHome")),
                      true);
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.goTo().homePage();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
