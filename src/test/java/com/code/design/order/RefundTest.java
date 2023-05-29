package com.code.design.order;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

import com.code.design.doamin.account.domain.Account;
import com.code.design.doamin.account.domain.CreditCard;
import com.code.design.doamin.model.Address;
import com.code.design.doamin.order.order.domain.Order;
import com.code.design.doamin.order.order.domain.Refund;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RefundTest {

    private Order order;
    private Account account;
    private CreditCard creditCard;

    @BeforeEach
    public void setUp() {
        final Address address = Address.builder()
                .address1("서울시 관악구 293-1")
                .address2("201호")
                .zip("503-23")
                .build();

        account = Account.builder()
                .accountHolder("홍길동")
                .accountNumber("110-2304-22344")
                .bankName("신한은행")
                .build();

        creditCard = CreditCard.builder()
                .creditNumber("110-22345-22345")
                .creditHolder("홍길동")
                .build();

        order = Order.builder()
                .address(address)
                .build();
    }

    @Test
    public void ByAccountBuilder_test_account_null이면_exception() {

        thenThrownBy(() -> Refund.ByAccountBuilder()
                .account(null)
                .order(order)
                .build()
        )
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void ByAccountBuilder_testorder_null이면_exception() {
        thenThrownBy(() -> Refund.ByAccountBuilder()
                .account(account)
                .order(null)
                .build()
        )
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    public void ByAccountBuilder_test() {
        final Refund refund = Refund.ByAccountBuilder()
                .account(account)
                .order(order)
                .build();

        then(refund.getAccount()).isEqualTo(account);
        then(refund.getOrder()).isEqualTo(order);
    }


    // 계좌번호 환불의 경우 account null 보내는 경우
    @Test
    public void ByCreditBuilder_test_account_null이면_exception() {
        thenThrownBy(() -> Refund.ByAccountBuilder()
                .account(null)
                .order(order)
                .build()
        )
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void refund_신용_카드_환불() {
        final Refund refund = Refund.ByCreditCardBuilder()
                .creditCard(creditCard)
                .order(order)
                .build();

        then(refund.getAccount()).isNull();;
        then(refund.getCreditCard()).isEqualTo(creditCard);
        then(refund.getOrder()).isEqualTo(order);
    }

    @Test
    public void refund_신용_카드_환불_의경우_credit_card_null_보내는_경우() {
        thenThrownBy(() -> Refund.ByCreditCardBuilder()
                        .creditCard(null)
                        .order(order)
                        .build()
        )
                .isInstanceOf(IllegalArgumentException.class);
    }

//    @Test
//    public void ByCreditBuilder_test_order_null이면_exception() {
//        thenThrownBy(() -> Refund.ByCreditcBuilder()
//                .creditCard(creditCard)
//                .order(null)
//                .build()
//        )
//                .isInstanceOf(IllegalArgumentException.class);
//    }
}