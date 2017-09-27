package ru.stqa.pft.mantis.model;

public class Issue {
  private int id;
  private String description;
  private String summary;
  private Project project;
  private String status;
  private int idstatus;

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public String getSummary() {
    return summary;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public Project getProject() {
    return project;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }
  public String getStatus() {
    return status;
  }

  public Issue withStatus(String status) {
    this.status = status;
    return this;
  }
  public int getIdStatus() {
    return idstatus;
  }

  public Issue withIdStatus(int status) {
    this.idstatus = idstatus;
    return this;
  }
}
