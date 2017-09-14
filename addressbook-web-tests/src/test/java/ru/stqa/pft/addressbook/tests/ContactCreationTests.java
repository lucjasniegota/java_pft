package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(app.properties.getProperty("web.groupName")));
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsfromJSON() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("web.contactFileJSON"))));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
    }.getType());
    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactsfromXML() throws IOException {
    Groups groups = app.db().groups();
    List<Object[]> list = new ArrayList<Object[]>();
   BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("web.contactFileXML"))));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }
  @DataProvider
  public Iterator<Object[]> validContactsfromCSV() throws IOException {
    Groups groups = app.db().groups();
    List<Object[]> list = new ArrayList<Object[]>();
BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("web.contactFileCSV"))));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1]).withEmail(split[2])
              .withPhoneHome(split[3]).withPhoneMobile(split[4]).withPhoneWork(split[5])
              .withEmail2(split[6]).withEmail3(split[7]).withPhoto(new File(split[8]))
              .withAddress(split[9]).inGroup(groups.iterator().next())});
      line = reader.readLine();
    }
    return list.iterator();}


  @Test(dataProvider = "validContactsfromCSV")
  public void testContactCreation(ContactData contact) {
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}




