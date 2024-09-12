package com.marceldev.ourcompanylunchnoti.controller;

import com.marceldev.ourcompanylunchnoti.dto.RegisterFcmTokenRequestDto;
import com.marceldev.ourcompanylunchnoti.dto.UnregisterFcmTokenRequestDto;
import com.marceldev.ourcompanylunchnoti.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  @DeleteMapping("/notifications/fcm/token")
  public ResponseEntity<Void> notificationFcmTokenDelete(
      @Validated @RequestBody UnregisterFcmTokenRequestDto dto
  ) {
    notificationService.unregisterToken(dto.getMemberId());
    return ResponseEntity.ok().build();
  }
}
