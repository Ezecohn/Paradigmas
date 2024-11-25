package namedEntities;

import java.util.List;

public abstract class NamedEntity {

  private Category category;
  private List<String> topics; 
  private List<String> atributes;

  public NamedEntity(Category category, List<String> topics, List<String> atributes) {
    this.category = category;
    this.topics = topics;
    this.atributes = atributes;
  }

  public abstract String getName();

  public Category getCategory() {
    return this.category;
  }

  public List<String> getTopics() {
    return this.topics;
  }

  public List<String> getAtributes() {
    return this.atributes;
  }

}

//class Person extends Category {

class Person extends NamedEntity {
  private String name;

  public Person(String name, Category category, List<String> topics, List<String> attributes) {
    super(category, topics, attributes);
    this.name = name;
  }

  @Override
    public String getName() {
        return name;
    }
}

class Location extends NamedEntity {
  private String location;

  public Location(String location, Category category, List<String> topics, List<String> attributes) {
    super(category, topics, attributes);
    this.location = location;
  }

  @Override
    public String getName() {
        return location;
    }
}

class Organization extends NamedEntity {
  private String organizationName;

  public Organization(String organizationName, Category category, List<String> topics, List<String> attributes) {
    super(category, topics, attributes);
    this.organizationName = organizationName;
  }
  @Override
    public String getName() {
        return organizationName;
    }
}


class Other extends NamedEntity {
  private String otherDetail;

  public Other(String otherDetail, Category category, List<String> topics, List<String> attributes) {
    super(category, topics, attributes);
    this.otherDetail = otherDetail;
  }

  @Override
    public String getName() {
        return otherDetail;
    }
}

class Event extends NamedEntity {
  private String name;

  public Event (String name, Category category, List<String> topics, List<String> attributes) {
    super(category, topics, attributes);
    this.name = name;
  }

  @Override
    public String getName() {
        return name;
    }
}