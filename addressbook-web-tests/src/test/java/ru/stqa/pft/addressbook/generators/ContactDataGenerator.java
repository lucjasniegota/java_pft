package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {


  public static void main (String [] args) throws IOException {


    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);
    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }
  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for(ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n" , contact.getFirstname(), contact.getLastname(),
              contact.getEmail(),
              contact.getEmail2(),
              contact.getEmail3(), contact.getAddress(), contact.getGroup(), contact.getPhoneHome(),
              contact.getPhoneMobile(),  contact.getPhoneWork(), contact.getPhoto()));

    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++ ){
      File photo = new File("src/test/resources/hiszp.png");
      contacts.add(new ContactData().withFirstname(String.format("Firstname %s", i))
              .withLastname(String.format("Lastname %s", i)).withEmail(String.format("email %s", i))
              .withEmail2(String.format("email2 %s", i)).withEmail3(String.format("email3 %s", i))
              .withPhoneHome(String.format("home %s", i)).withPhoneWork(String.format("work %s", i))
              .withPhoneMobile(String.format("mobile %s", i)).withAddress(String.format("adres %s", i)).withGroup("test5")
              .withPhoto(photo));

    }    return contacts;
}}
