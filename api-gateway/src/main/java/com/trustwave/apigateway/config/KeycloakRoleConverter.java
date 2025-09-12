package com.trustwave.apigateway.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

  @Override
  public Collection<GrantedAuthority> convert(@NonNull Jwt source) {

    Object realmAccessObj = source.getClaims().get("realm_access");
    Map<String, Object> realmAccess = null;
    if (realmAccessObj instanceof Map<?, ?>) {
      @SuppressWarnings("unchecked")
      Map<String, Object> temp = (Map<String, Object>) realmAccessObj;
      realmAccess = temp;
    }

    if (realmAccess == null || realmAccess.isEmpty()) {
      return new ArrayList<>();
    }

    Object rolesObj = realmAccess.get("roles");
    List<String> roles = new ArrayList<>();
    if (rolesObj instanceof List<?>) {
      for (Object role : (List<?>) rolesObj) {
        if (role instanceof String) {
          roles.add((String) role);
        }
      }
    }

    Collection<GrantedAuthority> authorities =
        roles.stream().map(roleName -> "ROLE_" + roleName.toUpperCase())
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    return authorities;
  }
}
