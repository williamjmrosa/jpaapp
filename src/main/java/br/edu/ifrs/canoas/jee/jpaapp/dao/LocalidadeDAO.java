package br.edu.ifrs.canoas.jee.jpaapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.jpaapp.pojo.Localidade;
import br.edu.ifrs.canoas.jee.jpaapp.util.EntityManagerUtil;

public class LocalidadeDAO {

	private EntityManager em;
	
	public void salva(Localidade localidade) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(localidade);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void atualiza(Localidade localidade) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Localidade localidadeRecuperada = em.find(Localidade.class, localidade.getId());
		localidadeRecuperada.setLat(localidade.getLat());
		localidadeRecuperada.setLon(localidade.getLon());
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(int id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Localidade localidade = em.find(Localidade.class, id);
		em.remove(localidade);
		em.getTransaction().commit();
		em.close();
	}
	
	public Localidade busca(int id) {
		em = EntityManagerUtil.getEM();
		Localidade localidade = em.find(Localidade.class, id);
		em.close();
		return localidade;
	}
	
	public List<Localidade> busca(){
		em = EntityManagerUtil.getEM();
		TypedQuery<Localidade> query = em.createQuery("SELECT loc FROM Localidade loc",Localidade.class);
		List<Localidade> localidades = query.getResultList();
		return localidades;
	}
	
	public List<Localidade> buscarLon(Float lon){
		em = EntityManagerUtil.getEM();
		TypedQuery<Localidade> query = em.createQuery("SELECT loc FROM Localidade loc WHERE loc.lon = :lon",Localidade.class);
		if(lon != null) {
			query.setParameter("lon", lon);
		}
		
		List<Localidade> localidades = query.getResultList();
		em.close();
		return localidades;
	}
}
