package edu.neu.ccs.cs5004;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RelationTest {

  private User user;
  private User user2;
  private Relation relation;
  private Relation sameRefRelation;
  private Relation sameStateAsRelation;
  private Relation diffRelation;
  private Relation yetAnotherRelation;
  private Relation nullRelation = null;

  @Before
  public void setUp() throws Exception {

    user = new User(4, "2/2/1995", "M", 22, "Seattle", 2);
    user2 = new User(3, "2/2/1995", "M", 22, "Seattle", 2);
    relation = new Relation(new HashMap<>(), new HashMap<>());
    relation.getFollowingRelation().put(user, new ArrayList<>());
    relation.getFollowingRelation().get(user).add(user2);
    relation.getFollowerRelation().put(user2, new ArrayList<>());
    relation.getFollowerRelation().get(user2).add(user);
    diffRelation = new Relation(new HashMap<>(), new HashMap<>());
    diffRelation.getFollowingRelation().put(user, new ArrayList<>());
    sameRefRelation = relation;
    sameStateAsRelation = new Relation(new HashMap<>(), new HashMap<>());
    sameStateAsRelation.getFollowingRelation().put(user, new ArrayList<>());
    sameStateAsRelation.getFollowingRelation().get(user).add(user2);
    sameStateAsRelation.getFollowerRelation().put(user2, new ArrayList<>());
    sameStateAsRelation.getFollowerRelation().get(user2).add(user);
    yetAnotherRelation = new Relation(new HashMap<>(), new HashMap<>());
    yetAnotherRelation.getFollowingRelation().put(user, new ArrayList<>());
    yetAnotherRelation.getFollowingRelation().get(user).add(user2);
    yetAnotherRelation.getFollowerRelation().put(user2, new ArrayList<>());
    yetAnotherRelation.getFollowerRelation().get(user2).add(user);
  }

  @Test
  public void getFollowingRelation() {
    Map<User, List<User>> map = new HashMap<>();
    map.put(user, new ArrayList<>());
    map.get(user).add(user2);
    Assert.assertEquals(map, relation.getFollowingRelation());
  }

  @Test
  public void getFollowerRelation() {
    Map<User, List<User>> map = new HashMap<>();
    map.put(user2, new ArrayList<>());
    map.get(user2).add(user);
    Assert.assertEquals(map, relation.getFollowerRelation());
  }

  @Test
  public void equals() {

    Assert.assertTrue(relation.equals(relation));
    Assert.assertTrue(relation.equals(sameRefRelation));
    Assert.assertTrue(relation.equals(sameStateAsRelation));
    Assert.assertTrue(sameStateAsRelation.equals(relation));
    Assert.assertEquals(relation.equals(sameStateAsRelation) && sameStateAsRelation.equals(yetAnotherRelation), yetAnotherRelation.equals(relation));
    Assert.assertFalse(relation.equals(diffRelation));
    Assert.assertFalse(relation.equals(nullRelation));
  }

  @Test
  public void testhashCode() {

    Assert.assertEquals(relation.equals(sameRefRelation), relation.hashCode() == sameRefRelation.hashCode());
    Assert.assertEquals(relation.equals(sameStateAsRelation), relation.hashCode() == sameStateAsRelation.hashCode());
  }
}