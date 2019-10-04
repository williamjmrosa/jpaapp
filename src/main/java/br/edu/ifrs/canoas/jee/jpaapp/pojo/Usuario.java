package br.edu.ifrs.canoas.jee.jpaapp.pojo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	private String email;
	private String senha;
	private String endereco;
	@OneToOne
	@JoinColumn(name="LOC_ID")
	private Localidade localidade;
	@OneToMany (mappedBy = "usuario")
	private Collection<Mensagem> mensagems;
	@ManyToMany
	private Collection<Lista> listasAssinadas;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String email, String senha,String endereco) {
		super();
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}
	
	public Usuario(String email, String senha, String endereco, Localidade localidade) {
		super();
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
		this.localidade = localidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Localidade getLocalidade() {
		return localidade;
	}
	
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	
	public Collection<Mensagem> getMensagems() {
		return mensagems;
	}
	
	public void setMensagems(Collection<Mensagem> mensagems) {
		this.mensagems = mensagems;
	}
	
	public Collection<Lista> getListasAssinadas() {
		return listasAssinadas;
	}
	
	public void setListasAssinadas(Collection<Lista> listasAssinadas) {
		this.listasAssinadas = listasAssinadas;
	}
	
	public void addListaAssunadas(Lista lista) {
		listasAssinadas.add(lista);
	}
	
	public void addMensagem(Mensagem msg){
		mensagems.add(msg);
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.edu.ifrs.canoas.jee.jpaapp.pojo.Usuario[ id=" + id + " ]";
	}

}