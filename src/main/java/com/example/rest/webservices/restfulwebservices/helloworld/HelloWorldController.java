package com.example.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller - need to tell this to spring
@RestController
public class HelloWorldController {
	
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
	
}
