package com.marceldev.ourcompanylunchnoti.repository;

import com.marceldev.ourcompanylunchnoti.document.PushNotificationToken;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PushNotificationTokenRepository extends
    MongoRepository<PushNotificationToken, Long> {

  Optional<PushNotificationToken> findByMemberId(Long memberId);
}
