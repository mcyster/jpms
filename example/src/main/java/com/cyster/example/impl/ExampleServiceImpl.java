package com.cyster.example.impl;

import com.cyster.example.service.ExampleService;
import com.cyster.example.service.ExampleServiceFactory;


public class ExampleServiceImpl implements ExampleService {

    private final String value;
    
    public ExampleServiceImpl(String value) {
        this.value = value;
    }
    
    public String getMessage() {
    	return "ExampleService: " + value;
    }
    
    public static class Factory implements ExampleServiceFactory {
        public Factory() {    
        }
        
        @Override
        public ExampleService createExampleService(String value) {
            return new ExampleServiceImpl(value);
        }
    }
 
}
