package br.edu.ifrs.canoas.jee.jpaapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.jpaapp.pojo.Usuario;
import br.edu.ifrs.canoas.jee.jpaapp.util.EntityManagerUtil;

public class UsuarioDAO {

    private EntityManager em;

    public UsuarioDAO() {
    }

    public void salva(Usuario usuario) {
    	em = EntityManagerUtil.getEM();
    	em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
    }

    public void atualiza(Usuario usuario) {
    	em = EntityManagerUtil.getEM();
    	Usuario usuarioRecuperado = em.find(Usuario.class, usuario.getId());
    	em.close();
    	usuarioRecuperado.setEmail(usuario.getEmail());
    	
    	EntityManager em2 = EntityManagerUtil.getEM();
    	//Usuario novo = em.find(Usuario.class, usuario.getId());
    	em2.merge(usuarioRecuperado);
    	Usuario novo = em2.find(Usuario.class, usuario.getId());
    	System.out.println("Novo: "+novo.getEmail());
    	novo = busca(novo.getId());
    	System.out.println("Novo Busca: "+novo.getEmail());
    	em2.close();
    	
    	
    }

    public void remove(Long id) {
    }

    public Usuario busca(Long id) {
    	em = EntityManagerUtil.getEM();
    	Usuario usuario = em.find(Usuario.class, id);
    	em.close();
		return usuario;
    }

    public List<Usuario> busca() {
    	em = EntityManagerUtil.getEM();
    	TypedQuery<Usuario> query = em.createQuery("SELECT usr FROM Usuario usr", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
    }

    public List<Usuario> buscaPorEmail(String email) {
    	em = EntityManagerUtil.getEM();
    	TypedQuery<Usuario> query = em.createQuery("SELECT usr FROM Usuario usr where usr.email = :email", Usuario.class);
			if(email != null) {
				query.setParameter("email", email);
			}
		
		List <Usuario> usuarios = query.getResultList();
		em.close();
		return usuarios;
    }
}