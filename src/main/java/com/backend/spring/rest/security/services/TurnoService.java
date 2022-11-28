package com.backend.spring.rest.security.services;

import com.backend.spring.rest.models.Domicilio;
import com.backend.spring.rest.models.Odontologo;
import com.backend.spring.rest.models.Paciente;
import com.backend.spring.rest.models.Turno;
import com.backend.spring.rest.payload.request.PacienteRequest;

import java.util.List;

public interface TurnoService {

    Turno saveTurno(Turno turno) throws Exception;
            //long odontologo_id, long paciente_id, LocalDateTime comienzo_turno, LocalDateTime fin_turno);
    List<Turno> getAllTurnos();
    Turno getTurnoById(long id);
    Turno updateTurno(Turno turno,long id);
    Turno deleteTurno(long id);
    Odontologo saveOdontologo(Odontologo odontologo);

    List<Odontologo> getAllOdontologos();

    Odontologo getOdontologoById(long id);

    Odontologo updateOdontologo(Odontologo odontologo, long id);

    Odontologo deleteOdontologo(long id);
    Paciente savePaciente(Paciente paciente);
    List<Paciente> getAllPacientes();
    Paciente getPacienteById(long id);
    Paciente updatePaciente(Paciente paciente,long id);
    Paciente deletePaciente(long id);

    Domicilio saveDomicilio(PacienteRequest pacienteRequest);

    List<Domicilio> getAllDomicilios();

    Domicilio getDomicilioById(long id);

    Domicilio updateDomicilio(Domicilio domicilio, long id);

    Domicilio deleteDomicilio(long id);

}

