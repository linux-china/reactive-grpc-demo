package org.mvnsearch;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mvnsearch.service.AccountResponse;
import org.mvnsearch.service.GetAccountRequest;
import org.mvnsearch.service.ReactorAccountServiceGrpc;
import reactor.core.publisher.Mono;

/**
 * reactor service test
 *
 * @author linux_china
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReactorServiceTest {
    private ManagedChannel channel;
    private ReactorAccountServiceGrpc.ReactorAccountServiceStub stub;

    @BeforeAll
    public void setUp() {
        channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        stub = ReactorAccountServiceGrpc.newReactorStub(channel);
    }

    @AfterAll
    public void tearDown() {
        channel.shutdownNow();
    }

    /**
     * test for say hello
     */
    @Test
    public void testSayHello() throws Exception {
        Mono<GetAccountRequest> request = Mono.just(GetAccountRequest.newBuilder().setId(1).build());
        request
                // Call service
                .as(stub::findAccountById)
                // Map response
                .map(AccountResponse::getNick)
                .subscribe(System.out::println);

        Thread.sleep(1000);
    }
}
