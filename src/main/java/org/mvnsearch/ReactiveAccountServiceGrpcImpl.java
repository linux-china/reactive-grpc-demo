package org.mvnsearch;

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

    @Override
    public Mono<AccountResponse> findAccountById(Mono<GetAccountRequest> request) {
        return request.map(getAccountRequest -> AccountResponse.newBuilder().setId(getAccountRequest.getId()).setNick("linux_china").build());
    }
}
