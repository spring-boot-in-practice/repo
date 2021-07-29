package com.manning.sbip.ch08.model;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputMessage {

	private Instant time;
	private String content;
}
