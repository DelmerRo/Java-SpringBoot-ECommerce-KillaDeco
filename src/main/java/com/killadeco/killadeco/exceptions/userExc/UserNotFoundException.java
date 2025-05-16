package com.killadeco.killadeco.exceptions.userExc;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
