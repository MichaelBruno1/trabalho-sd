package com.lab9.notas.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab9.notas.models.Aluno;
import com.lab9.notas.models.Disciplina;
import com.lab9.notas.models.Nota;
import com.lab9.notas.repository.AlunoRepository;
import com.lab9.notas.repository.DisciplinaRepository;
import com.lab9.notas.repository.NotaRepository;

@RestController
public class NotasController {
	
	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@Autowired
	AlunoRepository alunoRepository;	

	@Autowired
	NotaRepository notaRepository;

	@PostMapping("/cadastrar_aluno/{matricula}")
	public String cadastrarAluno(@PathVariable String matricula) {
		try {
			alunoRepository.save(new Aluno().matricula(matricula));
			return "Aluno criado com sucesso";
		}catch(Exception e) {
			return "Não foi possivel criar o aluno";
		}
	}
	
	@PostMapping("/cadastrar_disciplina/{codigo}")
	public String cadastrarDisciplina(@PathVariable String codigo) {
		try {
			disciplinaRepository.save(new Disciplina().codigo(codigo));
			return "Disciplina criada com sucesso";
		}catch(Exception e) {
			return "Não foi possivel criar a disciplina";
		}
	}
	
	@PostMapping("/cadastrar_nota/{matricula}/{codigo}/{nota}")
	public String cadastrarNota(@PathVariable String matricula, @PathVariable String codigo, @PathVariable double nota){

		try {
			
			Aluno aluno = alunoRepository.findByMatricula(matricula);
			Disciplina disciplina = disciplinaRepository.findByCodigo(codigo);
					
			if(aluno == null || disciplina == null) {
				return "Não foi possivel cadastrar a nota";
			}
						
			if(disciplina.getAlunos().isEmpty()) {
				disciplina.getAlunos().add(aluno);
				disciplina = disciplinaRepository.save(disciplina);
			}
			
			Nota notaObj = new Nota(nota, codigo, aluno);
			notaObj = notaRepository.save(notaObj);
			
			
			for(int i =0; i<disciplina.getAlunos().size(); i++){
				
				if(disciplina.getAlunos().get(i).getMatricula().equals(matricula)){
					disciplina.getAlunos().get(i).getNotas().add(notaObj);
					
					alunoRepository.save(disciplina.getAlunos().get(i));					
				}
			}
			
			
			disciplinaRepository.save(disciplina);
			
			return "Nota cadastrada";
			
		} catch (Exception e) {
			return "Não foi possivel cadastrar a nota";
		}
		
	}
	
	@GetMapping("/consultar_nota/{matricula}/{codigo}")
	public String consultarNota(@PathVariable String matricula, @PathVariable String codigo){
		
		try {
			Disciplina disciplina = disciplinaRepository.findByCodigo(codigo);
			String valorNota = "";
			
			for(Aluno aluno: disciplina.getAlunos()) {
				if(aluno.getMatricula().equals(matricula)) {
					for(Nota nota: aluno.getNotas()) {
						if(nota.getCodigoDisciplina().equals(codigo)) {
							valorNota = nota.getValor().toString();
						}
					}

					return valorNota;
				}
			}			

			return "Não foi possivel encontrar o aluno ou a nota";
			
		}catch(Exception e) {
			return "Não foi possivel encontrar o aluno ou a nota";
		}
		
	}
	
	@GetMapping("/consultar_notas/{matricula}")
	public String consultarNotas(@PathVariable String matricula){
		
		try {
			Aluno aluno = alunoRepository.findByMatricula(matricula);
			Map<String, Double> retorno = new HashMap<String, Double>();
			
			for(Nota nota: aluno.getNotas()) {
				retorno.put(nota.getCodigoDisciplina(), nota.getValor());
			}
			
			return retorno.toString();
			
		}catch(Exception e) {
			return "Não foi possivel encontrar as notas do aluno";
		}
		
	}
	
	@GetMapping("/consultar_cr/{matricula}")
	public String consultarCR(@PathVariable String matricula){
		
		try {
			Aluno aluno = alunoRepository.findByMatricula(matricula);
			Double total = 0.0;
			
			for(Nota nota: aluno.getNotas()) {
				total += nota.getValor();
			}

			return "CR: " + total/aluno.getNotas().size();
			
		}catch(Exception e) {
			return "Não foi possivel calcular o CR do aluno";
		}
		
	}
}
