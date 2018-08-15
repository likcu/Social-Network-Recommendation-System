package edu.neu.ccs.cs5004;

import java.util.Objects;

/**
 * Represents the common properties of a user.
 */
public class User {

  private Integer nodeId;
  private String date;
  private String gender;
  private Integer age;
  private String city;
  private Integer follower;

  /**
   * Creates a user.
   *
   * @param nodeId   the id of a user
   * @param date     the created date of a user
   * @param gender   the gender of a user
   * @param age      the age of a user
   * @param city     the city of a user
   * @param follower the number of follower of a user
   */
  public User(Integer nodeId, String date, String gender,
              Integer age, String city, Integer follower) {
    this.nodeId = nodeId;
    this.date = date;
    this.gender = gender;
    this.age = age;
    this.city = city;
    this.follower = follower;
  }

  /**
   * Getter for property 'nodeId'.
   *
   * @return Value for property 'nodeId'
   */
  public Integer getNodeId() {
    return nodeId;
  }

  /**
   * Setter for property 'nodeId'.
   *
   * @param nodeId for property 'nodeId'
   */
  public void setNodeId(Integer nodeId) {
    this.nodeId = nodeId;
  }

  /**
   * Getter for property 'date'.
   *
   * @return Value for property 'date'
   */
  public String getDate() {
    return date;
  }

  /**
   * Setter for property 'date'.
   *
   * @param date for property 'date'
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * Getter for property 'gender'.
   *
   * @return Value for property 'gender'
   */
  public String getGender() {
    return gender;
  }

  /**
   * Setter for property 'gender'.
   *
   * @param gender for property 'gender'
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * Getter for property 'age'.
   *
   * @return Value for property 'age'
   */
  public Integer getAge() {
    return age;
  }

  /**
   * Setter for property 'age'.
   *
   * @param age for property 'age'
   */
  public void setAge(Integer age) {
    this.age = age;
  }

  /**
   * Getter for property 'city'.
   *
   * @return Value for property 'city'
   */
  public String getCity() {
    return city;
  }

  /**
   * Setter for property 'city'.
   *
   * @param city for property 'city'
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Getter for property 'follower'.
   *
   * @return Value for property 'follower'
   */
  public Integer getFollower() {
    return follower;
  }

  /**
   * Setter for property 'follower'.
   *
   * @param follower for property 'follower'
   */
  public void setFollower(Integer follower) {
    this.follower = follower;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof User)) {
      return false;
    }
    User user = (User) object;
    return Objects.equals(getNodeId(), user.getNodeId())
        && Objects.equals(getDate(), user.getDate())
        && Objects.equals(getGender(), user.getGender())
        && Objects.equals(getAge(), user.getAge())
        && Objects.equals(getCity(), user.getCity())
        && Objects.equals(getFollower(), user.getFollower());
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public int hashCode() {

    return Objects.hash(getNodeId(), getDate(), getGender(), getAge(), getCity(), getFollower());
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public String toString() {
    return nodeId.toString();
  }
}
