package com.code.design.doamin.account.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "account_holder", nullable = false)
    private String accountHolder;

    // 불안전한 객채 생성 패턴
    // 그냥 단순하게 검증 아니라, 객체의 본인의 책임을 다하는 코드로 변경 했음
    // 해당 객체가 본인의 책임을 다하고 있지 않은 상태 -> 본인의 책임을 다하고 있다고 한다면 불완전한 객체를 생성하지 못하게 막아야 합니다.
    // 본인이 본인의 책임을 다하지 않으며, 그 책임은 다른 객체로 넘어가게 됩니다.
    @Builder
    public Account(final String bankName, final String accountNumber, final String accountHolder) {
        Assert.hasText(bankName, "bankName mut not be empty");
        Assert.hasText(accountNumber, "accountNumber mut not be empty"); // 특수문자 제거 or "-"  제거
        Assert.hasText(accountHolder, "accountHolder mut not be empty");

        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }
}