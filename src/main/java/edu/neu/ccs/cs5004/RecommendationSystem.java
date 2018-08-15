package edu.neu.ccs.cs5004;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * Represents a recommendation system.
 */
public class RecommendationSystem implements Irecommendationsystem {

  private static final Integer ZERO = 0;
  private static final Integer ONE = 1;
  private static final Integer NUMBER_OF_FOLLOWERS = 25;
  private static final Integer MAX_NUMBER = 100;
  private static final Integer MIN_NUMBER = 1;
  private static final Integer DEFAULT_NUMBER_OF_USERS = 50;
  private static final Integer DEFAULT_NUMBER_OF_RECOMMENDATIONS = 15;

  private List<User> users;
  private Relation relation;

  /**
   * Creates a recommendation system.
   *
   * @param users    users of a recommendation system
   * @param relation relation for users
   */
  public RecommendationSystem(List<User> users, Relation relation) {
    this.users = users;
    this.relation = relation;
  }

  /**
   * Getter for property 'users'.
   *
   * @return Value for property 'users'
   */
  public List<User> getUsers() {
    return users;
  }

  /**
   * Getter for property 'relation'.
   *
   * @return Value for property 'relation'
   */
  public Relation getRelation() {
    return relation;
  }

  /**
   * Recommend users for one user.
   *
   * @param curUser                 the user to recommend
   * @param numberOfRecommendations number of recommendations
   * @return list of users to recommend to the given user
   */
  private List<User> recommendForOne(User curUser, Integer numberOfRecommendations) {

    Set<User> tempRecommendSet = new HashSet<>();

    for (User following : relation.getFollowingRelation().get(curUser)) {

      if (relation.getFollowingRelation().get(following).size() == ZERO) {
        continue;
      }

      for (User user : relation.getFollowingRelation().get(following)) {

        if (user == null
            || curUser.equals(user)
            || relation.getFollowingRelation().get(curUser).contains(user)) {
          continue;
        }

        tempRecommendSet.add(user);
      }
    }

    List<User> tempRecommendList = new ArrayList<>(tempRecommendSet);
    List<User> recommendUsers = new ArrayList<>();

    if (tempRecommendList.size() >= numberOfRecommendations) {
      sortById(tempRecommendList);

      for (int i = 0; i < numberOfRecommendations; i ++) {
        recommendUsers.add(tempRecommendList.get(i));
      }
    } else {
      recommendUsers = tempRecommendList;
    }

    if (recommendUsers.size() < numberOfRecommendations) {

      List<User> influencers = mostInfluencer();

      for (User user : influencers) {

        if (user == null
            || curUser.equals(user)
            || relation.getFollowingRelation().get(curUser).contains(user)) {
          continue;
        }

        recommendUsers.add(user);

        if (recommendUsers.size() == numberOfRecommendations) {
          break;
        }
      }
    }

    while (recommendUsers.size() < numberOfRecommendations) {

      Random random = new Random();
      Integer randomNum = random.nextInt(users.size());
      User randomUser = users.get(randomNum);

      if (relation.getFollowingRelation().get(curUser).size()
          > users.size() - numberOfRecommendations) {
        break;
      }

      while (recommendUsers.contains(randomUser)
          || curUser.equals(randomUser)
          || relation.getFollowingRelation().get(curUser).contains(randomUser)) {
        randomNum = random.nextInt(users.size());
        randomUser = users.get(randomNum);
      }

      recommendUsers.add(randomUser);
    }

    sortById(recommendUsers);

    return recommendUsers;
  }

  /**
   * recommend for users.
   *
   * @param processingFlag          the flag for precessing
   * @param numberOfUsersToProcess  number of users to process
   * @param numberOfRecommendations number of recommendations
   * @return the recommendations for users
   */
  public Map<User, List<User>> recommend(String processingFlag, Integer numberOfUsersToProcess,
                                         Integer numberOfRecommendations) {

    if (processingFlag == null) {
      processingFlag = FileMessage.R.getMessage();
    }

    if (numberOfUsersToProcess == null
        || numberOfUsersToProcess < MIN_NUMBER
        || numberOfUsersToProcess > MAX_NUMBER) {
      numberOfUsersToProcess = DEFAULT_NUMBER_OF_USERS;
    }

    if (numberOfRecommendations == null
        || numberOfRecommendations < MIN_NUMBER
        || numberOfRecommendations > MAX_NUMBER) {
      numberOfRecommendations = DEFAULT_NUMBER_OF_RECOMMENDATIONS;
    }

    if (users.size() < numberOfUsersToProcess) {
      throw new RuntimeException(FileMessage.NOT_ENOUGH_USER.getMessage());
    }

    Map<User, List<User>> recommendations = new HashMap<>();

    if (processingFlag.equals(FileMessage.S.getMessage())) {

      for (int i = ZERO; i < numberOfUsersToProcess; i++) {
        List<User> recommendationForOne = recommendForOne(users.get(i), numberOfRecommendations);
        recommendations.put(users.get(i), recommendationForOne);
      }
    } else if (processingFlag.equals(FileMessage.E.getMessage())) {

      int size = users.size();
      for (int i = size - ONE; i >= size - numberOfUsersToProcess; i--) {
        List<User> recommendationForOne = recommendForOne(users.get(i), numberOfRecommendations);
        recommendations.put(users.get(i), recommendationForOne);
      }
    } else {

      for (int i = ZERO; i < numberOfUsersToProcess; i++) {
        Random random = new Random();
        Integer randomNum = random.nextInt(users.size());

        while (recommendations.containsKey(users.get(randomNum))) {
          randomNum = random.nextInt(users.size());
        }
        List<User> recommendationForOne =
            recommendForOne(users.get(randomNum), numberOfRecommendations);
        recommendations.put(users.get(randomNum), recommendationForOne);
      }
    }

    return recommendations;
  }

  /**
   * Returns the user of given ID.
   *
   * @param userId the ID of a user
   * @return the user with given ID
   */
  public User findById(Integer userId) {

    for (User user : users) {
      if (user.getNodeId().equals(userId)) {
        return user;
      }
    }

    throw new RuntimeException(FileMessage.NOT_FOUND_USER.getMessage());
  }

  /**
   * sort the list by user id.
   *
   * @param users the list of users
   */
  public void sortById(List<User> users) {

    Collections.sort(users, new Comparator<User>() {
      @Override
      public int compare(User user1, User user2) {
        return user1.getNodeId() - user2.getNodeId();
      }
    });
  }

  /**
   * Returns the list of users by number of followers.
   *
   * @return the list of users by number of followers
   */
  private List<User> mostInfluencer() {

    List<User> influencers = new ArrayList<>();

    for (User user : users) {
      if (user.getFollower() >= NUMBER_OF_FOLLOWERS) {
        influencers.add(user);
      }
    }

    sortById(influencers);

    return influencers;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof RecommendationSystem)) {
      return false;
    }
    RecommendationSystem that = (RecommendationSystem) object;
    return Objects.equals(getUsers(), that.getUsers())
        && Objects.equals(getRelation(), that.getRelation());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getUsers(), getRelation());
  }
}