package com.backend.spring.rest.controllers;



import com.backend.spring.rest.models.Domicilio;
import com.backend.spring.rest.models.Paciente;
import com.backend.spring.rest.payload.request.PacienteRequest;
import com.backend.spring.rest.security.services.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(("/api/pacientes"))

public class PacienteController {

    private TurnoService turnoService;

    public PacienteController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }


    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Paciente> saveDomicilio(@RequestBody PacienteRequest pacienteRequest){
        Domicilio savedDomicilio = turnoService.saveDomicilio(pacienteRequest);
      ;return new ResponseEntity<Paciente>(turnoService.getPacienteById(savedDomicilio.getId()), HttpStatus.CREATED);
    };
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Paciente> getAllPacientes(){
        return turnoService.getAllPacientes();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable("id") long pacienteId){
        return new ResponseEntity<Paciente>(turnoService.getPacienteById(pacienteId), HttpStatus.OK);
    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable("id") long pacienteId,@RequestBody Paciente paciente){
        return new ResponseEntity<Paciente>(turnoService.updatePaciente(paciente, pacienteId), HttpStatus.OK);}

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePacienteBy(@PathVariable("id") long pacienteId){
        turnoService.deletePaciente(pacienteId);
        return new ResponseEntity<String>("Paciente deleted succesfully!.", HttpStatus.OK);
    }

}
