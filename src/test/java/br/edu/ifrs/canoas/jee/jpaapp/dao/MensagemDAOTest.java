package br.edu.ifrs.canoas.jee.jpaapp.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.jpaapp.pojo.Mensagem;

public class MensagemDAOTest {

	MensagemDAO mensagemDAO;
	
	@Before
	public void setup() {
		mensagemDAO = new MensagemDAO();
	}
	
	@Test
	public void testSalva() {
		//Cria usuario
		Mensagem mensagem = new Mensagem("texto");
		//Salva no banco
		mensagemDAO.salva(mensagem);
		//Verifica se salvou
		assertThat(mensagem.getId()).isNotNull();
	}

	@Test
	public void testAtualiza() {
		//Cria Mensagem
		Mensagem mensagem = new Mensagem("texto");
		mensagemDAO.salva(mensagem);
		assertThat(mensagem.getId()).isNotNull();
		//Atualiza Mensagem
		mensagem.setTexto("Novo texto");
		mensagemDAO.atualiza(mensagem);
		//Conferir Atualizacao
		Mensagem buscaMensagem = mensagemDAO.busca(mensagem.getId());
		assertThat(buscaMensagem.getTexto()).isEqualTo("Novo texto");
		
	}

	@Test
	public void testRemove() {
		//Cria Mensagem
		Mensagem mensagem = new Mensagem("texto");
		mensagemDAO.salva(mensagem);
		//Verifica se salvou com sucesso
		assertThat(mensagem.getId()).isNotNull();
		//Remove
		mensagemDAO.remove(mensagem.getId());
		//Verifica se removeu com sucesso
		assertThat(mensagemDAO.busca(mensagem.getId())).isNull();
	}

	@Test
	public void testBuscaTodasMensagem() {
		//Cria Mensagem
		Mensagem m1 = new Mensagem();
		Mensagem m2 = new Mensagem();
		Mensagem m3 = new Mensagem("texto");
		mensagemDAO.salva(m1);
		mensagemDAO.salva(m2);
		mensagemDAO.salva(m3);
		//Busca Mensagem
		List<Mensagem> mensagems = mensagemDAO.busca();
		//Deve ter no mínimo 3 Mensagem no Banco
		assertThat(mensagems).size().isGreaterThan(3);		
	}
	
	@Test
	public void testBusca() {
		//Cria Mensagem
		Mensagem m1 = new Mensagem("textoBuscaID");
		mensagemDAO.salva(m1);
		assertThat(m1.getId()).isNotNull();
		//Busca Mensagem
		Mensagem mensagemDoBD = mensagemDAO.busca(m1.getId());
		//Confere o retorno
		assertThat(mensagemDoBD.getTexto()).isEqualTo("textoBuscaID");
		assertThat(mensagemDoBD.getId()).isNotNull();
	}

	@Test
	public void testBuscaTexto() {
		//Cria Mensagem
		mensagemDAO.salva(new Mensagem("textoBusca"));
		//Busca Mensagem
		Mensagem mensagemDoBD = mensagemDAO.buscaTexto("TextoBusca").get(0);
		//Confere o retorno
		assertThat(mensagemDoBD.getTexto()).isEqualTo("textoBusca");
		assertThat(mensagemDoBD.getId()).isNotNull();
	}

}
