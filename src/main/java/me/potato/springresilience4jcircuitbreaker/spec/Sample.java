
/*
 * Sample.java 2022. 01. 05
 *
 * Copyright 2022 Naver Cloud Corp. All rights Reserved.
 * Naver Cloud Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package me.potato.springresilience4jcircuitbreaker.spec;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dongju.paek
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Sample {
	private Long id;
	private String name;
	private LocalDateTime date;
}