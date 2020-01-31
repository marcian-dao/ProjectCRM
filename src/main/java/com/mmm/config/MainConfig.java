package com.mmm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.mmm")
public class MainConfig implements WebMvcConfigurer {
	
	@Autowired
	WebApplicationContext webApplicationContext;	
     
    @Bean
    public SpringResourceTemplateResolver getTemplateResolver() {
    	
    	SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
     	
    	templateResolver.setApplicationContext(webApplicationContext);
    	templateResolver.setPrefix("/WEB-INF/templates/");
    	templateResolver.setSuffix(".html");
    	templateResolver.setTemplateMode("HTML5");
   	
    	return templateResolver; 	
    }
    	
    @Bean 
    public SpringTemplateEngine templateEngine() { 
    	
    	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    	
    	templateEngine.setTemplateResolver(getTemplateResolver());
    	templateEngine.setEnableSpringELCompiler(true);
    	
    	return templateEngine;
    }	
    
    @Bean
    ThymeleafViewResolver configureViewResolvers() {
    	
    	ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    	 	
    	resolver.setTemplateEngine(templateEngine());
    	resolver.setOrder(1);
    	resolver.setViewNames(new String[] {"*.html"});
	
    	return resolver;
    }
    
    @Bean
    public UrlBasedViewResolver getUrlBasedViewResolver() {   	
    	
    	UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
    	
    	viewResolver.setViewClass(JstlView.class);
   	    viewResolver.setPrefix("/WEB-INF/views/");
   	    viewResolver.setSuffix("");
   	    viewResolver.setOrder(2);
    	   	
    	return viewResolver;   	   	
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**",
				            		"/img/**",
				                    "/css/**",
				                    "/js/**")
						            .addResourceLocations("/resources/")
						            .addResourceLocations("/resources/css/")
						            .addResourceLocations("/resources/js/")
						            .addResourceLocations("/resources/img/");
    }    
}
