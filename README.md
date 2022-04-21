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

* Reactive gPRC: <https://github.com/salesforce/reactive-grpc>
* Spring Boot starter module for gRPC framework: <https://github.com/LogNet/grpc-spring-boot-starter>
* Evans: <https://github.com/ktr0731/evans>
* gRPCurl: Like cURL, but for gRPC https://github.com/fullstorydev/grpcurl
* grpcui: web UI for gRPC https://github.com/fullstorydev/grpcui
* xDS-Based Global Load Balancing: https://github.com/grpc/proposal/blob/master/A27-xds-global-load-balancing.md
* Stop Breaking the Proto! Designing for Change in Microservices World https://www.youtube.com/watch?v=hbxOO2wnA1Y&list=PLj6h78yzYM2NN72UX_fdmc5CZI-D5qfJL&index=13
* Practical API Design at Netflix, Part 1: Using Protobuf FieldMask: https://netflixtechblog.com/practical-api-design-at-netflix-part-1-using-protobuf-fieldmask-35cfdc606518
* Protobuf Field Masks: https://pinkiepractices.com/posts/protobuf-field-masks/
* protoc-gen-doc: Documentation generator plugin for Google Protocol Buffers https://github.com/pseudomuto/protoc-gen-doc
* Various gRPC benchmarks: https://github.com/LesnyRumcajs/grpc_bench/wiki
