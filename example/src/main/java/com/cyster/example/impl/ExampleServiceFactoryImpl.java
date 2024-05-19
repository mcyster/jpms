package com.cyster.example.impl;

import com.cyster.example.service.ExampleService;
import com.cyster.example.service.ExampleServiceFactory;

public class ExampleServiceFactoryImpl implements ExampleServiceFactory {

	@Override
	public ExampleService createExampleService(String value) {
		return new ExampleServiceImpl(value);
	}

}
