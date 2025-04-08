package Estalinho.estalinho.controller;

import Estalinho.estalinho.domain.user.dto.appointment.AppointmentRequestDTO;
import Estalinho.estalinho.domain.user.dto.appointment.AppointmentResponseDTO;
import Estalinho.estalinho.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointment/")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> listAll() {
        return ResponseEntity.ok().body(appointmentService.listAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        boolean wasCreated = appointmentService.create(AppointmentRequestDTO.fromAppointment(appointmentRequestDTO));
        return wasCreated ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateOne(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        boolean responseDTO = appointmentService.updateOne(AppointmentRequestDTO.fromAppointment(appointmentRequestDTO));
        return responseDTO ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteOne(@PathVariable("id") long id) {
        boolean responseDTO = appointmentService.DeleteOne(id);
        return responseDTO ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    };
}
