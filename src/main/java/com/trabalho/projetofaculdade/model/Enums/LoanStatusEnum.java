package com.trabalho.projetofaculdade.model.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoanStatusEnum {
        ACTIVE(1,"ACTIVE"),
        RETURNED(2,"RETURNED"),
        OVERDUE(3,"OVERDUE");

    private final int id;
    private final String description;

    public static LoanStatusEnum of(Integer id) {
        if (id == null) return null;
        for (LoanStatusEnum c : values()) {
            if (c.id == id) return c;
        }
        return null;
    }
}
