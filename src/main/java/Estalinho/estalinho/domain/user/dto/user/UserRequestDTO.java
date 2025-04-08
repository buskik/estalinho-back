package Estalinho.estalinho.domain.user.dto.user;

import Estalinho.estalinho.domain.user.Enum.EstadoUsuarioEnum;
import Estalinho.estalinho.domain.user.Enum.TipoUsuarioEnum;
import Estalinho.estalinho.domain.user.entity.User;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuarioEnum tipoUsuarioEnum;
    private EstadoUsuarioEnum estadoUsuarioEnum;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;

    public static User fromUser(UserRequestDTO userRequestDTO) {
        return User.builder()
                .cpf(userRequestDTO.getCpf())
                .nome(userRequestDTO.getNome())
                .email(userRequestDTO.getEmail())
                .senha(userRequestDTO.getSenha())
                .tipoUsuarioEnum(userRequestDTO.getTipoUsuarioEnum())
                .estadoUsuarioEnum(userRequestDTO.getEstadoUsuarioEnum())
                .dataInclusao(userRequestDTO.getDataInclusao())
                .dataAlteracao(userRequestDTO.getDataAlteracao())
                .build();
    }
}
