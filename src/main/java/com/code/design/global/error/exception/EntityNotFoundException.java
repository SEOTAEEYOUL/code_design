package com.code.design.global.error.exception;

import com.code.design.global.error.exception.BusinessException;
import com.code.design.global.error.exception.ErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
