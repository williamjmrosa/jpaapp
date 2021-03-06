package br.edu.ifrs.canoas.jee.jpaapp.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Mensagem
 *
 */
@Entity

public class Mensagem implements Serializable {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	private String texto;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="USR_ID")
	private Usuario usuario;
	
	private static final long serialVersionUID = 1L;

	public Mensagem(String texto) {
		this.texto = texto;
	}

	public Mensagem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
