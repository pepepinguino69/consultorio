package com.backend.spring.rest.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="domicilio")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calle")
    @Size(max = 40)
    private String calle;


    @Size(max = 100)
    private String localidad;

    @Size(max = 100)
    private String ciudad;


    @OneToOne( mappedBy = "domicilio",cascade = CascadeType.ALL)
    private Paciente paciente;





}
