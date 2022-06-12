package de.bcxp.thesis.gitops.application.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
@Configuration
public class FilterConfiguration {

  @Bean
  public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
    final FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();

    registrationBean.setFilter(new RequestLoggingFilter());

    registrationBean.addUrlPatterns("/*");

    return registrationBean;

  }

  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilter() {
    final FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();

    registrationBean.setFilter(new CorsFilter());

    registrationBean.addUrlPatterns("/*");

    return registrationBean;
  }

}
