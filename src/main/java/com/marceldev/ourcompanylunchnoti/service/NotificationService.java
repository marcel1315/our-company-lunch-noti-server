package com.marceldev.ourcompanylunchnoti.service;

import com.marceldev.ourcompanylunchnoti.component.FCMPushNotification;
import com.marceldev.ourcompanylunchnoti.document.PushNotificationToken;
import com.marceldev.ourcompanylunchnoti.dto.RegisterFcmTokenRequestDto;
import com.marceldev.ourcompanylunchnoti.repository.PushNotificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

  private final PushNotificationTokenRepository pushNotificationTokenRepository;

  private final FCMPushNotification fcmPushNotification;

  /**
   * Save FCM Token for each member
   */
  public void registerToken(RegisterFcmTokenRequestDto dto) {
    pushNotificationTokenRepository.findByMemberId(dto.getMemberId())
        .ifPresent(pushNotificationTokenRepository::delete);

    PushNotificationToken tokenEntity = PushNotificationToken.builder()
        .token(dto.getToken())
        .memberId(dto.getMemberId())
        .build();
    pushNotificationTokenRepository.save(tokenEntity);
  }

  /**
   * Remove FCM Token
   */
  public void unregisterToken(long memberId) {
    pushNotificationTokenRepository.findByMemberId(memberId)
        .ifPresent(pushNotificationTokenRepository::delete);
  }
}
