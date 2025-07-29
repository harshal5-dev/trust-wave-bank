package com.trustwave.apigateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSecurity) {
    httpSecurity
            .authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
                    .pathMatchers("/trustwavebank/accounts/**").hasRole("ACCOUNTS")
                    .pathMatchers("/trustwavebank/cards/**").hasRole("CARDS")
                    .pathMatchers("/trustwavebank/loans/**").hasRole("LOANS"))
//                    .oauth2ResourceServer(oAuth2ResourceServerSpec ->
//                            oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()));
            .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                    .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesConverter())));
    httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);
    return httpSecurity.build();
  }


  private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesConverter() {
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

    return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
  }
}
