# JPMS with Gradle

This repository has 2 java subprojects
- example
  - a JPMS module
  - exposes a service factory
- check
  - get the ServiceFactory using java.util.SerivceLoader
  - creates the service using the loaded factory
  - invokes a method on the service to show its properly initialized
    
# Development

Build everything:
```
./gradlew
```

# Notes
Local projects should all have a module-info.java or no projects should have it. While java can support mixing class paths and JPMS modules, 
Gradle doesn't handle mixing local project types.

## References
- https://github.com/java9-modularity/gradle-modules-plugin
- https://docs.gradle.org/current/samples/sample_java_modules_multi_project.html
- https://docs.gradle.org/current/javadoc/org/gradle/api/jvm/package-summary.html
- https://github.com/baumgarb/javalin-with-jpms-and-gradle
  - simple code base that uses modules
- https://github.com/bredmold/jpms-junit-gradle
  - https://github.com/bredmold/jpms-junit-gradle/blob/master/service.impl/src/test/java/module-info.test

