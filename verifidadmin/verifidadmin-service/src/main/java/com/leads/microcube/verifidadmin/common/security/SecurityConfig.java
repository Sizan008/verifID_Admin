package com.leads.microcube.verifidadmin.common.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final boolean securityEnabled;

  public SecurityConfig(
      @Value("${verifidadmin.security.enabled:false}") boolean securityEnabled) {
    this.securityEnabled = securityEnabled;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity http,
      RestAuthenticationEntryPoint authenticationEntryPoint,
      RestAccessDeniedHandler accessDeniedHandler)
      throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .cors(Customizer.withDefaults())
        .sessionManagement(
            session ->
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .authorizeHttpRequests(
            authorization -> {
              if (!securityEnabled) {
                authorization.anyRequest().permitAll();
                return;
              }

              authorization
                  .requestMatchers(HttpMethod.OPTIONS, "/**")
                  .permitAll()
                  .requestMatchers(
                      "/",
                      "/api/Account/Login",
                      "/api/Account/Validate",
                      "/api/Account/ValidateCentralLogin",
                      "/api/Error/**",
                      "/swagger-ui.html",
                      "/swagger-ui/**",
                      "/v3/api-docs/**",
                      "/actuator/health")
                  .permitAll()
                  .anyRequest()
                  .authenticated();
            })
        .exceptionHandling(
            exceptions ->
                exceptions
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler));

    http.addFilterBefore(
        new SessionAuthenticationFilter(),
        AnonymousAuthenticationFilter.class);
    return http.build();
  }
}
