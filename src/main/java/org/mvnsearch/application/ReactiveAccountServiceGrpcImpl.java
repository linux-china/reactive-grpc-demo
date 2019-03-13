package org.mvnsearch.application;

import org.mvnsearch.domain.AccountService;
import org.mvnsearch.domain.impl.AccountServiceImpl;
import org.mvnsearch.service.AccountResponse;
import org.mvnsearch.service.GetAccountRequest;
import org.mvnsearch.service.ReactorAccountServiceGrpc;
import reactor.core.publisher.Mono;

/**
 * reactive account service gRPC implementation
 *
 * @author linux_china
 */
public class ReactiveAccountServiceGrpcImpl extends ReactorAccountServiceGrpc.AccountServiceImplBase {
    //reactive service handler
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public Mono<AccountResponse> findAccountById(Mono<GetAccountRequest> request) {
        return request.map(GetAccountRequest::getId)
                .flatMap(accountService::findById)
                .map(AccountMapper.INSTANCE::pojoToProtobuf);
    }
}
