package com.lab9.notas.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@NotBlank
	private String matricula;
		
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "nota_id", referencedColumnName = "id")
	private List<Nota> notas = new ArrayList<Nota>();

	public Aluno() {}
	
	public Aluno(@NotBlank String matricula) {
		this.matricula = matricula;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public Aluno matricula(String matricula) {
		this.matricula = matricula;
		return this;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}	
	
	
}
