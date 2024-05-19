package com.cyster.check;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.cyster.example.service.ExampleServiceFactory;

class CheckIt {
    public static void main(String[] args) {

    	//printClassPathEntries();
    	//printClassPath();
        //printResources();
        //printModules();
  
        System.out.println("Load ExampleServiceFactory:");

        ServiceLoader<ExampleServiceFactory> serviceLoader = ServiceLoader.load(ExampleServiceFactory.class);
        serviceLoader.forEach(factory -> {
            System.out.println("  Found Factory: " + factory.getClass().getName());
        });

        Optional<ExampleServiceFactory> factory = serviceLoader.findFirst();
        if (factory.isEmpty()) {
            System.out.println("  ServiceLoader found no implementation.");
            throw new IllegalStateException("No implementation of: " + ExampleServiceFactory.class.getSimpleName());
        }

        System.out.println("ExampleService.doIt():\n");
        var service = factory.get().createExampleService("testing 123");
        service.doIt();
        
        
        System.exit(0);
    }
    
    
    private static void printClassPathEntries() {
        System.out.println("Classpath Entries:");
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);
        for (String entry : classpathEntries) {
            System.out.println("  classpath::" + entry);
        }        
    }

    private static void printClassPath() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
        System.out.println("Class Path Resources:");
        try {
            Enumeration<URL> resources = classLoader.getResources("");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                System.out.println("  " + url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void printResources() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        System.out.println("Resource Loader Resources:");
        try {
            Enumeration<URL> resources = classLoader.getResources("");

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                System.out.println("  resource::" + resource);

                if ("file".equals(resource.getProtocol())) {
                    File dir = new File(resource.toURI());
                    listDirectoryContents(dir, "    " + resource + "::");
                } else if ("jar".equals(resource.getProtocol())) {
                    String path = resource.getPath();
                    String jarPath = path.substring(5, path.indexOf("!")); 
                    try (JarFile jarFile = new JarFile(jarPath)) {
                        Enumeration<JarEntry> entries = jarFile.entries();
                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            System.out.println("    " + resource + "::" + entry.getName());
                        }
                    }
                } else {
                    System.out.println("    <skipping>");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void printModules() {
	    System.out.println("Loaded Modules:");
	    ModuleLayer.boot().modules().stream()
	        .map(module -> "  module://" + module.getName())
	        .forEach(System.out::println);
    }
    
    private static void listDirectoryContents(File dir, String prefix) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                listDirectoryContents(file, prefix + "/" + file.getName());
            } else {
                System.out.println(prefix + "/" + file.getName());
            }
        }
    }
}
