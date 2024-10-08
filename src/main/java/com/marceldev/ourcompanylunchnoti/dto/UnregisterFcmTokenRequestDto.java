package com.marceldev.ourcompanylunchnoti.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnregisterFcmTokenRequestDto {

  @NotNull
  private Long memberId;
}
