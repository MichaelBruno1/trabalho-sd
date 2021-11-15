package com.lab9.notas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab9.notas.models.Nota;
import com.lab9.notas.repository.AlunoRepository;
import com.lab9.notas.repository.DisciplinaRepository;
import com.lab9.notas.repository.NotaRepository;

@Service
public class NotasService {

	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@Autowired
	NotaRepository notaRepository;
	
	@Autowired
	AlunoRepository alunoRepository;
	
	public Nota inserirNota(Nota nota) {
		notaRepository.save(nota);
		return nota;
	}
}
