package com.trabalho.projetofaculdade.model.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    Teacher(1,"Teacher"),
    Staff(2, "Staff"),
    Student(3, "Student");

    private final int id;
    private final String description;

    public static UserTypeEnum of(Integer id) {
        if (id == null) return null;
        for (UserTypeEnum c : values()) {
            if (c.id == id) return c;
        }
        return null;
    }

}
