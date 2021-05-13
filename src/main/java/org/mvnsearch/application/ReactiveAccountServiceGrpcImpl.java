package org.mvnsearch.application;

import com.google.protobuf.Int32Value;
import org.lognet.springboot.grpc.GRpcService;
import org.mvnsearch.domain.AccountService;
import org.mvnsearch.service.AccountResponse;
import org.mvnsearch.service.GetAccountRequest;
import org.mvnsearch.service.ReactorAccountServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * reactive account service gRPC implementation
 *
 * @author linux_china
 */
@GRpcService
public class ReactiveAccountServiceGrpcImpl extends ReactorAccountServiceGrpc.AccountServiceImplBase {
    @Autowired
    private AccountService accountService;

    @Override
    public Mono<AccountResponse> findAccount(Mono<GetAccountRequest> request) {
        return request.map(GetAccountRequest::getId)
                .flatMap(accountService::findById)
                .map(AccountMapper.INSTANCE::pojoToProtobuf);
    }

    @Override
    public Mono<AccountResponse> findById(Mono<Int32Value> request) {
        return request.map(Int32Value::getValue)
                .flatMap(accountService::findById)
                .map(AccountMapper.INSTANCE::pojoToProtobuf);
    }

    @Override
    public Flux<AccountResponse> findAccounts(Mono<GetAccountRequest> request) {
        return Flux.just(AccountResponse.newBuilder().setNick("xxx").build());
    }

    @Override
    public Flux<AccountResponse> channelAccounts(Flux<GetAccountRequest> request) {
        return Flux.just(AccountResponse.newBuilder().setNick("xxx").build());
    }
}
