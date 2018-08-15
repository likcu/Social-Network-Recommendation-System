package edu.neu.ccs.cs5004;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Represents a file processor.
 */
public interface Ifileprocessor {

  /**
   * write the recommendations in the file.
   *
   * @param file3 the destination file
   * @param recommendations recommendation content for the file
   * @throws IOException throws an IO exception
   */
  void writeFile(File file3, Map<User, List<User>> recommendations) throws IOException;

  /**
   * read the file.
   *
   * @param file1 file for nodes
   * @param file2 file for edges
   * @throws IOException throws an IO exception
   */
  void readFile(File file1, File file2)
      throws IOException;

  /**
   * check if the file is valid.
   *
   * @param file1 file for nodes
   * @param file2 file for edges
   * @throws IOException throws IO exception
   */
  void checkFile(File file1, File file2) throws IOException;

  Irecommendationsystem getRecommendationSystem();
}
