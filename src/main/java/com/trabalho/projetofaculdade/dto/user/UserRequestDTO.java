package com.trabalho.projetofaculdade.dto.user;

import com.trabalho.projetofaculdade.model.Enums.UserTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotNull
    private String Document;

    @NotNull
    private String Name;

    @NotNull
    private String MotherName;

    @Positive
    private Integer areaCode;

    @Positive
    @NotNull
    private Long Celphone;

    @NotNull
    private String Email;

    @NotNull
    private LocalDate BirthDate;

    @NotNull
    private String RegistrationNumber;

    @NotNull
    private UserTypeEnum UserType;

    @NotNull
    private LocalDateTime CreatedAt;

    @NotNull
    private Boolean Active;
}
