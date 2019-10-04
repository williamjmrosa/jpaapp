package br.edu.ifrs.canoas.jee.jpaapp.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Localidade
 *
 */
@Entity

public class Localidade implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Id
	private int id;
	private Float lat;
	private Float lon;
	@OneToOne (mappedBy = "localidade")
	private Usuario usuario;
	public Localidade() {
		super();
	}
	
	public Localidade(Float lat, Float lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
