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

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() ==0){
      app.group().create(new GroupData().withName("test5"));
    }
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create
              (new ContactData().withFirstname("nowa").withLastname("nowa").withEmail("lucja@gmail.com").withGroup("test5"),
                      true);}
  }

  @Test
  public void testContactPhone(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhone(), equalTo(mergePhones(contactInfoFromEditForm)));

  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneHome(),contact.getPhoneMobile(),contact.getPhoneWork())
            .stream().filter((s) ->!s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
  }


  public static String cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
