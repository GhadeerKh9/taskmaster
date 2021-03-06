package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the TaskClass type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "TaskClasses")
@Index(name = "byTeam", fields = {"teamID","title"})
public final class TaskClass implements Model {
  public static final QueryField ID = field("TaskClass", "id");
  public static final QueryField TEAM_ID = field("TaskClass", "teamID");
  public static final QueryField TITLE = field("TaskClass", "title");
  public static final QueryField BODY = field("TaskClass", "body");
  public static final QueryField STATE = field("TaskClass", "state");
  public static final QueryField IMG = field("TaskClass", "img");
  public static final QueryField LAT = field("TaskClass", "lat");
  public static final QueryField LON = field("TaskClass", "lon");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String teamID;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String") String body;
  private final @ModelField(targetType="String") String state;
  private final @ModelField(targetType="String") String img;
  private final @ModelField(targetType="String") String lat;
  private final @ModelField(targetType="String") String lon;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTeamId() {
      return teamID;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getBody() {
      return body;
  }
  
  public String getState() {
      return state;
  }
  
  public String getImg() {
      return img;
  }
  
  public String getLat() {
      return lat;
  }
  
  public String getLon() {
      return lon;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private TaskClass(String id, String teamID, String title, String body, String state, String img, String lat, String lon) {
    this.id = id;
    this.teamID = teamID;
    this.title = title;
    this.body = body;
    this.state = state;
    this.img = img;
    this.lat = lat;
    this.lon = lon;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      TaskClass taskClass = (TaskClass) obj;
      return ObjectsCompat.equals(getId(), taskClass.getId()) &&
              ObjectsCompat.equals(getTeamId(), taskClass.getTeamId()) &&
              ObjectsCompat.equals(getTitle(), taskClass.getTitle()) &&
              ObjectsCompat.equals(getBody(), taskClass.getBody()) &&
              ObjectsCompat.equals(getState(), taskClass.getState()) &&
              ObjectsCompat.equals(getImg(), taskClass.getImg()) &&
              ObjectsCompat.equals(getLat(), taskClass.getLat()) &&
              ObjectsCompat.equals(getLon(), taskClass.getLon()) &&
              ObjectsCompat.equals(getCreatedAt(), taskClass.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), taskClass.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTeamId())
      .append(getTitle())
      .append(getBody())
      .append(getState())
      .append(getImg())
      .append(getLat())
      .append(getLon())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("TaskClass {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("teamID=" + String.valueOf(getTeamId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("state=" + String.valueOf(getState()) + ", ")
      .append("img=" + String.valueOf(getImg()) + ", ")
      .append("lat=" + String.valueOf(getLat()) + ", ")
      .append("lon=" + String.valueOf(getLon()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TeamIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static TaskClass justId(String id) {
    return new TaskClass(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      teamID,
      title,
      body,
      state,
      img,
      lat,
      lon);
  }
  public interface TeamIdStep {
    TitleStep teamId(String teamId);
  }
  

  public interface TitleStep {
    BuildStep title(String title);
  }
  

  public interface BuildStep {
    TaskClass build();
    BuildStep id(String id);
    BuildStep body(String body);
    BuildStep state(String state);
    BuildStep img(String img);
    BuildStep lat(String lat);
    BuildStep lon(String lon);
  }
  

  public static class Builder implements TeamIdStep, TitleStep, BuildStep {
    private String id;
    private String teamID;
    private String title;
    private String body;
    private String state;
    private String img;
    private String lat;
    private String lon;
    @Override
     public TaskClass build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new TaskClass(
          id,
          teamID,
          title,
          body,
          state,
          img,
          lat,
          lon);
    }
    
    @Override
     public TitleStep teamId(String teamId) {
        Objects.requireNonNull(teamId);
        this.teamID = teamId;
        return this;
    }
    
    @Override
     public BuildStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep body(String body) {
        this.body = body;
        return this;
    }
    
    @Override
     public BuildStep state(String state) {
        this.state = state;
        return this;
    }
    
    @Override
     public BuildStep img(String img) {
        this.img = img;
        return this;
    }
    
    @Override
     public BuildStep lat(String lat) {
        this.lat = lat;
        return this;
    }
    
    @Override
     public BuildStep lon(String lon) {
        this.lon = lon;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String teamId, String title, String body, String state, String img, String lat, String lon) {
      super.id(id);
      super.teamId(teamId)
        .title(title)
        .body(body)
        .state(state)
        .img(img)
        .lat(lat)
        .lon(lon);
    }
    
    @Override
     public CopyOfBuilder teamId(String teamId) {
      return (CopyOfBuilder) super.teamId(teamId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder state(String state) {
      return (CopyOfBuilder) super.state(state);
    }
    
    @Override
     public CopyOfBuilder img(String img) {
      return (CopyOfBuilder) super.img(img);
    }
    
    @Override
     public CopyOfBuilder lat(String lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
    
    @Override
     public CopyOfBuilder lon(String lon) {
      return (CopyOfBuilder) super.lon(lon);
    }
  }
  
}
