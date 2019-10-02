package br.edu.ifrs.canoas.jee.jpaapp.pojo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Lista
 *
 */
@Entity

public class Lista implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@GeneratedValue (strategy = GenerationType.AUTO)
	@Id
	private int id;
	private String nome;
	@ManyToMany (mappedBy = "listasAssinadas")
	private Collection<Usuario> membros;
	
	public Lista() {
		super();
	}
	
	public Lista(String nome, Collection<Usuario> membros) {
		super();
		this.nome = nome;
		this.membros = membros;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Collection<Usuario> getMembros() {
		return membros;
	}
	
	public void setMembros(Collection<Usuario> membros) {
		this.membros = membros;
	}
	
	
   
}
