package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(app.properties.getProperty("web.groupName")));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create
              (new ContactData().withFirstname(app.properties.getProperty("contactFirstname"))
                              .withLastname(app.properties.getProperty("web.contactLastname"))
                              .withEmail(app.properties.getProperty("web.contactEmail"))
                              .withGroup(app.properties.getProperty("web.groupName"))
                              .withPhoneHome(app.properties.getProperty("web.contactHome"))
                              .withPhoneMobile(app.properties.getProperty("web.contactMobile"))
                              .withPhoneWork(app.properties.getProperty("web.contactWork")),
                      true);
    }
  }

  @Test
  public void testContactPhone() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhone(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
            .stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
  }
}
