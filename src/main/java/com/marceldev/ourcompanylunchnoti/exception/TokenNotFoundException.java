package com.marceldev.ourcompanylunchnoti.exception;

public class TokenNotFoundException extends RuntimeException {

  public TokenNotFoundException() {
    super("Token not found.");
  }
}
