package Estalinho.estalinho.domain.user.dto.appointment;

import Estalinho.estalinho.domain.user.Enum.EstadoConsultaEnum;
import Estalinho.estalinho.domain.user.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequestDTO {
    private long id;
    private long fkMedico;
    private long fkPaciente;
    private LocalDateTime dataConsulta;
    private EstadoConsultaEnum estadoConsultaEnum;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;

    public static Appointment fromAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        return Appointment.builder()
                .id(appointmentRequestDTO.getId())
                .fkMedico(appointmentRequestDTO.getFkMedico())
                .fkPaciente(appointmentRequestDTO.getFkPaciente())
                .dataConsulta(appointmentRequestDTO.getDataConsulta())
                .estadoConsultaEnum(appointmentRequestDTO.getEstadoConsultaEnum())
                .dataInclusao(appointmentRequestDTO.getDataInclusao())
                .dataAlteracao(appointmentRequestDTO.getDataAlteracao())
                .build();
    }
}
