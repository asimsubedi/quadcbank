/**
 * This is our web related configurations like viewResolver, resource resolver, theme resolver if any
 */
package com.nivtek.quadcbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author AsimSubedi
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@ComponentScan(basePackages = { "com.nivtek.quadcbank.controller" })
@EnableWebMvc
public class SpringWebConfig extends WebMvcConfigurerAdapter{

	// Resource Handlers for static contents like css, js
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");

	}

	// view resolver added
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;

	}

}
