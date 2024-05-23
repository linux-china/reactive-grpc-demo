package org.mvnsearch.application;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Int32Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mvnsearch.domain.Account;
import org.mvnsearch.service.AccountResponse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

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

  @Test
  public void testInstance() throws Exception {
    Account account = new Account(1, "linux_china");
    AccountResponse accountResponse = AccountMapper.INSTANCE.pojoToProtobuf(account);
    byte[] bytes = accountResponse.toByteArray();
    Method parseFrom = AccountResponse.class.getMethod("parseFrom", byte[].class);
    AccountResponse accountResponse1 = (AccountResponse) parseFrom.invoke(null, bytes);
    System.out.println(accountResponse1.getNick());
  }

  @Test
  public void testAssignFrom() {
    System.out.println(AccountResponse.class.getSuperclass().equals(AbstractMessage.class));
    System.out.println(Int32Value.class.isAssignableFrom(AbstractMessage.class));
    System.out.println(ArrayList.class.isAssignableFrom(List.class));
    System.out.println(Set.class.isAssignableFrom(SortedSet.class)); // true

  }
}
