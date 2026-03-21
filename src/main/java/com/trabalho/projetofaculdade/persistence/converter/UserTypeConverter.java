package com.trabalho.projetofaculdade.persistence.converter;

import com.trabalho.projetofaculdade.model.Enums.BookGenreEnum;
import com.trabalho.projetofaculdade.model.Enums.UserTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserTypeEnum userTypeEnum) {
        return (userTypeEnum == null) ? null : userTypeEnum.getId();
    }

    @Override
    public UserTypeEnum convertToEntityAttribute(Integer integer) {
        return UserTypeEnum.of(integer);
    }
}
