package com.manning.sbip.ch08.controller;

import java.time.Clock;
import java.time.Instant;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.manning.sbip.ch08.model.InputMessage;
import com.manning.sbip.ch08.model.OutputMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MessageController {

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public OutputMessage message(InputMessage message) {
		log.info("Input Message "+message);
		return OutputMessage.builder().time(Instant.now(Clock.systemDefaultZone())).content(message.getContent()).build();
	}
}
