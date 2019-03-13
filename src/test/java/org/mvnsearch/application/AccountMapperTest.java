package org.mvnsearch.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mvnsearch.domain.Account;
import org.mvnsearch.service.AccountResponse;

/**
 * Account mapper test
 *
 * @author linux_china
 */
public class AccountMapperTest {

    @Test
    public void testMap() {
        Account account = new Account(1, "linux_china");
        AccountResponse accountResponse = AccountMapper.INSTANCE.pojoToProtobuf(account);
        Assertions.assertEquals(accountResponse.getNick(), account.getNick());
        Account account2 = AccountMapper.INSTANCE.protobufToPojo(accountResponse);
        Assertions.assertEquals(account2.getNick(), account.getNick());
    }
}
