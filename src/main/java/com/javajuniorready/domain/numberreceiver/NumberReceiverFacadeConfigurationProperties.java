package com.javajuniorready.domain.numberreceiver;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="lotto.number-receiver.facade")
@Builder
public record NumberReceiverFacadeConfigurationProperties(int count, int lowBound, int highBound) {
}
