package Estalinho.estalinho.repository;

import Estalinho.estalinho.domain.user.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByFkPaciente(Long id);
    List<Appointment> findByFkMedico(Long id);
    boolean existsByDataConsultaAndFkPaciente(LocalDate dataConsulta, Long pacienteId);
}
