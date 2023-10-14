package br.com.marketupdate.service;

import br.com.marketupdate.configuration.PersistenceManager;
import br.com.marketupdate.entity.Carrinho;
import br.com.marketupdate.entity.Usuario;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserService {
    private final EntityManager entityManager;
    private final CriteriaBuilder cb;

    String emailText = "email";
    public UserService() {
        this.entityManager = PersistenceManager.getEntityManager();
        this.cb = entityManager.getCriteriaBuilder();
    }
    public Usuario getUserByEmailAndPassword(String email, String password) {
        CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);
        Predicate condition = cb.and(cb.equal(root.get(emailText), email), cb.equal(root.get("password"), password));
        query.select(root).where(condition);
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultStream().findFirst().orElse(null);
    }
    public boolean processRegistration(Usuario user) {
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> root = cq.from(Usuario.class);
        cq.select(cb.count(root));
        Predicate emailPredicate = cb.equal(root.get(emailText), user.getEmail());
        Predicate namePredicate = cb.equal(root.get("name"), user.getName());
        cq.where(cb.or(emailPredicate, namePredicate));
        TypedQuery<Long> query = entityManager.createQuery(cq);
        Long count = query.getSingleResult();
        return count <= 0;
    }
    public boolean saveUser(Usuario usuario){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(usuario);
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        entityManager.persist(carrinho);
        transaction.commit();
        return true;
    }
    public String getFindIdToEmail(String email) {
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuarioRoot = cq.from(Usuario.class);
        cq.select(usuarioRoot).where(cb.equal(usuarioRoot.get(emailText), email));
        TypedQuery<Usuario> query = entityManager.createQuery(cq);
        Usuario usuario = query.getSingleResult();
        return usuario.getId();
    }
}
