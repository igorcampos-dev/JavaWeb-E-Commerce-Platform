package br.com.marketupdate.service;

import br.com.marketupdate.configuration.PersistenceManager;
import br.com.marketupdate.entity.Produto;
import br.com.marketupdate.entity.ProdutoNoCarrinho;
import br.com.marketupdate.entity.Usuario;
import br.com.marketupdate.entity.dto.ProdutoDTO;
import io.jsonwebtoken.Claims;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private final EntityManager entityManager;
    private final CriteriaBuilder cb;
    private final TokenService tokenService;
    public CartService() {
        this.entityManager = PersistenceManager.getEntityManager();
        this.cb = entityManager.getCriteriaBuilder();
        this.tokenService = new TokenService();
    }
    public List<ProdutoDTO> getProdutosFromCart(String usuarioId) {
        CriteriaQuery<Produto> query = cb.createQuery(Produto.class);
        Root<ProdutoNoCarrinho> pncRoot = query.from(ProdutoNoCarrinho.class);
        Join<ProdutoNoCarrinho, Produto> produtoJoin = pncRoot.join("produtoId");
        query.select(produtoJoin).where(cb.equal(pncRoot.get("usuarioId").get("id"), usuarioId));
        List<Produto> produtosDoCarrinho = entityManager.createQuery(query).getResultList();
        List<ProdutoDTO> produtosDTO = new ArrayList<>();
        for (Produto produto : produtosDoCarrinho) {
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setId(produto.getId());
            produtoDTO.setName(produto.getName());
            produtoDTO.setImage(produto.getImage());
            produtoDTO.setPrice(produto.getPrice());
            produtoDTO.setCategory(produto.getCategory());
            produtosDTO.add(produtoDTO);
        }
        return produtosDTO;
    }
    public void removeProductFromCart(String authorizationHeader, Long productId) {
        Claims claims = tokenService.decodeToken(authorizationHeader);
        EntityManager emRemove = PersistenceManager.getEntityManager();
        entityManager.getTransaction().begin();
        Usuario usuario = entityManager.find(Usuario.class, claims.getSubject());
        CriteriaQuery<ProdutoNoCarrinho> cqProduto = cb.createQuery(ProdutoNoCarrinho.class);
        Root<ProdutoNoCarrinho> produtoNoCarrinhoRoot = cqProduto.from(ProdutoNoCarrinho.class);
        cqProduto.select(produtoNoCarrinhoRoot).where(cb.and(cb.equal(produtoNoCarrinhoRoot.get("usuario_id"), usuario), cb.equal(produtoNoCarrinhoRoot.get("produto_id"), productId)));
        TypedQuery<ProdutoNoCarrinho> queryProduto = emRemove.createQuery(cqProduto);
        ProdutoNoCarrinho produtoNoCarrinho = queryProduto.getSingleResult();
        emRemove.getTransaction().begin();
        emRemove.remove(produtoNoCarrinho);
        emRemove.getTransaction().commit();
        entityManager.getTransaction().commit();
        entityManager.close();
        emRemove.close();
    }


}
