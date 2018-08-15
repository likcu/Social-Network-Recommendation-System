package edu.neu.ccs.cs5004;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Run the recommendation system.
 */
public class Run {

  private static final Integer ZERO = 0;
  private static final Integer ONE = 1;
  private static final Integer TWO = 2;
  private static final Integer THREE = 3;
  private static final Integer FOUR = 4;
  private static final Integer FIVE = 5;
  private static final Integer SIX = 6;

  /**
   * main method to run the recommendation system.
   *
   * @param args input parameters
   * @throws IOException throws an IO exception
   */
  public static void main(String[] args) throws IOException {

    Irecommendationsystem recommendationSystem = new RecommendationSystem(new ArrayList<>(),
        new Relation(new HashMap<>(), new HashMap<>()));

    if (args == null || args.length < THREE) {
      throw new RuntimeException(FileMessage.ERROR.getMessage());
    }

    if (!args[ZERO].endsWith(FileMessage.FILE_TYPE.getMessage())
        || !args[ONE].endsWith(FileMessage.FILE_TYPE.getMessage())
        || !args[TWO].endsWith(FileMessage.FILE_TYPE.getMessage())) {
      throw new RuntimeException(FileMessage.ERROR.getMessage());
    }

    File file1 = new File(args[ZERO]);
    File file2 = new File(args[ONE]);
    File file3 = new File(args[TWO]);

    Ifileprocessor fileProcesser = new FileProcesser(recommendationSystem);

    fileProcesser.checkFile(file1, file2);

    fileProcesser.readFile(file1, file2);

    String processingFlag = args.length < FOUR ? null : args[THREE];
    Integer numberOfUsersToProcess = args.length < FIVE ? null : Integer.parseInt(args[FOUR]);
    Integer numberOfRecommendations = args.length < SIX ? null : Integer.parseInt(args[FIVE]);

    Map<User, List<User>> result = fileProcesser.getRecommendationSystem().recommend(processingFlag,
        numberOfUsersToProcess, numberOfRecommendations);

    fileProcesser.writeFile(file3, result);
  }
}

