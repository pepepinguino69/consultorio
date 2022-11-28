package com.backend.spring.rest.models;


import com.backend.spring.rest.payload.request.TurnoRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turno")

public class Turno {
        @Id
        @Column(name="id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @ManyToOne
        @JoinColumn(
                name = "odontologo_id", referencedColumnName = "id",nullable = false,

    foreignKey = @ForeignKey(
            name = "odontologo_turno_fk"
    ))

        @JsonIgnore
        private Odontologo odontologo;
       @ManyToOne
       @JoinColumn(
               name = "paciente_id",
               nullable = false,
               referencedColumnName = "id",
               foreignKey = @ForeignKey(
                       name = "paciente_turno_fk"
               ))
       @JsonIgnore
        private Paciente paciente;
        @Column(name="comienzo_turno",nullable = false)
        private LocalDateTime comienzo_turno;
        @Column(name="fin_turno",nullable = false)
        private LocalDateTime fin_turno;


    public Turno( Odontologo odontologo, Paciente paciente, LocalDateTime comienzo_turno, LocalDateTime fin_turno) {
        this.id = id;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.comienzo_turno = comienzo_turno;
        this.fin_turno = fin_turno;
    }

    public static Turno from(TurnoRequest turnoRequest){

        Turno turno = new Turno();

        Odontologo newOdontologo=new Odontologo();
        turno.setId(0);
        newOdontologo.setId(turnoRequest.getOdontologo_id());
        Paciente newPaciente=new Paciente();
        newPaciente.setId(turnoRequest.getPaciente_id());
        turno.setOdontologo(newOdontologo);
        turno.setPaciente(newPaciente);
        turno.setFin_turno(turnoRequest.getFin_turno());
        turno.setComienzo_turno(turnoRequest.getComienzo_turno());

        return turno;

    }

    public String message(Turno turno){return   "DR."+turno.getOdontologo().getApellido()+" atiende al Sr./a "+
            turno.getPaciente().getApellido()+" de "+ turno.getComienzo_turno()+" hasta "+
            turno.getFin_turno();}
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}



