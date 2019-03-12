package org.mvnsearch;

import org.mvnsearch.service.HelloReply;
import org.mvnsearch.service.HelloRequest;
import org.mvnsearch.service.ReactorGreeterGrpc;
import reactor.core.publisher.Mono;

/**
 * reactive greeter implementation
 *
 * @author linux_china
 */
public class ReactiveGreeterImpl extends ReactorGreeterGrpc.GreeterImplBase {
    @Override
    public Mono<HelloReply> sayHello(Mono<HelloRequest> request) {
        return Mono.just(HelloReply.newBuilder().setMessage("echo").build());
    }

    @Override
    public Mono<HelloReply> sayHelloAgain(Mono<HelloRequest> request) {
        return Mono.just(HelloReply.newBuilder().setMessage("echo again").build());
    }
}
