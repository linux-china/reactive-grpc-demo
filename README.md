Reactive gRPC Spring Boot Demo
==============================

Spring Boot Application with reactive gRPC support backbone by https://github.com/salesforce/reactive-grpc and https://github.com/LogNet/grpc-spring-boot-starter

### Development


* Modify protobuf-maven-plugin with 'reactor-grpc' protocPlugin support

```xml
<plugin>
   <groupId>org.xolstice.maven.plugins</groupId>
   <artifactId>protobuf-maven-plugin</artifactId>
   <version>0.6.1</version>
   <configuration>
       <protocArtifact>com.google.protobuf:protoc:${protobuf-java.version}:exe:${os.detected.classifier}
       </protocArtifact>
       <pluginId>grpc-java</pluginId>
       <pluginArtifact>io.grpc:protoc-gen-grpc-java:${grpc.version}:exe:${os.detected.classifier}
       </pluginArtifact>
       <protocPlugins>
           <protocPlugin>
               <id>reactor-grpc</id>
               <groupId>com.salesforce.servicelibs</groupId>
               <artifactId>reactor-grpc</artifactId>
               <version>${reactive-grpc.version}</version>
               <mainClass>com.salesforce.reactorgrpc.ReactorGrpcGenerator</mainClass>
           </protocPlugin>
       </protocPlugins>
   </configuration>
   <executions>
       <execution>
           <goals>
               <goal>compile</goal>
               <goal>compile-custom</goal>
           </goals>
       </execution>
   </executions>
</plugin>
```

* Implement gRPC service to extend reactive class

```
@GRpcService
public class ReactiveAccountServiceGrpcImpl extends ReactorAccountServiceGrpc.AccountServiceImplBase {
    @Autowired
    private AccountService accountService;

    @Override
    public Mono<AccountResponse> findAccountById(Mono<GetAccountRequest> request) {
        return request.map(GetAccountRequest::getId)
                .flatMap(accountService::findById)
                .map(AccountMapper.INSTANCE::pojoToProtobuf);
    }
}
```

* Start Spring Boot application and test with evans

```
evans src/main/proto/account.proto
```

### Mapper between domain model and Protobuf message

Please refer MapStruct http://mapstruct.org

### References

* Reactive gPRC: https://github.com/salesforce/reactive-grpc
* Spring Boot starter module for gRPC framework: https://github.com/LogNet/grpc-spring-boot-starter
* Evans: https://github.com/ktr0731/evans
