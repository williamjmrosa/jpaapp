package br.edu.ifrs.canoas.jee.jpaapp.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.jpaapp.pojo.Localidade;
import br.edu.ifrs.canoas.jee.jpaapp.pojo.Usuario;

public class UsuarioDAOTest {

	UsuarioDAO usuarioDAO;
	LocalidadeDAO localidadeDAO;
	
	@Before
	public void setup() {
		usuarioDAO = new UsuarioDAO();
		localidadeDAO = new LocalidadeDAO();
	}
	
	@Test
	public void testSalvaNovoUsuario() {
		// Cria usuario
		Localidade localidade = new Localidade(25.44F, 12.22F); 
		//localidadeDAO.salva(localidade);
		Usuario usuario = new Usuario();//"email.do@usuario.com", "senha123","endereco",localidade);
		usuario.setEmail("email.do@usuario.com");
		usuario.setSenha("senha123");
		usuario.setEndereco("endereco");
		usuario.setLocalidade(localidade);
		// salva no banco
		usuarioDAO.salva(usuario);
		// verifica se salvou
		assertThat(usuario.getId()).isNotNull();
		assertThat(usuario.getLocalidade().getId()).isNotNull();
		

	}

	@Test
	public void testaBuscaTodosUsuarios() {
		// Cria usuario
		Usuario u1 = new Usuario();
		Usuario u2 = new Usuario();
		Usuario u3 = new Usuario("email", "senha", "endereco");
		usuarioDAO.salva(u1);
		usuarioDAO.salva(u2);
		usuarioDAO.salva(u3);
		List<Usuario> usuarios = usuarioDAO.busca();
		// Deve ter no mínimo 3 usuários no banco
		assertThat(usuarios).size().isGreaterThan(3);
	}

	@Test
	public void testaUsuarioPorEmail() {

		// Cria usuario
		usuarioDAO.salva(new Usuario("email@do.usuario", "senha",
				"endereco"));
		Usuario usuarioDoBD = usuarioDAO.buscaPorEmail("EMAIL@DO.USUARIO").get(0);
		assertThat(usuarioDoBD.getEmail()).isEqualTo("email@do.usuario");
		assertThat(usuarioDoBD.getId()).isNotNull();

	}
	@Test
	public void testaAtualizaUsuario() {

		Usuario usuario = new Usuario("emailDeAtualizacao", "senha", "endereco");
		// Cria usuario
		usuarioDAO.salva(usuario);
		assertThat(usuario.getId()).isNotNull();
		assertThat(usuario.getEmail()).as("emailDeAtualizacao");
		usuario.setEmail("agora_mudou_o_email");
		usuarioDAO.atualiza(usuario);
		Usuario novoUsuarioRecuperadoDoBanco = usuarioDAO.busca(usuario.getId());
		assertThat(novoUsuarioRecuperadoDoBanco.getEmail()).isEqualTo("agora_mudou_o_email");
	}

	@Test
	public void testaRemoveUsuario() {
		Usuario usuario = new Usuario("emailDeExclusao", "senha", "endereco");
		usuarioDAO.salva(usuario);
		// verifica se salvou com sucesso
		assertThat(usuario.getId()).isNotNull();
		// remove
		usuarioDAO.remove(usuario.getId());
		// remove
		assertThat(usuarioDAO.busca(usuario.getId())).isNull();
		// VERIFICA SE REMOVEU COM SUCESSO
	}

}