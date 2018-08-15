package edu.neu.ccs.cs5004;

import java.util.List;
import java.util.Map;

/**
 * Represents a recommendation system.
 */
public interface Irecommendationsystem {

  /**
   * recommend for users.
   *
   * @param processingFlag the flag for precessing
   * @param numberOfUsersToProcess number of users to process
   * @param numberOfRecommendations number of recommendations
   * @return the recommendations for users
   */
  Map<User, List<User>> recommend(String processingFlag, Integer numberOfUsersToProcess,
                                  Integer numberOfRecommendations);

  /**
   * Returns the user of given ID.
   *
   * @param userId the ID of a user
   * @return the user with given ID
   */
  User findById(Integer userId);

  /**
   * sort the list by user id.
   *
   * @param users the list of users
   */
  void sortById(List<User> users);

  /**
   * Getter for property 'relation'.
   *
   * @return Value for property 'relation'
   */
  Relation getRelation();

  /**
   * Getter for property 'users'.
   *
   * @return Value for property 'users'
   */
  List<User> getUsers();
}
