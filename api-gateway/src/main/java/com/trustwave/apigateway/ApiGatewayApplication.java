package com.trustwave.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}


	@Bean
	public RouteLocator trustWaveBankRoutesConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
								.path("/trustwavebank/accounts/**")
								.filters(f -> f.rewritePath("/trustwavebank/accounts/(?<segment>.*)", "/${segment}")
												.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://ACCOUNTS"))
						.route(p -> p
										.path("/trustwavebank/loans/**")
										.filters(f -> f.rewritePath("/trustwavebank/loans/(?<segment>.*)", "/${segment}")
														.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
										.uri("lb://LOANS"))
						.route(p -> p
										.path("/trustwavebank/cards/**")
										.filters(f -> f.rewritePath("/trustwavebank/cards/(?<segment>.*)", "/${segment}")
														.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
										.uri("lb://CARDS"))
				.build();
	}

}
