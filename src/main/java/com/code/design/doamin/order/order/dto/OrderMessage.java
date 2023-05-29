package com.code.design.doamin.order.order.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.code.design.validation.domain.MessageType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;


/**
 * 1. 주문시 주문완료 메시지에 대한 책임을 갖는다.
 * 2. SMS, EMAIL, KAKAO
 */
public class OrderMessage {

    final private String type;

    private OrderMessage(String type) {
        this.type = ObjectUtils.isEmpty(type) ? null : type;
    }

    @Contract("_ -> new")
    public static @NotNull OrderMessage of(Set<MessageType> types) {
        return new OrderMessage(joining(types));
    }

    public List<MessageType> getTypes() {
        if (ObjectUtils.isEmpty(type)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(doSplit());
    }

    private static String joining(@NotNull Set<MessageType> types) {
        return types.stream()
            .map(Enum::name)
            .collect(Collectors.joining(","));
    }

    private Set<MessageType> doSplit() {
        final String[] split = this.type.split(",");
        return Arrays.stream(split)
            .map(MessageType::valueOf)
            .collect(Collectors.toSet());
    }
}
