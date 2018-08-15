package edu.neu.ccs.cs5004;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

  private User user;
  private User sameRefUser;
  private User sameStateAsUser;
  private User diffUser;
  private User diffUser2;
  private User diffUser3;
  private User diffUser4;
  private User yetAnotherUser;
  private User nullUser = null;

  @Before
  public void setUp() throws Exception {

    user = new User(4, "2/2/1995", "M", 22, "Seattle", 2);
    diffUser = new User(2, "2/2/1995", "M", 22, "Seattle", 2);
    diffUser2 = new User(4, "2/2/1995", "F", 22, "Seattle", 2);
    diffUser3 = new User(4, "2/2/1995", "M", 23, "Seattle", 2);
    diffUser4 = new User(4, "2/2/1995", "M", 22, "Seattle", 4);
    sameRefUser = user;
    sameStateAsUser = new User(4, "2/2/1995", "M", 22, "Seattle", 2);
    yetAnotherUser = new User(4, "2/2/1995", "M", 22, "Seattle", 2);
  }

  @Test
  public void getNodeId() {
    Assert.assertEquals(new Integer(4), user.getNodeId());
  }

  @Test
  public void setNodeId() {
    user.setNodeId(3);
    Assert.assertEquals(new Integer(3), user.getNodeId());
  }

  @Test
  public void getDate() {
    Assert.assertEquals("2/2/1995", user.getDate());
  }

  @Test
  public void setDate() {
    user.setDate("2/3/1995");
    Assert.assertEquals("2/3/1995", user.getDate());
  }

  @Test
  public void getGender() {
    Assert.assertEquals("M", user.getGender());
  }

  @Test
  public void setGender() {
    user.setGender("F");
    Assert.assertEquals("F", user.getGender());
  }

  @Test
  public void getAge() {
    Assert.assertEquals(new Integer(22), user.getAge());
  }

  @Test
  public void setAge() {
    user.setAge(23);
    Assert.assertEquals(new Integer(23), user.getAge());
  }

  @Test
  public void getCity() {
    Assert.assertEquals("Seattle", user.getCity());
  }

  @Test
  public void setCity() {
    user.setCity("New York");
    Assert.assertEquals("New York", user.getCity());
  }

  @Test
  public void getFollower() {
    Assert.assertEquals(new Integer(2), user.getFollower());
  }

  @Test
  public void setFollower() {
    user.setFollower(3);
    Assert.assertEquals(new Integer(3), user.getFollower());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("4", user.toString());
  }

  @Test
  public void equals() {

    Assert.assertTrue(user.equals(user));
    Assert.assertTrue(user.equals(sameRefUser));
    Assert.assertTrue(user.equals(sameStateAsUser));
    Assert.assertTrue(sameStateAsUser.equals(user));
    Assert.assertEquals(user.equals(sameStateAsUser) && sameStateAsUser.equals(yetAnotherUser), yetAnotherUser.equals(user));
    Assert.assertFalse(user.equals(diffUser));
    Assert.assertFalse(user.equals(diffUser2));
    Assert.assertFalse(user.equals(diffUser3));
    Assert.assertFalse(user.equals(diffUser4));
    Assert.assertFalse(user.equals(nullUser));
  }

  @Test
  public void testHashCode() {

    Assert.assertEquals(user.equals(sameRefUser), user.hashCode() == sameRefUser.hashCode());
    Assert.assertEquals(user.equals(sameStateAsUser), user.hashCode() == sameStateAsUser.hashCode());
  }
}