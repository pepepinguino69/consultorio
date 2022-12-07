package com.backend.spring.rest.controllers;



import com.backend.spring.rest.models.Domicilio;

import com.backend.spring.rest.security.services.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/domicilios/"))
@CrossOrigin(origins="*",exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})

public class DomicilioController {

    private TurnoService turnoService;

    public DomicilioController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Domicilio> getAllDomicilios(){
        return turnoService.getAllDomicilios();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Domicilio> getDomicilioById(@PathVariable("id") long domicilioId){
        return new ResponseEntity<Domicilio>(turnoService.getDomicilioById(domicilioId), HttpStatus.OK);
    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Domicilio> updateDomicilio(@PathVariable("id") long domicilioId,@RequestBody Domicilio domicilio){
        return new ResponseEntity<Domicilio>(turnoService.updateDomicilio(domicilio, domicilioId), HttpStatus.OK);}

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteDomicilioBy(@PathVariable("id") long domicilioId){
        turnoService.deleteDomicilio(domicilioId);
        return new ResponseEntity<String>("Domicilio deleted succesfully!.", HttpStatus.OK);
    }

}
