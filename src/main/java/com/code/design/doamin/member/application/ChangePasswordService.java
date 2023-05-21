package com.code.design.doamin.member.application;

import com.code.design.doamin.member.dao.PasswordChangeRequest;

/**
 * 1. 행위를 표현하고 있다. -> 클래스명으로 책임을 명시만 하더라도 많은 효과를 볼 수 있음
 * 2. 인터페이스는 다형성이 있음 -> 책임이 명확하기 때문에
 * 3. 메소드가 1개여야 하나 ? ...
 */
public interface ChangePasswordService {
    public void change(Long id, PasswordChangeRequest dto);
}
