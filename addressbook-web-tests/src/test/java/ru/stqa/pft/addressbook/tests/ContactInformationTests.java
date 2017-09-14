package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase {

  public static String cleaned(String data) {
    return data.replaceAll("\\s", "").replaceAll("W:", "")
            .replaceAll("H:", "").replaceAll("M:", "")
            .replaceAll("Memberof:test0", "").replaceAll("Memberof:test1", "").replaceAll("Memberof:test5", "");
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
              (new ContactData().withFirstname(app.properties.getProperty("web.contactFirstname"))
                              .withLastname(app.properties.getProperty("web.contactLastname"))
                              .withEmail(app.properties.getProperty("web.contactEmail"))
                              .withPhoneHome(app.properties.getProperty("web.contactHome"))
                              .withPhoneMobile(app.properties.getProperty("web.contactMobile"))
                              .withPhoneWork(app.properties.getProperty("web.contactWork"))
                              .withAddress(app.properties.getProperty("web.contactAddress"))
                              .withEmail2(app.properties.getProperty("web.contactEmail2"))
                              .withEmail3(app.properties.getProperty("web.contactEmail3")),
                      true);
    }
  }

  @Test
  public void testContactInformation() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromInfForm = app.contact().infoFromInfForm(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(merge1(contactInfoFromInfForm), equalTo(merge2(contactInfoFromEditForm)));
  }
  private String merge1(ContactData contact) {
    return Arrays.asList(contact.getAllName())
            .stream().filter((s) -> !s.equals("")).map(ContactInformationTests::cleaned).collect(Collectors.joining(""));
  }
  private String merge2(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAddress(),
            contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork()
            , contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream()
            .filter((c) -> !c.equals("")).map(ContactInformationTests::cleaned).collect(Collectors.joining(""));
  }
}

