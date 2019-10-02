package br.edu.ifrs.canoas.jee.jpaapp.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.jpaapp.pojo.Mensagem;
import br.edu.ifrs.canoas.jee.jpaapp.util.EntityManagerUtil;

public class MensagemDAO {

	private EntityManager em;
	
	public MensagemDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void salva(Mensagem mensagem){
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(mensagem);
		em.getTransaction().commit();
		em.close();
	}
	
	public void atualiza(Mensagem mensagem) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Mensagem atualizaMensagem = em.find(Mensagem.class, mensagem.getId());
		atualizaMensagem.setTexto(mensagem.getTexto());
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Mensagem mensagem = em.find(Mensagem.class, id);
		em.remove(mensagem);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public Mensagem busca(Long id) {
		em = EntityManagerUtil.getEM();
		Mensagem mensagem = em.find(Mensagem.class, id);
		em.close();
		return mensagem;
	}
	
	public List<Mensagem> busca(){
		em = EntityManagerUtil.getEM();
		TypedQuery<Mensagem> query = em.createQuery("SELECT mens FROM Mensagem mens",Mensagem.class);
		List<Mensagem> mensagems = query.getResultList();
		return mensagems;
	}
	
	public List<Mensagem> buscaTexto(String texto){
		em = EntityManagerUtil.getEM();
		TypedQuery<Mensagem> query = em.createQuery("SELECT mens FROM Mensagem mens where lower(mens.texto) = lower(:texto)",Mensagem.class);
			if(texto != null) {
				query.setParameter("texto", texto);
			}
			
		List<Mensagem> mensagems = query.getResultList();
		em.close();
		return mensagems;
	}
	
}
