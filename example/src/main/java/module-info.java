

module com.example {
    exports com.cyster.example.service;
    
    uses com.cyster.example.service.ExampleServiceFactory;
    provides com.cyster.example.service.ExampleServiceFactory with com.cyster.example.impl.ExampleServiceFactoryImpl;
}

