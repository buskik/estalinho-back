package Estalinho.estalinho.repository;

import Estalinho.estalinho.domain.user.entity.Appointment;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByFkPaciente(Long id);
    List<Appointment> findByFkMedico(Long id);
    boolean existsByDataConsultaBetweenAndFkPaciente(LocalDateTime start, LocalDateTime end, long idPaciente);
    boolean existsByDataConsultaBetweenAndFkMedico(LocalDateTime start, LocalDateTime end, long idMedico);
    List<Appointment> findByDataConsultaBetween(@NotNull(message = "Preecha o campo 'Data de Consulta'") LocalDateTime dataConsultaAfter, @NotNull(message = "Preecha o campo 'Data de Consulta'") LocalDateTime dataConsultaBefore);
}
