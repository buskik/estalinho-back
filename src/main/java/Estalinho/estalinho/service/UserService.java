package Estalinho.estalinho.service;

import Estalinho.estalinho.domain.user.Enum.EstadoUsuarioEnum;
import Estalinho.estalinho.domain.user.dto.user.UserResponseDTO;
import Estalinho.estalinho.domain.user.entity.User;
import Estalinho.estalinho.exception.AlreadyExistException;
import Estalinho.estalinho.exception.DisableException;
import Estalinho.estalinho.exception.InvalidParameterException;
import Estalinho.estalinho.exception.NotFoundException;
import Estalinho.estalinho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> listAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::fromUser)
                .collect(Collectors.toList());
    }

    public Optional<UserResponseDTO> findById(long id) {
        if (id <= 0) throw new InvalidParameterException("Verifique o campo id do cliente!");

        Optional<User> respDTO = userRepository.findById(id);
        if (respDTO.isEmpty()) throw new NotFoundException("Ops, esse usuario não foi encontrado!");
        if (respDTO.get().getEstadoUsuarioEnum() == EstadoUsuarioEnum.DESATIVO)
            throw new DisableException("Esse usuario foi desativado!");

        return Optional.of(UserResponseDTO.fromUser(respDTO.get()));
    }

    public Optional<UserResponseDTO> findByCpf(String cpf) {
        if (cpf.isEmpty()) throw new InvalidParameterException("Verifique o campo cpf do cliente!");

        Optional<User> respDTO = userRepository.findUserByCpf(cpf);
        if (respDTO.isEmpty()) throw new NotFoundException("Ops, esse usuario não foi encontrado!");
        if (respDTO.get().getEstadoUsuarioEnum() == EstadoUsuarioEnum.DESATIVO)
            throw new DisableException("Esse usuario foi desativado!");

        return Optional.of(UserResponseDTO.fromUser(respDTO.get()));
    }

    public boolean create(User usuario) {
        if (usuario == null) throw new InvalidParameterException("Verifique se todos os campos foram preechidos!");
        Optional<User> user = userRepository.findUserByCpf(usuario.getCpf());
        if (user.isPresent()) throw new AlreadyExistException("Esse cpf já está cadastrado!");

        usuario.setDataInclusao(LocalDate.now());
        usuario.setEstadoUsuarioEnum(EstadoUsuarioEnum.ATIVO);
        userRepository.save(usuario);
        return true;
    }

    public boolean updateOne(User usuario) {
        Optional<User> user = userRepository.findUserByCpf(usuario.getCpf());
        if(user.isEmpty()) throw new NotFoundException("Ops, esse usuario não foi encontrado!");
        User userToUpdate = user.get();
        userToUpdate.updateFields(usuario);
        userToUpdate.setDataAlteracao(LocalDate.now());

        userRepository.save(userToUpdate);
        return true;
    }

    public boolean DeleteOne(long id) {
        if(id <= 0) throw new InvalidParameterException("Verifique o campo id do usuario!");
        if(!userRepository.existsById(id)) throw new NotFoundException("Ops, esse usuario não foi encontrado!");

        userRepository.deleteById(id);
        return true;
    }
}
