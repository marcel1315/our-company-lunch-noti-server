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
public class RegisterFcmTokenRequestDto {

  @NotNull
  private String token;

  @NotNull
  private Long memberId;
}
