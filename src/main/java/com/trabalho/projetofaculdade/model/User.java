package com.trabalho.projetofaculdade.model;

import com.trabalho.projetofaculdade.model.Enums.UserTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data //Get e Setters
@Builder
@NoArgsConstructor //Construtores vazios
@AllArgsConstructor // Construtores implicitos para todas as propriedades
@Entity
@Table(name = "tbUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Column(nullable = false)
    private String Document;

    @NotNull //A ideia é fazer se required no banco SQL Server
    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    @NotNull
    private String MotherName;

    @Positive
    @Column(name = "area_code", nullable = false)
    private Integer areaCode;

    @Positive
    @Column(name = "CelPhone", nullable = false)
    @NotNull
    private Long Celphone;

    @Column(nullable = false)
    @NotNull
    private String Email;

    @Column(nullable = false, name = "BirthDate")
    @NotNull
    private LocalDate BirthDate;

    @Column(nullable = false, name = "RegistrationNumber")
    @NotNull
    private String RegistrationNumber;

    @Column(nullable = false, name = "UserType")
    @NotNull
    private UserTypeEnum UserType;

    @Column(nullable = false, name = "CreateDate")
    @NotNull
    private LocalDateTime CreatedAt;

    @Column(nullable = false, name = "Status")
    @NotNull
    private Boolean Active;
}
