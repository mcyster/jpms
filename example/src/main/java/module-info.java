
module com.cyster.example {
    exports com.cyster.example.service;
    provides com.cyster.example.service.ExampleServiceFactory with com.cyster.example.impl.ExampleServiceImpl.Factory;
}
