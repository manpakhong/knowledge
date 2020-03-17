package com.rabbitforever.knowledge.spring.init;


import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.rabbitforever.knowledge.spring.configs.DataServiceConfig;
import com.rabbitforever.knowledge.spring.configs.SecurityConfig;
import com.rabbitforever.knowledge.spring.configs.WebConfig;
import com.rabbitforever.knowledge.spring.configs.WebSocketConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{
			DataServiceConfig.class
			,
			SecurityConfig.class,
			WebSocketConfig.class

		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{
				WebConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);
		return new Filter[]{new HiddenHttpMethodFilter(), cef};
	}

	// <=> <multipart-config>
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	private MultipartConfigElement getMultipartConfigElement() {
		return  new MultipartConfigElement(
				null, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
	}

	private static final long MAX_FILE_SIZE = 5000000;
	// Beyond that size spring will throw exception.
	private static final long MAX_REQUEST_SIZE = 5000000;

	// Size threshold after which files will be written to disk
	private static final int FILE_SIZE_THRESHOLD = 0;
}