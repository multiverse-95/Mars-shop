package rest.model.entity;

import java.io.Serializable;

   
//JPA (Begin)
import javax.persistence.*;
//JPA (End)   
  

@Entity
@Table(name = "\"students\"")
public class EStudent implements Serializable
{
  @Id
  @Column(name = "\"id\"")
  private long id;
  @Column(name = "\"firstname\"")
  private String name;
  
  public long getId() {
    return id;
  }
  
  public void setId(long sID) {
    id = sID;
  }  
  
  public String getName() {
    return name;
  }
  
  public void setName(String sName) {
    name = sName;
  }
}