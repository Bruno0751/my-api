package dev.com.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
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
@Table(name = "cliente")
public class ClienteModel extends RepresentationModel<ClienteModel> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCliente;

    @NotBlank(message = "O campo 'name' não pode ser vazio. Por favor, forneça um valor válido.")
    @Column(name = "name", nullable = false)
    private String name;

    @Nullable
    @Column(name = "old_year")
    private Integer oldYear;

    @Nullable
    @Column(name = "heigth")
    private Double heigth;

    @NotNull(message = "O campo 'email' não pode ser null. Por favor, forneça um valor válido.")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "O campo 'data_time' não pode ser null. Por favor, forneça um valor válido.")
    @Column(name = "data_time", nullable = false)
    private Timestamp dataTime;

}
