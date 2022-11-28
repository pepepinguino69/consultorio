package com.backend.spring.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 65)
    @Column(name = "nombre")
    private String nombre;

    @Size(max = 65)
    @Column(name = "apellido")
    private String apellido;

    @NotNull
    @Size(max = 20)
    @Column(unique = true)
    private String dni;

    @NotNull
    private LocalDateTime fecha;
    @OneToOne (cascade = {CascadeType.ALL})
    @JsonIgnore
    private Domicilio domicilio;

        public Paciente(String nombre, String lastNameAPELLIDO, String dni, LocalDateTime fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha = fecha;
    }
    @OneToMany(
            mappedBy = "paciente",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Turno> turnosPac = new ArrayList<>();



    public void addTurno(Turno turno) {
        turnosPac.add(turno);
       turno.setPaciente(this);


    }

    public void removeTurno(Turno turno) {
        turnosPac.add(turno);
        turno.setPaciente(this);
    }


}


