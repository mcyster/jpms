package com.cyster.check;

import java.util.Optional;
import java.util.ServiceLoader;

import com.cyster.example.service.ExampleServiceFactory;

class CheckIt {
    public static void main(String[] args) {
        printModules();
  
        System.out.println("Load ExampleServiceFactory");

        ServiceLoader<ExampleServiceFactory> serviceLoader = ServiceLoader.load(ExampleServiceFactory.class);
        Optional<ExampleServiceFactory> factory = serviceLoader.findFirst();
        if (factory.isEmpty()) {
            System.out.println("  ServiceLoader found no implementation.");
            throw new IllegalStateException("No implementation of: " + ExampleServiceFactory.class.getSimpleName());
        }

        var service = factory.get().createExampleService("testing 123");
        System.out.println("ExampleService.getMessage():" + service.getMessage());
        
        System.exit(0);
    }
        
    private static void printModules() {
	    System.out.println("Loaded Modules:");
	    ModuleLayer.boot().modules().stream()
	        .map(module -> "  module://" + module.getName())
	        .forEach(System.out::println);
    }

}
