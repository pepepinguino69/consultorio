package com.backend.spring.rest.controllers;

import com.backend.spring.rest.security.services.TurnoService;
import com.backend.spring.rest.models.Turno;
import com.backend.spring.rest.payload.request.TurnoRequest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(("/api/turnos"))
public class TurnoController {

    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }


    @PostMapping()
    public ResponseEntity<String> saveTurno(@RequestBody TurnoRequest turnoRequest) throws Exception {
        Turno messageValue =turnoService.saveTurno(Turno.from(turnoRequest));return new ResponseEntity<>(messageValue.message(messageValue) , HttpStatus.CREATED);

                                               //long odontologo_id, long paciente_id, LocalDateTime comienzo_turno, LocalDateTime fin_turno){
       // return new ResponseEntity<Turno>(turnoService.saveTurno(odontologo_id,paciente_id,comienzo_turno,fin_turno), HttpStatus.CREATED);
    };
    @GetMapping
    public List<Turno> getAllTurnos(){
        return turnoService.getAllTurnos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable("id") long turnoId){
        return new ResponseEntity<Turno>(turnoService.getTurnoById(turnoId), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable("id") long turnoId,@RequestBody Turno turno){
        return new ResponseEntity<Turno>(turnoService.updateTurno(turno, turnoId), HttpStatus.OK);}

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTurnoBy(@PathVariable("id") long turnoId){
        turnoService.deleteTurno(turnoId);
        return new ResponseEntity<String>("Turno deleted succesfully!.", HttpStatus.OK);
    }

}
