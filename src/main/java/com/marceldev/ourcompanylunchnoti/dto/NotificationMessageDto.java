package com.marceldev.ourcompanylunchnoti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessageDto {

  private Long senderId;
  private Long receiverId;
  private Long dinerId;
  private String senderName;
  private String receiverName;
  private String dinerName;
  private String content;
}