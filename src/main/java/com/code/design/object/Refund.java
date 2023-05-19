package com.code.design.object;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "refund")
@Getter
@NoArgsConstructor
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Account account;

    @Embedded
    private CreditCard creditCard;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order1 order;


    // 1. account -> null, creditCard -> notnull
    // 2. account -> notnull, creditCard -> null
    // 3. account -> null, creditCard -> null
    // 4. account -> notnull, creditCard -> notnull


    // builderClassName = "환불사유를적음"
    @Builder(builderClassName = "ByAccountBuilder", builderMethodName = "ByAccountBuilder") // 계좌 번호 기반 환불, Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
    public Refund(Account account, Order1 order) {
        Assert.notNull(account, "account must not be null");
        Assert.notNull(order, "order must not be null");

        this.order = order;
        this.account = account;
    }

    @Builder(builderClassName = "ByCreditCardBuilder", builderMethodName = "ByCreditCardBuilder")  // 신용 카드 기반 환불, Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
    public Refund(CreditCard creditCard, Order1 order) {
        Assert.notNull(creditCard, "creditCard must not be null");
        Assert.notNull(order, "order must not be null");

        this.order = order;
        this.creditCard = creditCard;
    }
}