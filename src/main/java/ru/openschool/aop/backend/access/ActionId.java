package ru.openschool.aop.backend.access;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActionId {
    VIEW(1l),
    FULL(2l);

    private Long id;
}
