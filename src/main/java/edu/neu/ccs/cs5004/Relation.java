package edu.neu.ccs.cs5004;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the common properties of a relation.
 */
public class Relation {

  private Map<User, List<User>> followingRelation;
  private Map<User, List<User>> followerRelation;

  /**
   * Creates a relation.
   *
   * @param followingRelation the following relation of a user
   * @param followerRelation  the follower relation of a user
   */
  public Relation(Map<User, List<User>> followingRelation, Map<User, List<User>> followerRelation) {
    this.followingRelation = followingRelation;
    this.followerRelation = followerRelation;
  }

  /**
   * Getter for property 'followingRelation'.
   *
   * @return Value for property 'followingRelation'
   */
  public Map<User, List<User>> getFollowingRelation() {
    return followingRelation;
  }

  /**
   * Getter for property 'followerRelation'.
   *
   * @return Value for property 'followerRelation'
   */
  public Map<User, List<User>> getFollowerRelation() {
    return followerRelation;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Relation)) {
      return false;
    }
    Relation relation = (Relation) object;
    return Objects.equals(getFollowingRelation(), relation.getFollowingRelation())
        && Objects.equals(getFollowerRelation(), relation.getFollowerRelation());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getFollowingRelation(), getFollowerRelation());
  }
}
