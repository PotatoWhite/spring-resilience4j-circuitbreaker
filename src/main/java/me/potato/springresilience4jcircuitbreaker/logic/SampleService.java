
/*
 * SampleService.java 2022. 01. 05
 *
 * Copyright 2022 Naver Cloud Corp. All rights Reserved.
 * Naver Cloud Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package me.potato.springresilience4jcircuitbreaker.logic;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.potato.springresilience4jcircuitbreaker.spec.Sample;

/**
 * @author dongju.paek
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {

	public Sample getSampleOrThrow() {
		log.info("getSample !");

		throw new RuntimeException("Hello Error World");
		//				return new Sample(1L, "potato", LocalDateTime.now());
	}

	public Sample getSampleFallback() {
		log.info("getSampleFallback !");
		return new Sample(-1L, "carrot", LocalDateTime.now());
	}

}