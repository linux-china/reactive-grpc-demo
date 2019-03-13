package org.mvnsearch.application;

import org.lognet.springboot.grpc.GRpcService;
import org.mvnsearch.domain.AccountService;
import org.mvnsearch.service.AccountResponse;
import org.mvnsearch.service.GetAccountRequest;
import org.mvnsearch.service.ReactorAccountServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Mono<AccountResponse> findAccountById(Mono<GetAccountRequest> request) {
        return request.map(GetAccountRequest::getId)
                .flatMap(accountService::findById)
                .map(AccountMapper.INSTANCE::pojoToProtobuf);
    }
}
