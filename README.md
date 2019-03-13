Reactive-gPRC-demo
===================

Integrates reactive programming with grpc-java.

### Development Flow

* Write your protobuf IDL
* Compile protobuf:  'mvn protobuf:compile'
* Implement reactive gPRC service with Reactor Grpc classes: delegate logic to reactive service handler
* 
```
public class ReactiveGreeterImpl extends ReactorGreeterGrpc.GreeterImplBase {
}
```

* Build project: 'mvn -DskipTests clean package'

### Mapper between domain model and Protobuf message

Please refer MapStruct http://mapstruct.org

### References

* Reactive gPRC: https://github.com/salesforce/reactive-grpc
* Evans: https://github.com/ktr0731/evans
