package com.example.demo.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "teste")
public class TesteModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTeste", nullable = false)
    private Long idTeste;

    @NotBlank(message = "O campo 'name' não pode ser vazio. Por favor, forneça um valor válido.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "O campo 'old_year' não pode ser vazio. Por favor, forneça um valor válido.")
    @Column(name = "old_year", nullable = false)
    private int oldYear;

    @Nullable
    @Column(name = "heigth")
    private Double heigth;
}
