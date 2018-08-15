package edu.neu.ccs.cs5004;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RecommendationSystemTest {

  private User user;
  private User user2;
  private User user3;
  List<User> userList;
  List<User> userList2;
  private Relation relation;
  private Relation relation2;
  private RecommendationSystem recommendationSystem;
  private RecommendationSystem recommendationSystem2;
  private RecommendationSystem sameRefRecommendationSystem;
  private RecommendationSystem sameStateAsRecommendationSystem;
  private RecommendationSystem diffRecommendationSystem;
  private RecommendationSystem yetAnotherRecommendationSystem;
  private RecommendationSystem nullRecommendationSystem = null;

  @Before
  public void setUp() throws Exception {

    user = new User(4, "2/2/1995", "M", 22, "Seattle", 2000);
    user2 = new User(2, "2/2/1995", "M", 22, "Seattle", 2);
    user3 = new User(3, "2/2/1995", "M", 22, "Seattle", 2);
    userList = new ArrayList<>();
    userList2 = new ArrayList<>();
    userList.add(user);
    userList.add(user2);
    userList.add(user3);
    userList2.add(user);
    relation = new Relation(new HashMap<>(), new HashMap<>());
    relation2 = new Relation(new HashMap<>(), new HashMap<>());
    relation.getFollowingRelation().put(user, new ArrayList<>());
    relation.getFollowingRelation().get(user).add(user2);
    relation.getFollowerRelation().put(user, new ArrayList<>());
    relation.getFollowerRelation().put(user2, new ArrayList<>());
    relation.getFollowerRelation().get(user2).add(user);
    relation.getFollowingRelation().put(user2, new ArrayList<>());
    relation.getFollowingRelation().get(user2).add(user3);
    relation.getFollowingRelation().put(user3, new ArrayList<>());
    relation.getFollowerRelation().put(user3, new ArrayList<>());
    relation.getFollowerRelation().get(user3).add(user2);
    recommendationSystem = new RecommendationSystem(userList, relation);
    recommendationSystem2 = new RecommendationSystem(userList2, relation2);
    sameRefRecommendationSystem = recommendationSystem;
    sameStateAsRecommendationSystem = new RecommendationSystem(userList, relation);
    diffRecommendationSystem = new RecommendationSystem(userList, new Relation(new HashMap<>(), new HashMap<>()));
    yetAnotherRecommendationSystem = new RecommendationSystem(userList, relation);
  }

  @Test
  public void getUsers() {
    Assert.assertEquals(userList, recommendationSystem.getUsers());
  }

  @Test
  public void getRelation() {
    Assert.assertEquals(relation, recommendationSystem.getRelation());
  }

  @Test(expected = RuntimeException.class)
  public void recommend() {
    Map<User, List<User>> map = new HashMap<>();
    map.put(user, new ArrayList<>());
    map.get(user).add(user3);
    Assert.assertEquals(map, recommendationSystem.recommend("s", 1, 1));
    Map<User, List<User>> map2 = new HashMap<>();
    map2.put(user3, new ArrayList<>());
    map2.get(user3).add(user);
    Assert.assertEquals(map2, recommendationSystem.recommend("e", 1, 1));
    Map<User, List<User>> map3 = new HashMap<>();
    map3.put(user, new ArrayList<>());
    map3.get(user).add(user3);
    map3.put(user2, new ArrayList<>());
    map3.get(user2).add(user);
    Assert.assertEquals(map3, recommendationSystem.recommend("s", 2, 1));
    Map<User, List<User>> map4 = new HashMap<>();
    map4.put(user, new ArrayList<>());
    Assert.assertEquals(map4, recommendationSystem2.recommend(null, 1, 0));
    Assert.assertEquals(map4, recommendationSystem2.recommend("r", 1, 0));
    recommendationSystem.recommend(null, null, null);
  }

  @Test
  public void findById() {
    Assert.assertEquals(user, recommendationSystem.findById(4));
  }

  @Test
  public void sortById() {
    List<User> users = new ArrayList<>();
    users.add(user2);
    users.add(user3);
    users.add(user);
    recommendationSystem.sortById(recommendationSystem.getUsers());
    Assert.assertEquals(users, recommendationSystem.getUsers());
  }

  @Test
  public void equals() {
    Assert.assertTrue(recommendationSystem.equals(recommendationSystem));
    Assert.assertTrue(recommendationSystem.equals(sameRefRecommendationSystem));
    Assert.assertTrue(recommendationSystem.equals(sameStateAsRecommendationSystem));
    Assert.assertTrue(sameStateAsRecommendationSystem.equals(recommendationSystem));
    Assert.assertEquals(recommendationSystem.equals(sameStateAsRecommendationSystem) && sameStateAsRecommendationSystem.equals(yetAnotherRecommendationSystem), yetAnotherRecommendationSystem.equals(recommendationSystem));
    Assert.assertFalse(recommendationSystem.equals(diffRecommendationSystem));
    Assert.assertFalse(recommendationSystem.equals(nullRecommendationSystem));
  }

  @Test
  public void testhashCode() {
    Assert.assertEquals(recommendationSystem.equals(sameRefRecommendationSystem), recommendationSystem.hashCode() == sameRefRecommendationSystem.hashCode());
    Assert.assertEquals(recommendationSystem.equals(sameStateAsRecommendationSystem), recommendationSystem.hashCode() == sameStateAsRecommendationSystem.hashCode());
  }
}