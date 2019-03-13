package org.mvnsearch.domain;

import reactor.core.publisher.Mono;

/**
 * account service with reactive support
 *
 * @author linux_china
 */
public interface AccountService {
    Mono<Account> findById(Integer id);
}
