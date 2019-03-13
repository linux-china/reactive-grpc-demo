package org.mvnsearch.application;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mvnsearch.domain.Account;
import org.mvnsearch.service.AccountResponse;

/**
 * account mapper
 *
 * @author linux_china
 */
@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountResponse pojoToProtobuf(Account account);

    Account protobufToPojo(AccountResponse accountResponse);
}
