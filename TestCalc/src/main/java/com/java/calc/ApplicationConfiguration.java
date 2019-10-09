package com.java.calc;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.java.calc.common.OperatorConverter;

@Configuration
@ComponentScan(basePackages = "com.java.calc")
public class ApplicationConfiguration extends WebMvcConfigurationSupport {
//  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/")
				.setCacheControl(CacheControl.maxAge(30L, TimeUnit.DAYS).cachePublic()).resourceChain(true)
				.addResolver(new WebJarsResourceResolver());
	}
}
