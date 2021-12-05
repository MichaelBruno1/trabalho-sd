package com.lab9.notas.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valor;	
	
	private String codigoDisciplina;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
	private Aluno aluno;
	
	public Nota() {}
	
	public Nota(double valor, String codigoDisciplina, Aluno aluno) {
		this.valor = valor;
		this.codigoDisciplina = codigoDisciplina;
		this.aluno = aluno;
	}

	public Nota(Long id, Double valor, String codigoDisciplina, Aluno aluno) {
		this.id = id;
		this.valor = valor;
		this.codigoDisciplina = codigoDisciplina;
		this.aluno = aluno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	
}
