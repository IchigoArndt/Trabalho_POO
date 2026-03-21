package com.trabalho.projetofaculdade.dto.user;

import com.trabalho.projetofaculdade.model.Enums.UserTypeEnum;
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
public class UserResponseDTO {
    private Long Id;
    private String Document;
    private String Name;
    private String MotherName;
    private Integer areaCode;
    private Long Celphone;
    private String Email;
    private LocalDate BirthDate;
    private String RegistrationNumber;
    private UserTypeEnum UserType;
    private LocalDateTime CreatedAt;
    private Boolean Active;
}
