package com.manning.sbip.ch04.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class UrlNotAccessibleException extends RuntimeException {

	private String url;
	
	public UrlNotAccessibleException(String url) {
		this(url, null);
	}

	public UrlNotAccessibleException(String url, Throwable cause) {
		super("URL " + url + " is not accessible", cause);
		this.url = url;
	}
}
