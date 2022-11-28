package com.backend.spring.rest.repository;


import com.backend.spring.rest.models.Odontologo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
    public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
    }

