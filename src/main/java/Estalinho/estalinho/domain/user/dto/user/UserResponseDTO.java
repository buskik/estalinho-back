package Estalinho.estalinho.domain.user.dto.user;

import Estalinho.estalinho.domain.user.Enum.EstadoUsuarioEnum;
import Estalinho.estalinho.domain.user.Enum.TipoUsuarioEnum;
import Estalinho.estalinho.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuarioEnum tipoUsuarioEnum;
    private EstadoUsuarioEnum estadoUsuarioEnum;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;

    public static UserResponseDTO fromUser(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .cpf(user.getCpf())
                .nome(user.getNome())
                .email(user.getEmail())
                .senha(user.getSenha())
                .tipoUsuarioEnum(user.getTipoUsuarioEnum())
                .estadoUsuarioEnum(user.getEstadoUsuarioEnum())
                .dataInclusao(user.getDataInclusao())
                .dataAlteracao(user.getDataAlteracao())
                .build();
    }
}
