package Estalinho.estalinho.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "User")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
