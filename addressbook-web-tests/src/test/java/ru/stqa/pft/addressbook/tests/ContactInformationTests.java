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
    ContactData contact = app.contact().all().iterator().next();;
    ContactData contactInfoFromInfForm = app.contact().infoFromInfForm(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(merge1(contactInfoFromInfForm), equalTo(merge2(contactInfoFromEditForm)));

  }
  private String merge1(ContactData contact) {
   return Arrays.asList(contact.getAllName())
         .stream().filter((s) ->!s.equals("")).map(ContactInformationTests::cleaned).collect(Collectors.joining(""));


  }
  private String merge2(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getLastname(),contact.getAddress(),
            contact.getPhoneHome(),contact.getPhoneMobile(),contact.getPhoneWork()
            ,contact.getEmail(),contact.getEmail2(),contact.getEmail3()).stream()
            .filter((c) ->!c.equals("")).map(ContactInformationTests::cleaned).collect(Collectors.joining(""));

  }

  public static String cleaned (String data){
    return data.replaceAll("\\s","").replaceAll("[W:]","")
            .replaceAll("[H:]","").replaceAll("[M:]","");
  }
}

