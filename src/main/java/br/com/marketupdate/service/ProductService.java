package br.com.marketupdate.service;

import br.com.marketupdate.configuration.PersistenceManager;
import br.com.marketupdate.entity.Carrinho;
import br.com.marketupdate.entity.Produto;
import br.com.marketupdate.entity.ProdutoNoCarrinho;
import br.com.marketupdate.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
public class ProductService {
    private  final EntityManager entityManager;
    private final ObjectMapper objectMapper;
    private final CriteriaBuilder cb;
    public ProductService() {
        this.entityManager = PersistenceManager.getEntityManager();
        this.cb = PersistenceManager.getEntityManager().getCriteriaBuilder();
        this.objectMapper = new ObjectMapper();
    }
    public void getProductsByCategory(String category, HttpServletResponse response) throws IOException {
        try {
            CriteriaQuery<Produto> criteriaQuery = cb.createQuery(Produto.class);
            Root<Produto> root = criteriaQuery.from(Produto.class);
            criteriaQuery.select(root).where(cb.equal(root.get("category"), category));
            List<Produto> products = entityManager.createQuery(criteriaQuery).getResultList();
            objectMapper.writeValue(response.getWriter(), products);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void addToCart(String userId, int productId) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Usuario user = entityManager.find(Usuario.class, userId);
            Produto product = entityManager.find(Produto.class, productId);

            CriteriaQuery<Carrinho> cq = cb.createQuery(Carrinho.class);
            Root<Carrinho> carrinhoRoot = cq.from(Carrinho.class);
            cq.select(carrinhoRoot).where(cb.equal(carrinhoRoot.get("usuario"), user));

            TypedQuery<Carrinho> query = entityManager.createQuery(cq);
            Carrinho carrinho = query.getSingleResult();

            ProdutoNoCarrinho produtoNoCarrinho = new ProdutoNoCarrinho(user, product, carrinho);
            entityManager.persist(produtoNoCarrinho);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}


