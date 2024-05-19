# JPMS with Gradle

This repository has 2 java subprojects
- example
  - a JPMS module
  - exposes a service factory
- check
  - get the ServiceFactory using java.util.SerivceLoad
  - creates the service using the factory
  - invokes a method on the service to show its properly initialized
    
# Development

Build everything:
```
./gradlew
```

## References
- https://github.com/java9-modularity/gradle-modules-plugin

