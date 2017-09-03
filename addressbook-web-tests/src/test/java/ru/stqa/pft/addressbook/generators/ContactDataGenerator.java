package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;
  @Parameter(names = "-d", description = "Data format")
  public String format;
  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jcommander = new JCommander(generator);
    try {
      jcommander.parse(args);
    } catch (ParameterException ex) {
      jcommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {

    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCSF(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXML(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJSON(contacts, new File(file));
    } else {
      System.out.println("Nieznany format " + format);
    }
  }


  private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }

  }

  private void saveAsCSF(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)){
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
              contact.getEmail(), contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork(),
              contact.getEmail2(),
              contact.getEmail3(), contact.getPhoto(), contact.getAddress(), contact.getGroup()));
    }}
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      File photo = new File("src/test/resources/hiszp.png");
      contacts.add(new ContactData().withFirstname(String.format("Firstname%s", i))
              .withLastname(String.format("Lastname%s", i))
              .withEmail(String.format("email%s", i))
              .withPhoneHome(String.format("home%s", i))
              .withPhoneWork(String.format("work%s", i))
              .withPhoneMobile(String.format("mobile%s", i))
              .withEmail2(String.format("email2%s", i))
              .withEmail3(String.format("email3%s", i))
              .withPhoto(photo)
              .withAddress(String.format("adres%s", i))
              .withGroup("test5"));
    }
    return contacts;
  }
}
