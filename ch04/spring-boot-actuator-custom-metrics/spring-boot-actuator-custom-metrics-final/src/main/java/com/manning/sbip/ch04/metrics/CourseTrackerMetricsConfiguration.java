package com.manning.sbip.ch04.metrics;

import com.manning.sbip.ch04.service.CourseService;
import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.binder.BaseUnits;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CourseTrackerMetricsConfiguration {

    @Bean
    public Counter createCourseCounter(MeterRegistry meterRegistry) {
        return Counter.builder("api.courses.created.count")
                .description("Total number of courses created")
                .register(meterRegistry);
    }

    @Bean
    public Gauge createCoursesGauge(MeterRegistry meterRegistry, CourseService courseService) {
        return Gauge.builder("api.courses.created.gauge", courseService::count)
                .description("Total courses created")
                .register(meterRegistry);
    }

    @Bean
    public Timer createCoursesTimer(MeterRegistry meterRegistry) {
        return Timer.builder("api.courses.creation.time")
                .description("Course creation time")
                /*.sla(Duration.ofMillis(10))
                .minimumExpectedValue(Duration.ofMillis((1)))
                .maximumExpectedValue(Duration.ofMillis(10))
                .publishPercentiles(0.5, 0.95)
                .publishPercentileHistogram()*/
                .register(meterRegistry);
    }

    @Bean
    public DistributionSummary createDistributionSummary(MeterRegistry meterRegistry) {
        return DistributionSummary.builder("api.courses.rating.distribution.summary")
               .description("Rating distribution summary")
                .register(meterRegistry);
    }
}
