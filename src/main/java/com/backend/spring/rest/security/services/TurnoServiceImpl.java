package com.backend.spring.rest.security.services;

import com.backend.spring.rest.models.Domicilio;
import com.backend.spring.rest.payload.request.PacienteRequest;
import com.backend.spring.rest.repository.DomicilioRepository;
import com.backend.spring.rest.repository.OdontologoRepository;
import com.backend.spring.rest.repository.PacienteRepository;
import com.backend.spring.rest.repository.TurnoRepository;
import com.backend.spring.rest.exception.ResourceNotFoundException;
import com.backend.spring.rest.models.Odontologo;
import com.backend.spring.rest.models.Paciente;
import com.backend.spring.rest.models.Turno;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TurnoServiceImpl implements TurnoService {

    public TurnoRepository turnoRepository;
    private OdontologoRepository odontologoRepository;
    private PacienteRepository pacienteRepository;

    private DomicilioRepository domicilioRepository;


    public TurnoServiceImpl(TurnoRepository turnoRepository, OdontologoRepository odontologoRepository,
                            PacienteRepository pacienteRepository, DomicilioRepository domicilioRepository) {
                this.turnoRepository = turnoRepository;
                this.odontologoRepository = odontologoRepository;
                this.pacienteRepository = pacienteRepository;
                this.domicilioRepository = domicilioRepository;

    }

    @Override
    public Turno saveTurno(Turno turno) throws Exception {


        turno.setPaciente(getPacienteById(turno.getPaciente().getId()));
        turno.setOdontologo(getOdontologoById(turno.getOdontologo().getId()));
        turno.getOdontologo().addTurno(turno);
        turno.getPaciente().addTurno(turno);
        return turnoRepository.save(turno);
    }
    @Override
    public List<Turno> getAllTurnos(){
        return turnoRepository.findAll();
    }
    @Override
    public Turno getTurnoById(long id){
        return turnoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Turno","id",id));
    }
    @Override
    public Turno updateTurno(Turno turno,long id){
        Turno existingTurno= turnoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Turno","id",id));
        existingTurno.setOdontologo(turno.getOdontologo());
        existingTurno.setPaciente(turno.getPaciente());
        existingTurno.setComienzo_turno(turno.getComienzo_turno());
        existingTurno.setFin_turno(turno.getFin_turno());
        turnoRepository.save(existingTurno);
        return existingTurno;
    }
    @Override
    public Turno deleteTurno(long id){
        Turno existingTurno= turnoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Turno","id",id));
        turnoRepository.deleteById(id);
        return existingTurno;


    }
    @Override
    public Odontologo saveOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    @Override
    public List<Odontologo> getAllOdontologos(){
        return odontologoRepository.findAll();
    }
    @Override
    public Odontologo getOdontologoById(long id){
        return odontologoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Odontologo","id",id));
    }
    @Override
    public Odontologo updateOdontologo(Odontologo odontologo,long id){
        Odontologo existingOdontologo= odontologoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Odontologo","id",id));
        existingOdontologo.setNombre(odontologo.getNombre());
        existingOdontologo.setApellido(odontologo.getApellido());
        existingOdontologo.setMatricula(odontologo.getMatricula());
        odontologoRepository.save(existingOdontologo);
        return existingOdontologo;


    }
    @Override
    public Odontologo deleteOdontologo(long id){
        Odontologo existingOdontologo= odontologoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Odontologo","id",id));
        odontologoRepository.deleteById(id);
        return existingOdontologo;


    }


    @Override
    public Paciente savePaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    @Override
    public List<Paciente> getAllPacientes(){
        return pacienteRepository.findAll();
    }
    @Override
    public Paciente getPacienteById(long id){
        return pacienteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Paciente","id",id));
    }
    @Override
    public Paciente updatePaciente(Paciente paciente,long id){
        Paciente existingPaciente= pacienteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Paciente","id",id));
        existingPaciente.setNombre(paciente.getNombre());
        existingPaciente.setApellido(paciente.getApellido());
        existingPaciente.setDomicilio(paciente.getDomicilio());
        existingPaciente.setDni(paciente.getDni());
        existingPaciente.setFecha(paciente.getFecha());
        pacienteRepository.save(existingPaciente);
        return existingPaciente;

    }
    @Override
    public Paciente deletePaciente(long id){
        Paciente existingPaciente= pacienteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Paciente","id",id));
        pacienteRepository.deleteById(id);
        return existingPaciente;}

        @Override
        public Domicilio saveDomicilio(PacienteRequest pacienteRequest){

            Paciente paciente = new Paciente();
            paciente.setNombre(pacienteRequest.getNombre());
            paciente.setApellido(pacienteRequest.getApellido());
            paciente.setDni(pacienteRequest.getDni());
            paciente.setFecha(pacienteRequest.getFecha());
            Domicilio domicilio = new Domicilio();
            domicilio.setCalle(pacienteRequest.getCalle());
            domicilio.setLocalidad(pacienteRequest.getLocalidad());
            domicilio.setCiudad(pacienteRequest.getCiudad());
            paciente.setDomicilio(domicilio);
            domicilio.setPaciente(paciente);
            pacienteRepository.save(paciente);
            return domicilio;
        }
        @Override
        public List<Domicilio> getAllDomicilios(){
            return domicilioRepository.findAll();
        }
        @Override
        public Domicilio getDomicilioById(long id){
            return domicilioRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Domicilio","id",id));
        }
        @Override
        public Domicilio updateDomicilio(Domicilio domicilio,long id){
            Domicilio existingDomicilio= domicilioRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Paciente","id",id));
            existingDomicilio.setCalle(domicilio.getCalle());
            existingDomicilio.setLocalidad(domicilio.getLocalidad());
            existingDomicilio.setCiudad(domicilio.getCiudad());
            domicilioRepository.save(existingDomicilio);
            return existingDomicilio;


        }
        @Override
        public Domicilio deleteDomicilio(long id){
            Domicilio existingDomicilio= domicilioRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Domicilio","id",id));
            domicilioRepository.deleteById(id);
            return existingDomicilio;


        }






}

