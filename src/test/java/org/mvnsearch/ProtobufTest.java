package org.mvnsearch;

import com.google.protobuf.FieldMask;
import com.google.protobuf.util.FieldMaskUtil;
import org.junit.jupiter.api.Test;
import org.mvnsearch.service.AccountResponse;


public class ProtobufTest {

    @Test
    public void testFieldMask() {
        FieldMask fieldMask = FieldMaskUtil.fromFieldNumbers(AccountResponse.class, AccountResponse.NICK_FIELD_NUMBER);
        final AccountResponse accountDTO = AccountResponse.newBuilder().setId(1).setNick("nick").build();
        AccountResponse.Builder accountWithMaskedFields = AccountResponse.newBuilder();
        FieldMaskUtil.merge(fieldMask, accountDTO, accountWithMaskedFields);
        final AccountResponse accountResponse = accountWithMaskedFields.build();
        System.out.println("id: " + accountResponse.getId());
        System.out.println("nick: " + accountResponse.getNick());
    }
}
