package edu.remad.tutoring2.tutoringconfig;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import edu.remad.tutoring2.appconstants.ViewResolversAppConstants;

@Configuration
public class FreeMarkerConfig {

	@Bean 
	public FreeMarkerViewResolver freeMarkerViewResolver() { 
	    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver(); 
	    resolver.setCache(true); 
	    resolver.setPrefix(ViewResolversAppConstants.FREE_MARKER_PREFIX); 
	    resolver.setSuffix(ViewResolversAppConstants.FREE_MARKER_SUFFIX);
	    resolver.setOrder(0);
	    
	    return resolver; 
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		Properties properties = new Properties();
		properties.put("auto_import", "/spring.ftl as spring");
		properties.put("template_exception_handler", "rethrow");

		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath(ViewResolversAppConstants.PATH_WEB_INF + "templates");
		configurer.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
		configurer.setFreemarkerSettings(properties);

		return configurer;
	}
}
