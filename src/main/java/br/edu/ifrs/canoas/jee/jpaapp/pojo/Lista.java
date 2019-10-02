package br.edu.ifrs.canoas.jee.jpaapp.pojo;

import java.io.Serializable;
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
	public Lista() {
		super();
	}
   
}
