package com.example.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller - need to tell this to spring
@RestController
public class HelloWorldController {
	
	@Autowired
	MessageSource messageSource;		//automatically picks up messages.properties file
	
	//method - GET
	//URI - /hello-world
	//will return text "Hello world"
	
	//Also can use @GetMapping(path="/hello-world") --> No need to specify method then
	@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	public String helloWorld() {
		
		return "Hello World!";
		
	}
	
	//return a bean
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean HelloWorldBean() {
		
		return new HelloWorldBean("Hello World!");
	}
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean HelloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello, %s", name));
	}
	
	//Internationalization - i18n -> for different users in different languages
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized(
			//@RequestHeader(name="Accept-Language", required=false) Locale locale			//Locale = contains different languages
			) {																				//for this, need to take locale as a parameter always
		
		return messageSource.getMessage("good.morning.message", null,"Default Message ", 
										//locale);
										LocaleContextHolder.getLocale());		//method to retrieve Locale, no need to send with request now
		
		//Need to configure all this in a properties file - use messageSource
		//en - Hello world!
		//fr - Bonjour!
		//ja - Konichiwa Sekai!
		
	}
	
}
