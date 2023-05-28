package com.nikolay.legacy.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventType {

    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String type;
}
