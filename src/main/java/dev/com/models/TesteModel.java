package dev.com.models;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

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
