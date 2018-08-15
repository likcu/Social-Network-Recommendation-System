package edu.neu.ccs.cs5004;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a file processor.
 */
public class FileProcesser implements Ifileprocessor {

  private static final Integer ZERO = 0;
  private static final Integer ONE = 1;
  private static final Integer TWO = 2;
  private static final Integer THREE = 3;
  private static final Integer FOUR = 4;
  private static final Integer FILE1_LENGTH = 5;
  private static final Integer FILE2_LENGTH = 2;

  private Irecommendationsystem recommendationSystem;

  /**
   * Creates a file processor.
   */
  public FileProcesser(Irecommendationsystem recommendationSystem) {
    this.recommendationSystem = recommendationSystem;
  }

  public Irecommendationsystem getRecommendationSystem() {
    return recommendationSystem;
  }

  /**
   * write the recommendations in the file.
   *
   * @param file3           the destination file
   * @param recommendations recommendation content for the file
   * @throws IOException throws an IO exception
   */
  public void writeFile(File file3, Map<User, List<User>> recommendations) throws IOException {
    try {

      OutputStream outputStream = new FileOutputStream(file3);
      BufferedWriter bufferedWriter
          = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

      bufferedWriter.write(FileMessage.USER_ID.getMessage() + FileMessage.COMMA.getMessage()
          + FileMessage.RECOMMENDATION_ID.getMessage());
      bufferedWriter.newLine();

      Set<User> users = recommendations.keySet();
      List<User> userList = new ArrayList<>(users);
      recommendationSystem.sortById(userList);

      for (User keyUser : userList) {
        String user = keyUser.toString();
        List<User> recommenders = recommendations.get(keyUser);
        bufferedWriter.write(user + FileMessage.COMMA.getMessage());

        for (User recommender : recommenders) {
          bufferedWriter.write(recommender.toString() + FileMessage.SPACE.getMessage());
        }

        bufferedWriter.newLine();
      }

      bufferedWriter.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * read the file.
   *
   * @param file1 file for nodes
   * @param file2 file for edges
   * @throws IOException throws an IO exception
   */
  public void readFile(File file1, File file2)
      throws IOException {

    try {
      InputStream inputStream = new FileInputStream(file1);
      BufferedReader bufferedReader
          = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

      InputStream inputStream2 = new FileInputStream(file2);
      BufferedReader bufferedReader2
          = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"));

      InputStream inputStream3 = new FileInputStream(file2);
      BufferedReader bufferedReader3
          = new BufferedReader(new InputStreamReader(inputStream3, "UTF-8"));

      bufferedReader.readLine();
      bufferedReader2.readLine();
      bufferedReader3.readLine();
      String line1;
      String line2;

      while ((line1 = bufferedReader.readLine()) != null) {
        String[] node = line1.split(FileMessage.COMMA.getMessage());

        User user = new User(Integer.parseInt(node[ZERO]), node[ONE], node[TWO],
            Integer.parseInt(node[THREE]), node[FOUR], ZERO);
        recommendationSystem.getUsers().add(user);
      }

      for (int i = ZERO; i < recommendationSystem.getUsers().size(); i++) {
        User user = recommendationSystem.findById(i);
        recommendationSystem.getRelation().getFollowerRelation().put(user, new ArrayList<>());
      }

      while ((line2 = bufferedReader2.readLine()) != null) {
        String[] edge = line2.split(FileMessage.COMMA.getMessage());

        User curUser = recommendationSystem.findById(Integer.parseInt(edge[ZERO]));
        User following = recommendationSystem.findById(Integer.parseInt(edge[ONE]));

        if (!recommendationSystem.getRelation().getFollowerRelation().get(following)
            .contains(curUser)) {
          recommendationSystem.getRelation().getFollowerRelation().get(following).add(curUser);
        }
      }

      for (int i = ZERO; i < recommendationSystem.getUsers().size(); i++) {

        User curUser = recommendationSystem.findById(i);
        Integer size = recommendationSystem.getRelation().getFollowerRelation()
            .get(curUser).size();
        for (int j = ZERO; j < size; j++) {
          curUser.setFollower(curUser.getFollower() + ONE);
        }
      }

      for (int i = ZERO; i < recommendationSystem.getUsers().size(); i++) {
        User user = recommendationSystem.findById(i);
        recommendationSystem.getRelation().getFollowingRelation().put(user, new ArrayList<>());
        recommendationSystem.getRelation().getFollowerRelation().put(user, new ArrayList<>());
      }

      String line3;

      while ((line3 = bufferedReader3.readLine()) != null) {
        String[] edge = line3.split(FileMessage.COMMA.getMessage());

        User curUser = recommendationSystem.findById(Integer.parseInt(edge[ZERO]));
        User following = recommendationSystem.findById(Integer.parseInt(edge[ONE]));

        if (!recommendationSystem.getRelation().getFollowingRelation().get(curUser)
            .contains(following)) {
          recommendationSystem.getRelation().getFollowingRelation().get(curUser).add(following);
        }

        if (!recommendationSystem.getRelation().getFollowerRelation().get(following)
            .contains(curUser)) {
          recommendationSystem.getRelation().getFollowerRelation().get(following).add(curUser);
        }
      }

      bufferedReader.close();
      bufferedReader2.close();
      bufferedReader3.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * check if the file is valid.
   *
   * @param file1 file for nodes
   * @param file2 file for edges
   * @throws IOException throws IO exception
   */
  public void checkFile(File file1, File file2) throws IOException {

    InputStream inputStream = new FileInputStream(file1);
    BufferedReader bufferedReader
        = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
    String line = bufferedReader.readLine();
    String[] titles = line == null ? null : line.split(FileMessage.COMMA.getMessage());

    if (titles == null || titles.length != FILE1_LENGTH) {
      throw new RuntimeException(FileMessage.ERROR.getMessage());
    }

    if (!titles[ZERO].equals(FileMessage.NODE_ID.getMessage())
        || !titles[ONE].equals(FileMessage.DATE.getMessage())
        || !titles[TWO].equals(FileMessage.GENDER.getMessage())
        || !titles[THREE].equals(FileMessage.AGE.getMessage())
        || !titles[FOUR].equals(FileMessage.CITY.getMessage())) {
      throw new RuntimeException(FileMessage.ERROR.getMessage());
    }

    InputStream inputStream2 = new FileInputStream(file2);
    BufferedReader bufferedReader2
        = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"));
    String line2 = bufferedReader2.readLine();
    String[] titles2 = line2 == null ? null : line2.split(FileMessage.COMMA.getMessage());

    if (titles2 == null || titles2.length != FILE2_LENGTH) {
      throw new RuntimeException(FileMessage.ERROR.getMessage());
    }

    if (!titles2[ZERO].equals(FileMessage.SOURCE.getMessage())
        || !titles2[ONE].equals(FileMessage.DESTINATION.getMessage())) {
      throw new RuntimeException(FileMessage.ERROR.getMessage());
    }

    bufferedReader.close();
    bufferedReader2.close();
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof FileProcesser)) {
      return false;
    }
    FileProcesser that = (FileProcesser) object;
    return Objects.equals(recommendationSystem, that.recommendationSystem);
  }

  @Override
  public int hashCode() {

    return Objects.hash(recommendationSystem);
  }
}
