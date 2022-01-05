
/*
 * SampleCircuitBreakerConfig.java 2022. 01. 05
 *
 * Copyright 2022 Naver Cloud Corp. All rights Reserved.
 * Naver Cloud Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package me.potato.springresilience4jcircuitbreaker.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

/**
 * @author dongju.paek
 */
@Configuration
public class SampleConfig {

	@Bean
	public CircuitBreaker countCircuitBreaker() {
		var cbConfig = CircuitBreakerConfig.custom()
			.waitDurationInOpenState(Duration.ofMinutes(1))
			.slidingWindowSize(2)
			.failureRateThreshold(50)
			.permittedNumberOfCallsInHalfOpenState(2)
			.build();

		return CircuitBreakerRegistry.of(cbConfig).circuitBreaker("SampleCountCircuitBreaker");
	}
}