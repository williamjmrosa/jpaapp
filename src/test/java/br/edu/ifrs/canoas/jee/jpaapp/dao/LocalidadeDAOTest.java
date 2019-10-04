package br.edu.ifrs.canoas.jee.jpaapp.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.jpaapp.pojo.Localidade;

public class LocalidadeDAOTest {

	LocalidadeDAO localidadeDAO;
	
	@Before
	public void setup() {
		localidadeDAO = new LocalidadeDAO();
	}
	
	@Test
	public void testSalva() {
		//Cria Localidade
		Localidade localidade = new Localidade(-12.235F,43.345F);
		//Salva Localidade
		localidadeDAO.salva(localidade);
		//Verifica se Salvo
		assertThat(localidade.getId()).isNotNull();
		assertThat(localidade.getLat()).isEqualTo(-12.235F);
		
	}

	@Test
	public void testAtualiza() {
		//Cria Localidade
		Localidade localidade = new Localidade(54.345F,-32.342F);
		//Sava localidade
		localidadeDAO.salva(localidade);
		assertThat(localidade.getId()).isNotNull();
		//Atualiza Localidade
		localidade.setLat(24.22F);
		localidade.setLon(22.22F);
		localidadeDAO.atualiza(localidade);
		Localidade buscaLocalidade = localidadeDAO.busca(localidade.getId());
		assertThat(buscaLocalidade.getId()).isNotNull();
		assertThat(buscaLocalidade.getLat()).isEqualTo(24.22F);
		assertThat(buscaLocalidade.getLon()).isEqualTo(22.22F);
	}

	@Test
	public void testRemove() {
		//Salva Localidade
		Localidade localidade = new Localidade(13.33F,14.33F);
		localidadeDAO.salva(localidade);
		//Verifica se Salvo com sucesso
		assertThat(localidade.getId()).isNotNull();
		//Remove Localidade
		localidadeDAO.remove(localidade.getId());
		//Verifica se removeu com sucesso
		assertThat(localidadeDAO.busca(localidade.getId())).isNull();
		
		
		
	}

	@Test
	public void testBuscaPeloId() {
		//Cria Localidades
		Localidade localidade = new Localidade(23.99F,10.88F);
		//Salva Localidade
		localidadeDAO.salva(localidade);
		//Verifica se salvo
		assertThat(localidade.getId()).isNotNull();
		//Busca Logitude Pelo ID
		Localidade buscaLocalidade = localidadeDAO.busca(localidade.getId());
		//Confere Busca do Banco
		assertThat(buscaLocalidade.getId()).isNotNull();
		assertThat(buscaLocalidade.getLat()).isEqualTo(23.99F);
		assertThat(buscaLocalidade.getLon()).isEqualTo(10.88F);
	}

	@Test
	public void testBuscaTodasAsLocalidades() {
		//Cria Localidades
		Localidade l1 = new Localidade();
		Localidade l2 = new Localidade();
		Localidade l3 = new Localidade();
		//Salva Localidades
		localidadeDAO.salva(l1);
		localidadeDAO.salva(l2);
		localidadeDAO.salva(l3);
		//Busca Localidades
		List<Localidade> buscaLocalidades = localidadeDAO.busca();
		//Deve ter no minimo 3 localidades no banco
		assertThat(buscaLocalidades).size().isGreaterThan(3);
		
	}

	@Test
	public void testBuscarLon() {
		//Cria Localidade
		Localidade localidade = new Localidade(15.11F, -45.66F);
		//Salva Localidade
		localidadeDAO.salva(localidade);
		//Verifica se salvo
		assertThat(localidade.getId()).isNotNull();
		//Busca Longitude
		Localidade buscaLocalidade = localidadeDAO.buscarLon(-45.66F).get(0);
		//Confere Busca
		assertThat(buscaLocalidade.getId()).isNotNull();
		assertThat(buscaLocalidade.getLon()).isEqualTo(-45.66F);
		assertThat(buscaLocalidade.getLat()).isEqualTo(15.11F);
		
	}

}
