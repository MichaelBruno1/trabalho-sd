package com.lab9.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab9.notas.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	public Aluno findByMatricula(String matricula);
}
