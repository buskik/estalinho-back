package Estalinho.estalinho.domain.user.entity;

import Estalinho.estalinho.domain.user.Enum.EstadoUsuarioEnum;
import Estalinho.estalinho.domain.user.Enum.TipoUsuarioEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Table(name = "TB_USUARIOS")
@Entity(name = "TB_USUARIOS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Preecha o campo 'CPF'")
    private String cpf;

    @NotNull(message = "Preecha o campo 'Nome'")
    private String nome;

    @Email
    @NotNull(message = "Preecha o campo 'email'")
    private String email;

    @NotNull(message = "Preecha o campo 'senha'")
    private String senha;

    @NotNull(message = "Preecha o campo 'Tipo Usuario'")
    @Enumerated(EnumType.ORDINAL)
    private TipoUsuarioEnum tipoUsuarioEnum;

    @NotNull(message = "Preecha o campo 'Estado Usuario'")
    @Enumerated(EnumType.ORDINAL)
    private EstadoUsuarioEnum estadoUsuarioEnum;

    @NotNull(message = "Preecha o campo 'Data de Inclusão'")
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;


    public void updateFields(User newData) {
        if (newData.getNome() != null && !newData.getNome().equals(this.nome)) {
            this.nome = newData.getNome();
        }
        if (newData.getEmail() != null && !newData.getEmail().equals(this.email)) {
            this.email = newData.getEmail();
        }
        if (newData.getSenha() != null && !newData.getSenha().equals(this.senha)) {
            this.senha = newData.getSenha();
        }
        if (newData.getTipoUsuarioEnum() != null && !newData.getTipoUsuarioEnum().equals(this.tipoUsuarioEnum)) {
            this.tipoUsuarioEnum = newData.getTipoUsuarioEnum();
        }
        if (newData.getEstadoUsuarioEnum() != null && !newData.getEstadoUsuarioEnum().equals(this.estadoUsuarioEnum)) {
            this.estadoUsuarioEnum = newData.getEstadoUsuarioEnum();
        }
        if (newData.getDataInclusao() != null && !newData.getDataInclusao().equals(this.dataInclusao)) {
            this.dataInclusao = newData.getDataInclusao();
        }
        if (newData.getDataAlteracao() != null) {
            this.dataAlteracao = newData.getDataAlteracao();
        }
    }
}
