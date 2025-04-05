package Estalinho.estalinho.domain.user.dto;

import Estalinho.estalinho.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;

    public static UserResponseDTO fromUserAdmin(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .build();
    }
}
