package Estalinho.estalinho.service;

import Estalinho.estalinho.domain.user.Enum.EstadoConsultaEnum;
import Estalinho.estalinho.domain.user.Enum.TipoUsuarioEnum;
import Estalinho.estalinho.domain.user.dto.appointment.AppointmentResponseDTO;
import Estalinho.estalinho.domain.user.entity.Appointment;
import Estalinho.estalinho.exception.AlreadyExistException;
import Estalinho.estalinho.exception.DisableException;
import Estalinho.estalinho.exception.InvalidParameterException;
import Estalinho.estalinho.exception.NotFoundException;
import Estalinho.estalinho.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserService userService;

    public List<AppointmentResponseDTO> listAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentResponseDTO::fromAppointment)
                .collect(Collectors.toList());
    }

    public Optional<AppointmentResponseDTO> findById(long id) {
        if (id <= 0) throw new InvalidParameterException("Verifique o campo id da consulta!");

        Optional<Appointment> respDTO = appointmentRepository.findById(id);
        if (respDTO.isEmpty()) throw new NotFoundException("Ops, essa consulta não foi encontrada!");
        if (respDTO.get().getEstadoConsultaEnum() == EstadoConsultaEnum.DESATIVO)
            throw new DisableException("Essa consulta foi desativada!");

        return Optional.of(AppointmentResponseDTO.fromAppointment(respDTO.get()));
    }

    public List<AppointmentResponseDTO> findByUser(long id) {
        if (userService.findById(id).isEmpty()) throw new InvalidParameterException("Ops, esse usuario não exite!");

        List<Appointment> respDTO = appointmentRepository.findByFkPaciente(id);
        if (respDTO.isEmpty()) throw new NotFoundException("Ops, nenhuma consulta foi encontrada!");

        return respDTO.stream()
                .map(AppointmentResponseDTO::fromAppointment)
                .collect(Collectors.toList());
    }

    public List<AppointmentResponseDTO> findingAppointmentsByMedico(long id) {
        if (userService.findById(id).isEmpty()) throw new InvalidParameterException("Ops, esse usuario não existe!");

        List<Appointment> respDTO = appointmentRepository.findByFkMedico(id);

        return respDTO.stream()
                .map(AppointmentResponseDTO::fromAppointment)
                .collect(Collectors.toList());
    }

    public void validAppointmentDate(long idPaciente, long idMedico, LocalDate appointmentDate) {
        if(idPaciente == 0) throw new InvalidParameterException("Ops, informe um paciente para a consulta!");
        if(idMedico == 0) throw new InvalidParameterException("Ops, informe um medico para a consulta!");
        if(appointmentDate.isBefore(LocalDate.now())) throw new InvalidParameterException("Ops, essa data já passou. Insira uma data valida para sua consulta!");

        boolean existAppointment = appointmentRepository.existsByDataConsultaAndFkPaciente(appointmentDate, idPaciente);
        if (existAppointment) throw new AlreadyExistException("Esse paciente já tem uma consulta marcada nesse dia!");

        List<AppointmentResponseDTO> appointmentsMedico = findingAppointmentsByMedico(idMedico);
        boolean hasAppointment = appointmentsMedico.stream().anyMatch(app -> app.getDataConsulta().equals(appointmentDate));
        if(hasAppointment)
            throw new InvalidParameterException("Ops, o medico está indisponivel nessa data. Informe outra data para sua consulta ou escolha outro medico!");
    }


    public boolean create(Appointment appointment) {
        if (appointment == null) throw new InvalidParameterException("Verifique se todos os campos foram preechidos!");
        if(!userService.findById(appointment.getFkMedico()).get().getTipoUsuarioEnum().equals(TipoUsuarioEnum.MEDICO)) throw new InvalidParameterException("Ops, esse medico não existe!");
        validAppointmentDate(appointment.getFkPaciente(), appointment.getFkMedico(), appointment.getDataConsulta());

        appointment.setDataInclusao(LocalDate.now());
        appointment.setEstadoConsultaEnum(EstadoConsultaEnum.ATIVA);
        appointmentRepository.save(appointment);
        return true;
    }

    public boolean updateOne(Appointment appointment) {
        Optional<Appointment> findedAppointment = appointmentRepository.findById(appointment.getId());
        if(findedAppointment.isEmpty()) throw new NotFoundException("Ops, essa consulta não foi encontrada!");
        Appointment appointmentToUpdate = findedAppointment.get();

        if(appointment.getDataConsulta() != null)
            validAppointmentDate(appointment.getFkPaciente(), appointment.getFkMedico(), appointment.getDataConsulta());

        appointmentToUpdate.updateFields(appointment);
        appointmentToUpdate.setDataAlteracao(LocalDate.now());
        appointmentRepository.save(appointmentToUpdate);
        return true;
    }

    public boolean DeleteOne(long id) {
        if(id <= 0) throw new InvalidParameterException("Verifique o campo id da consulta!");
        if(!appointmentRepository.existsById(id)) throw new NotFoundException("Ops, essa consulta não foi encontrada!");

        appointmentRepository.deleteById(id);
        return true;
    }
}
