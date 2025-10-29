package goya.daw2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class Mensajes {	
	@Bean
	ResourceBundleMessageSource messageSource() {
	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	messageSource.setBasename("Messages");
    messageSource.setDefaultEncoding("UTF-8"); 
	return messageSource;

}

}
