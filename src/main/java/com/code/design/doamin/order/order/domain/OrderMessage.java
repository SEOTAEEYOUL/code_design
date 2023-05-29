package com.code.design.doamin.order.order.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// import org.hibernate.annotations.Type;
// import org.jetbrains.annotations.Contract;
// import org.jetbrains.annotations.NotNull;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;


/**
 * 1. 주문시 주문완료 메시지에 대한 책임을 갖는다.
 * 2. SMS, EMAIL, KAKAO
 */

@Embeddable
@Getter
public class OrderMessage {


    @Column(name = "type", nullable = false)
    final private String type_;

    private OrderMessage(String type_) {
        this.type_ = ObjectUtils.isEmpty(type_) ? null : type_;
    }

    // @Contract("_ -> new")
    public static OrderMessage of(Set<MessageType> types) {
        return new OrderMessage(joining(types));
    }

    public List<MessageType> getTypes() {
        if (ObjectUtils.isEmpty(type_)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(doSplit());
    }

    private static String joining(Set<MessageType> types) {
        return types.stream()
            .map(Enum::name)
            .collect(Collectors.joining(","));
    }

    private Set<MessageType> doSplit() {
        final String[] split = this.type_.split(",");
        return Arrays.stream(split)
            .map(MessageType::valueOf)
            .collect(Collectors.toSet());
    }
}
