package com.marceldev.ourcompanylunchnoti.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.marceldev.ourcompanylunchnoti.document.PushNotificationToken;
import com.marceldev.ourcompanylunchnoti.dto.RegisterFcmTokenRequestDto;
import com.marceldev.ourcompanylunchnoti.exception.TokenNotFoundException;
import com.marceldev.ourcompanylunchnoti.repository.PushNotificationTokenRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

  @Mock
  private PushNotificationTokenRepository pushNotificationTokenRepository;

  @InjectMocks
  private NotificationService notificationService;

  @Test
  @DisplayName("Register FCM Token - Success")
  void save_push_notification() {
    //given
    RegisterFcmTokenRequestDto dto = new RegisterFcmTokenRequestDto("token", 1L);

    //when
    notificationService.registerToken(dto);
    ArgumentCaptor<PushNotificationToken> captor = ArgumentCaptor.forClass(
        PushNotificationToken.class);

    //then
    verify(pushNotificationTokenRepository).save(captor.capture());
    assertEquals("token", captor.getValue().getToken());
  }

  @Test
  @DisplayName("Unregister FCM token - Success")
  void delete_push_notification() {
    //given
    PushNotificationToken token = PushNotificationToken.builder()
        .id("id10")
        .build();

    //when
    when(pushNotificationTokenRepository.findByMemberId(1L))
        .thenReturn(Optional.of(token));
    notificationService.unregisterToken(1L);

    //then
    verify(pushNotificationTokenRepository).delete(token);
  }

  @Test
  @DisplayName("Unregister FCM token - Fail(Token not found)")
  void delete_push_notification_fail_token_not_found() {
    //given
    //when
    when(pushNotificationTokenRepository.findByMemberId(1L))
        .thenReturn(Optional.empty());

    //then
    assertThrows(
        TokenNotFoundException.class,
        () -> notificationService.unregisterToken(1L)
    );
  }
}