package com.leads.microcube.verifidadmin.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityWebConfig implements WebMvcConfigurer {

  private final FeaturePermissionInterceptor featurePermissionInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(featurePermissionInterceptor)
        .addPathPatterns("/api/**")
        .excludePathPatterns(
            "/api/Account/Login",
            "/api/Account/Validate");
  }
}
