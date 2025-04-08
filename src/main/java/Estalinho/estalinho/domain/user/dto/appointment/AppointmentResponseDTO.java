package Estalinho.estalinho.domain.user.dto.appointment;

import Estalinho.estalinho.domain.user.Enum.EstadoConsultaEnum;
import Estalinho.estalinho.domain.user.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponseDTO {
    private long id;
    private long fkMedico;
    private long fkPaciente;
    private LocalDate dataConsulta;
    private EstadoConsultaEnum estadoConsultaEnum;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;

    public static AppointmentResponseDTO fromAppointment(Appointment appointment) {
        return AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .fkMedico(appointment.getFkMedico())
                .fkPaciente(appointment.getFkPaciente())
                .dataConsulta(appointment.getDataConsulta())
                .estadoConsultaEnum(appointment.getEstadoConsultaEnum())
                .dataInclusao(appointment.getDataInclusao())
                .dataAlteracao(appointment.getDataAlteracao())
                .build();
    }
}
