package br.edu.ifrs.canoas.jee.jpaapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.jpaapp.pojo.Lista;
import br.edu.ifrs.canoas.jee.jpaapp.util.EntityManagerUtil;

public class ListaDAO {

private EntityManager em;
	
	public void salva(Lista lista) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(lista);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void atualiza(Lista lista) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Lista listaRecuperada = em.find(Lista.class, lista.getId());
		listaRecuperada.setNome(lista.getNome());
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Lista lista = em.find(Lista.class, id);
		em.remove(lista);
		em.getTransaction().commit();
		em.close();
	}
	
	public Lista busca(Long id) {
		em = EntityManagerUtil.getEM();
		Lista lista = em.find(Lista.class, id);
		em.close();
		return lista;
	}
	
	public List<Lista> busca(){
		em = EntityManagerUtil.getEM();
		TypedQuery<Lista> query = em.createQuery("SELECT lis FROM Lista lis",Lista.class);
		List<Lista> listas = query.getResultList();
		return listas;
	}
	
	public List<Lista> buscarNome(String nome){
		em = EntityManagerUtil.getEM();
		TypedQuery<Lista> query = em.createQuery("SELECT lis FROM Lista lis WHERE lower(lis.nome) = lower(:nome)",Lista.class);
		if(nome != null) {
			query.setParameter("nome", nome);
		}
		
		List<Lista> listas = query.getResultList();
		em.close();
		return listas;
	}
	
}
