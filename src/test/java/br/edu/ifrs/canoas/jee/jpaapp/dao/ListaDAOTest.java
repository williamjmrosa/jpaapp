package br.edu.ifrs.canoas.jee.jpaapp.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.jpaapp.pojo.Lista;
import br.edu.ifrs.canoas.jee.jpaapp.pojo.Usuario;

public class ListaDAOTest {

	UsuarioDAO usuarioDAO;
	ListaDAO listaDAO;
	
	@Before
	public void setup() {
		usuarioDAO = new UsuarioDAO();
		listaDAO = new ListaDAO();
	}
	
	@Test
	public void testSalva() {
		//Cria Lista
		Usuario u1 = new Usuario();
		Usuario u2 = new Usuario();
		Usuario u3 = new Usuario("email", "senha", "endereco");
		usuarioDAO.salva(u1);
		usuarioDAO.salva(u2);
		usuarioDAO.salva(u3);
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		Usuario usuario = new Usuario("email@mail.com","senha321","end");
		Lista lista = new Lista("Participantes", usuarios);
		List<Lista> listas = new ArrayList<Lista>();
		listas.add(lista);
		usuarioDAO.salva(usuario);
		
		//listaDAO.salva(lista);
		
	}

	@Test
	public void testAtualiza() {
		
	}

	@Test
	public void testRemove() {
		
	}

	@Test
	public void testBuscaLong() {
		
	}

	@Test
	public void testBusca() {
		
	}

	@Test
	public void testBuscarNome() {
		
	}

}
