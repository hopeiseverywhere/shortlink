package com.fran.shortlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Valid Date Type
 */
@RequiredArgsConstructor
public enum ValidDateTypeEnum {
    /**
     * Period: permanent
     */
    PERMANENT(0),

    CUSTOM(1);

    @Getter
    private final int type;
}
