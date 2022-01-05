
/*
 * SampleController.java 2022. 01. 05
 *
 * Copyright 2022 Naver Cloud Corp. All rights Reserved.
 * Naver Cloud Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package me.potato.springresilience4jcircuitbreaker.expose.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.potato.springresilience4jcircuitbreaker.logic.SampleService;
import me.potato.springresilience4jcircuitbreaker.spec.Sample;

/**
 * @author dongju.paek
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class SampleController {

	private final CircuitBreaker countCircuitBreaker;
	private final CircuitBreakerRegistry circuitBreakerRegistry;
	private final SampleService sampleService;

	@GetMapping("test1")
	public Sample getSample() {
		try {
			return sampleService.getSampleOrThrow();
		} catch (Exception e) {
			log.error("an error occur at catch");
			return sampleService.getSampleFallback();
		}
	}

	@GetMapping("test2")
	public Sample getSamplePlain() {
		var decoratedSupplier = CircuitBreaker.decorateSupplier(countCircuitBreaker, sampleService::getSampleOrThrow);
		return Try.ofSupplier(decoratedSupplier).recover(throwable -> sampleService.getSampleFallback()).get();
	}

	@GetMapping("test3")
	public Sample getSampleSpring() {
		var circuit = circuitBreakerRegistry.circuitBreaker("sampleCircuit");
		var decoratedSupplier = CircuitBreaker.decorateSupplier(circuit, sampleService::getSampleOrThrow);
		return Try.ofSupplier(decoratedSupplier).recover(throwable -> sampleService.getSampleFallback()).get();
	}
}