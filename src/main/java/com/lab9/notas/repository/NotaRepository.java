package com.lab9.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab9.notas.models.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>{

}
