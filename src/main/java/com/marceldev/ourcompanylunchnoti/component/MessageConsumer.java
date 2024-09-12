package com.marceldev.ourcompanylunchnoti.component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marceldev.ourcompanylunchnoti.document.PushNotificationToken;
import com.marceldev.ourcompanylunchnoti.dto.NotificationMessageDto;
import com.marceldev.ourcompanylunchnoti.exception.TokenNotFoundException;
import com.marceldev.ourcompanylunchnoti.repository.PushNotificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

  private final static String COMMENT_WRITE_TOPIC_NAME = "comment.write.v1";

  private final static String COMMENT_WRITE_TITLE = "New comment";

  private static final String GROUP_ID = "group1";

  private final ObjectMapper objectMapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  private final PushNotificationTokenRepository pushNotificationTokenRepository;

  private final FCMPushNotification fcmPushNotification;

  @KafkaListener(topics = COMMENT_WRITE_TOPIC_NAME, groupId = GROUP_ID)
  public void commentWriteListener(String message) {
    log.debug("Message received: {}", message);

    try {
      NotificationMessageDto dto = objectMapper
          .readValue(message, NotificationMessageDto.class);

      PushNotificationToken token = pushNotificationTokenRepository
          .findByMemberId(dto.getReceiverId())
          .orElseThrow(TokenNotFoundException::new);

      fcmPushNotification.sendPushNotification(
          token.getToken(),
          COMMENT_WRITE_TITLE,
          dto.getContent()
      );

      log.debug(
          "Message consumed. sender: {}, receiver: {}, content: {}",
          dto.getSenderId(), dto.getReceiverId(), dto.getContent()
      );
    } catch (Exception e) {
      log.error("{}", e.getMessage());
    }
  }
}
