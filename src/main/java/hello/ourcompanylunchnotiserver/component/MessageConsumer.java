package hello.ourcompanylunchnotiserver.component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.ourcompanylunchnotiserver.dto.NotificationMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

  private final static String COMMENT_WRITE_TOPIC_NAME = "comment.write.v1";

  private static final String GROUP_ID = "group1";

  private final ObjectMapper objectMapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @KafkaListener(topics = COMMENT_WRITE_TOPIC_NAME, groupId = GROUP_ID)
  public void recordListener(String message) {
    try {
      NotificationMessageDto dto = objectMapper.readValue(message,
          NotificationMessageDto.class);
      //TODO: find fcm token. then send message.
      log.info("sender: {}, receiver: {}, content: {}",
          dto.getSenderName(),
          dto.getReceiverName(),
          dto.getContent()
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
