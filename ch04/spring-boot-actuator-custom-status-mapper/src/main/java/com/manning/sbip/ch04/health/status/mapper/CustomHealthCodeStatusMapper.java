package com.manning.sbip.ch04.health.status.mapper;

import org.springframework.boot.actuate.health.HttpCodeStatusMapper;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCodeStatusMapper implements HttpCodeStatusMapper{

	public int getStatusCode(Status status) {
		if(status == Status.DOWN) {
			return HttpStatus.INTERNAL_SERVER_ERROR.value();
		}
		
		else if(status == Status.OUT_OF_SERVICE) {
			return HttpStatus.INTERNAL_SERVER_ERROR.value();
		}
		return HttpStatus.OK.value();
	}

}
