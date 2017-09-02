package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() ==0){
      app.group().create(new GroupData().withName("test5"));
    }
  }
  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line!= null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1]).withEmail(split[2])
              .withPhoneHome(split[3]).withPhoneMobile(split[4]).withPhoneWork(split[5])
              .withEmail2(split[6]).withEmail3(split[7]).withPhoto(new File(split[8]))
              .withAddress(split[9]).withGroup(split[10])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact){
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() +1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded( contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }




}




