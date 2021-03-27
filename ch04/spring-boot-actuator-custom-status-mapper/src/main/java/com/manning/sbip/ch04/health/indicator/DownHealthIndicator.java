package com.manning.sbip.ch04.health.indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class DownHealthIndicator implements HealthIndicator {

	public Health health() {
		return Health.status(Status.DOWN).build();
	}

}
