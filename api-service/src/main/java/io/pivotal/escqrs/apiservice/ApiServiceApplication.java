package io.pivotal.escqrs.apiservice;

import io.pivotal.escqrs.apiservice.widget.FakeWidgetCommandService;
import io.pivotal.escqrs.apiservice.widget.HttpWidgetQueryService;
import io.pivotal.escqrs.apiservice.widget.WidgetCommandService;
import io.pivotal.escqrs.apiservice.widget.WidgetQueryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceApplication.class, args);
	}

	@Bean
	public WidgetQueryService widgetQueryService() {
		return new HttpWidgetQueryService();
	}

	@Bean
	public WidgetCommandService widgetCommandService() {
		return new FakeWidgetCommandService();
	}
}
