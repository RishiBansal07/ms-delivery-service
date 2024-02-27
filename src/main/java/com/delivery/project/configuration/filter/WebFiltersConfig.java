package com.delivery.project.configuration.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;


@Configuration
@SuppressWarnings({"rawtypes", "unchecked"})
public class WebFiltersConfig {

    @Bean
    public FilterRegistrationBean requestLoggingFilter() {
        return registerFilter(Ordered.HIGHEST_PRECEDENCE + 2, new RequestLoggingFilter());
    }

    private FilterRegistrationBean registerFilter(int order, Filter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setOrder(order);
        return registration;
    }

}
