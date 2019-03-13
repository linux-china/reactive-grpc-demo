package org.mvnsearch.domain.impl;

import org.mvnsearch.domain.Account;
import org.mvnsearch.domain.AccountService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * account service implementation
 *
 * @author linux_china
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Mono<Account> findById(final Integer id) {
        return Mono.fromCallable(() -> new Account(id, "nick:" + id));
    }
}
