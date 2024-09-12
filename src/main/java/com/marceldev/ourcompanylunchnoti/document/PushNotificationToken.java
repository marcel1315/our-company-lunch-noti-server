package com.marceldev.ourcompanylunchnoti.document;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Document
public class PushNotificationToken {

  @Id
  private String id;

  @Indexed // The token will be loaded by memberId.
  private Long memberId;

  private String token;

  @LastModifiedDate
  private LocalDateTime modifiedAt;
}
