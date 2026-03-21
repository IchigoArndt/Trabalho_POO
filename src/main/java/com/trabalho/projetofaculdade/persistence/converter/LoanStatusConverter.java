package com.trabalho.projetofaculdade.persistence.converter;

import com.trabalho.projetofaculdade.model.Enums.LoanStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LoanStatusConverter implements AttributeConverter<LoanStatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LoanStatusEnum status) {
        return (status == null) ? null : status.getId();
    }

    @Override
    public LoanStatusEnum convertToEntityAttribute(Integer integer) {
        return LoanStatusEnum.of(integer);
    }
}
