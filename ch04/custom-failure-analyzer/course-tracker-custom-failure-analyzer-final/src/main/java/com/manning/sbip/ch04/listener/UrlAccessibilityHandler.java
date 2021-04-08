package com.manning.sbip.ch04.listener;

import com.manning.sbip.ch04.exception.UrlNotAccessibleException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UrlAccessibilityHandler {

	@Value("${api.url:https://dog.ceo/}")
	private String url;

	@EventListener(classes = ContextRefreshedEvent.class)
	public void listen() {
		// For demonstration purpose, we are throwing
		// the exception assuming the site is not reachable
		throw new UrlNotAccessibleException(url);
	}
}
