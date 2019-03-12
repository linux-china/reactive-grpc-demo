package org.mvnsearch;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mvnsearch.service.HelloReply;
import org.mvnsearch.service.HelloRequest;
import org.mvnsearch.service.ReactorGreeterGrpc;
import reactor.core.publisher.Mono;

/**
 * reactor service test
 *
 * @author linux_china
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReactorServiceTest {
    private ManagedChannel channel;
    private ReactorGreeterGrpc.ReactorGreeterStub stub;

    @BeforeAll
    public void setUp() throws Exception {
        channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext().build();
        stub = ReactorGreeterGrpc.newReactorStub(channel);
    }

    @AfterAll
    public void tearDown() throws Exception {
        channel.shutdownNow();
    }

    /**
     * test for say hello
     */
    @Test
    public void testSayHello() throws Exception {
        Mono<HelloRequest> request = Mono.just(HelloRequest.newBuilder().setName("OSCON").build());
        request
                // Call service
                .as(stub::sayHello)
                // Map response
                .map(HelloReply::getMessage)
                .subscribe(System.out::println);

        Thread.sleep(1000);
    }
}
