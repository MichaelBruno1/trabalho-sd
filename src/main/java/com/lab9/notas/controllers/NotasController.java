package com.lab9.notas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lab9.notas.models.Aluno;
import com.lab9.notas.models.Disciplina;
import com.lab9.notas.models.Nota;
import com.lab9.notas.services.NotasService;

@RestController
public class NotasController {
	
	@Autowired
	NotasService notasService;
	
	@GetMapping("/cadastrar_nota/{matricula}/{codigo}/{nota}")
	public String buscarLivro(@PathVariable String matricula, @PathVariable String codigo, @PathVariable double nota){
		
		Gson gson = new Gson();
		Aluno aluno = new Aluno("Michael", "2015780295");
		
		List<Aluno> alunos = new ArrayList<Aluno>();		
		alunos.add(aluno);
		
		Disciplina disciplina = new Disciplina("SD", codigo, alunos);
		
		Nota notaObj = new Nota(nota, disciplina, aluno);
		
		
		return gson.toJson(notasService.inserirNota(notaObj));
	}
}
