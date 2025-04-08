package Estalinho.estalinho.domain.user.entity;

import Estalinho.estalinho.domain.user.Enum.EstadoConsultaEnum;
import Estalinho.estalinho.exception.InvalidParameterException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Table(name = "TB_CONSULTAS")
@Entity(name = "TB_CONSULTAS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Preecha o campo 'FkMedico'")
    private long fkMedico;
    @NotNull(message = "Preecha o campo 'FkPaciente'")
    private long fkPaciente;
    @NotNull(message = "Preecha o campo 'Data de Consulta'")
    private LocalDate dataConsulta;
    private EstadoConsultaEnum estadoConsultaEnum;
    @NotNull(message = "Preecha o campo 'Data de Inclusão'")
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;

    public void updateFields(Appointment newData) {
        if (newData.getFkMedico() != 0 && newData.getFkMedico() != this.fkMedico) {
            this.fkMedico = newData.getFkMedico();
        }
        if (newData.getFkPaciente() != 0 && newData.getFkPaciente() != this.fkPaciente) {
            this.fkPaciente = newData.getFkPaciente();
        }
        if (newData.getDataConsulta() != null && !newData.getDataConsulta().equals(this.dataConsulta)) {
            this.dataConsulta = newData.getDataConsulta();
        }
        if (newData.getEstadoConsultaEnum() != null && !newData.getEstadoConsultaEnum().equals(this.estadoConsultaEnum)) {
            this.estadoConsultaEnum = newData.getEstadoConsultaEnum();
        }
        if (newData.getDataInclusao() != null && !newData.getDataInclusao().equals(this.dataInclusao)) {
            this.dataInclusao = newData.getDataInclusao();
        }
        if (newData.getDataAlteracao() != null) {
            this.dataAlteracao = newData.getDataAlteracao();
        }
    }
}
