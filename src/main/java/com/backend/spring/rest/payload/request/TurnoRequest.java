package com.backend.spring.rest.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class TurnoRequest {

    private long odontologo_id;
    private long paciente_id;
    private LocalDateTime comienzo_turno;
    private LocalDateTime fin_turno;



}
