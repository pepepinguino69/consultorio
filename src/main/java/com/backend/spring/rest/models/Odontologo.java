package com.backend.spring.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "odontologo")

public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;
    @OneToMany(
            mappedBy = "odontologo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Turno> turnosOdo = new ArrayList<>();


    public void addTurno(Turno turno) {
        turnosOdo.add(turno);
        turno.setOdontologo(this);
    }

    public void removeTurno(Turno turno) {
        turnosOdo.add(turno);
        turno.setOdontologo(this);
    }
}


