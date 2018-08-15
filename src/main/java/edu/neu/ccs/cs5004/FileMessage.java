package edu.neu.ccs.cs5004;

/**
 * Represents the message for file processor.
 */
public enum FileMessage {

  USER_ID("User ID"),
  RECOMMENDATION_ID("Recommendation ID"),
  COMMA(","),
  SPACE(" "),

  NODE_ID("Node ID"),
  ERROR("invalid file."),
  DATE("Date created"),
  GENDER("Gender"),
  AGE("Age"),
  CITY("City"),
  SOURCE("Source"),
  DESTINATION("Destination"),
  FILE_TYPE(".csv"),

  S("s"),
  E("e"),
  R("r"),
  NOT_FOUND_USER("user id not found"),
  NOT_ENOUGH_USER("not enough users to process");

  private String message;

  /**
   * Creates file message.
   *
   * @param message message to show
   */
  FileMessage(String message) {
    this.message = message;
  }

  /**
   * Getter for property 'message'.
   *
   * @return Value for property 'message'
   */
  public String getMessage() {
    return message;
  }

}
