package com.trustwave.apigateway.config;


// @Configuration
// @EnableWebFluxSecurity
public class SecurityConfig {


//  @Bean
//  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSecurity) {
//    httpSecurity
//            .authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
//                    .pathMatchers("/trustwavebank/accounts/**").hasAnyRole("ACCOUNTS")
//                    .pathMatchers("/trustwavebank/cards/**").hasAnyRole("CARDS")
//                    .pathMatchers("/trustwavebank/loans/**").hasAnyRole("LOANS"))
//            .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
//                    .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesConverter())));
//    httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);
//    return httpSecurity.build();
//  }
//
//
//  private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesConverter() {
//    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//
//    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
//
//    return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
//  }
}
