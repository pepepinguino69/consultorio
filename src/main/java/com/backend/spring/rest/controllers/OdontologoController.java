package com.backend.spring.rest.controllers;

import com.backend.spring.rest.models.Odontologo;
import com.backend.spring.rest.security.services.TurnoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(("/api/odontologos"))
public class OdontologoController {

    private TurnoService turnoService;

    public OdontologoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Odontologo> saveOdontologo(@RequestBody Odontologo odontologo){
        return new ResponseEntity<Odontologo>(turnoService.saveOdontologo(odontologo), HttpStatus.CREATED);
    };

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole(ROLE_MODERATOR) or hasRole('ADMIN')")
    public List<Odontologo> getAllOdontologos(){
        return turnoService.getAllOdontologos();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole(ROLE_MODERATOR) or hasRole('ADMIN')")
    public ResponseEntity<Odontologo> getOdontologoById(@PathVariable("id") long odontologoId){
        return new ResponseEntity<Odontologo>(turnoService.getOdontologoById(odontologoId), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Odontologo> updateOdontologo(@PathVariable("id") long odontologoId,@RequestBody Odontologo odontologo){
        return new ResponseEntity<Odontologo>(turnoService.updateOdontologo(odontologo, odontologoId), HttpStatus.OK);}

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOdontologoBy(@PathVariable("id") long odontologoId){
        turnoService.deleteOdontologo(odontologoId);
        return new ResponseEntity<String>("Odontologo deleted succesfully!.", HttpStatus.OK);
    }

}
