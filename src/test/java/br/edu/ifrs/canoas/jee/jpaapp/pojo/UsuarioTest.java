package br.edu.ifrs.canoas.jee.jpaapp.pojo;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import br.edu.ifrs.canoas.jee.jpaapp.util.EntityManagerUtil;

public class UsuarioTest {

	private EntityManager em;

	@Before
	public void setup() {
		em = EntityManagerUtil.getEM();
	}

	@Test
	public void testa_a_persistencia_do_usuario_em_branco() {
		//Given - tenho um usuario vazio e com id nulo
		Usuario usuario = new Usuario();
		assertThat(usuario.getId()).isNull();
		
		//when - salvo o usuario
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
		
		//then - o id deve ter sido populado
		assertThat(usuario.getId()).isNotNull();
	}

	@Test
	public void testa_a_persistencia_de_usuario_com_valor() {
		// construtor deve ter email, senha, endereco
		Usuario usuario = new Usuario("email.do@usuario.com", "Senha_Do_Usuario", "Endereco do Usuario");

		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();

		assertThat(usuario.getId()).isNotNull();
		assertThat(usuario.getEndereco()).isEqualTo("Endereco do Usuario");
	}

	@Test
	public void testa_a_busca_de_usuario_por_nome() {
		// construtor deve ter email, senha, endereco
		Usuario usuario = this.criaUsuario();
		Usuario usuarioRecuperado = em.find(Usuario.class, usuario.getId());
		em.close();

		//assertThat(usuarioRecuperado.getId()).isNull();
		assertThat(usuarioRecuperado.getId()).isNotNull();
		assertThat(usuarioRecuperado.getEndereco()).isEqualTo("Endereco do Usuario");
	}

	@Test
	public void testa_a_exclusao_de_usuario() {
		// construtor deve ter email, senha, endereco
		Usuario usuario = this.criaUsuario();
		
		em.remove(usuario);
		assertThat(usuario).isNotNull();
		Usuario usuarioRecuperado = em.find(Usuario.class, usuario.getId());
		em.close();
		assertThat(usuarioRecuperado).isNull();
	}

	@Test
	public void testa_a_atualizacao_de_usuario_managed() {
		// construtor deve ter email, senha, endereco
		Usuario usuario = this.criaUsuario();
		assertThat(usuario.getEmail()).isEqualTo("email.do@usuario.com");
		usuario.setEmail("email@atualizado.com");

		Usuario usuarioRecuperado = em.find(Usuario.class, usuario.getId());
		em.close();
		assertThat(usuarioRecuperado.getEmail()).isEqualTo("email@atualizado.com");
		
	}

	@Test
	public void testa_a_atualizacao_de_usuario_detached() {
		// construtor deve ter email, senha, endereco
		Usuario usuario = this.criaUsuario();
		// todas entidades detached
		
		usuario.setEmail("email@atualizado.com");
		em.close();;
		em = EntityManagerUtil.getEM();
		em.merge(usuario);
		Usuario usuarioRecuperado = em.find(Usuario.class, usuario.getId());
		em.close();

		assertThat(usuarioRecuperado.getEmail()).isEqualTo("email@atualizado.com");
	}

	@Test
	public void testa_a_busca() {
		// construtor deve ter email, senha, endereco
		this.criaUsuario();
		this.criaUsuario();
		this.criaUsuario();

		TypedQuery<Usuario> query = em.createQuery("SELECT usr FROM Usuario usr", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		em.close();
		assertThat(usuarios).size().isGreaterThan(3);
	}

	@Test
	public void testa_a_busca_por_email() {
		// construtor deve ter email, senha, endereco
		Usuario usuario = new Usuario("busca@usuario.com", "Senha_Do_Usuario", "Endereco do Usuario");
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();

		TypedQuery<Usuario> query = em.createQuery("SELECT usr FROM Usuario usr where usr.email = :email", Usuario.class);
		if(usuario != null) {
			if(usuario.getEmail() != null) {
				query.setParameter("email", usuario.getEmail());
			}
		}
		List <Usuario> usuarios = query.getResultList();

		assertThat(usuarios).hasSize(1);
	}

	private Usuario criaUsuario() {
		Usuario usuario = new Usuario("email.do@usuario.com", "Senha_Do_Usuario", "Endereco do Usuario");
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		return usuario;
	}

}