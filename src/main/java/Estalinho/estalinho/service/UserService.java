package Estalinho.estalinho.service;

import Estalinho.estalinho.domain.user.dto.UserResponseDTO;
import Estalinho.estalinho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> listAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::fromUserAdmin)
                .collect(Collectors.toList());
    }
}
