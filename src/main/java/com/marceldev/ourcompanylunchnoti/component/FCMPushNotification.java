package com.marceldev.ourcompanylunchnoti.component;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FCMPushNotification {

  public void sendPushNotification(String token, String title, String body) {
    Message msg = Message.builder()
        .setNotification(
            Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build()
        )
        .setToken(token)
        .build();

    try {
      String response = FirebaseMessaging.getInstance().send(msg);
      log.debug("Message sent successfully: " + response);
    } catch (Exception e) {
      // "Requested entity was not found." error -> The token might not valid anymore.
      log.error(e.getMessage());
    }
  }
}
