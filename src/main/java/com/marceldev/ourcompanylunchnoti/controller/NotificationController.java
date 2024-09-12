package com.marceldev.ourcompanylunchnoti.controller;

import com.marceldev.ourcompanylunchnoti.dto.RegisterFcmTokenRequestDto;
import com.marceldev.ourcompanylunchnoti.dto.UnregisterFcmTokenRequestDto;
import com.marceldev.ourcompanylunchnoti.exception.ErrorResponse;
import com.marceldev.ourcompanylunchnoti.exception.TokenNotFoundException;
import com.marceldev.ourcompanylunchnoti.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "1 Notification")
public class NotificationController {

  private final NotificationService notificationService;

  @Operation(
      summary = "Register FCM Push Notification Token",
      description = "Client get a token from FCM server. Register the token using this endpoint."
  )
  @PostMapping("/notifications/fcm/token")
  public ResponseEntity<Void> notificationFcmToken(
      @Validated @RequestBody RegisterFcmTokenRequestDto dto
  ) {
    notificationService.registerToken(dto);
    return ResponseEntity.ok().build();
  }

  @Operation(
      summary = "Unregister FCM Push Notification Token",
      description = "Remove the token from our server."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "errorCode: 6001 - Token not found", content = @Content)
  })
  @DeleteMapping("/notifications/fcm/token")
  public ResponseEntity<Void> notificationFcmTokenDelete(
      @Validated @RequestBody UnregisterFcmTokenRequestDto dto
  ) {
    notificationService.unregisterToken(dto.getMemberId());
    return ResponseEntity.ok().build();
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handler(
      TokenNotFoundException e,
      HttpServletRequest request
  ) {
    log.error("TokenNotFoundException, {}, {}, {}", request.getRequestURI(), e.getMessage(),
        String.valueOf(e.getCause()));

    return ErrorResponse.badRequest(6001, e.getMessage());
  }
}
