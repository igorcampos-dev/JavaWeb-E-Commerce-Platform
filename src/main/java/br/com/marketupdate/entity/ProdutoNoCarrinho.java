package br.com.marketupdate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "produtonocarrinho")
@Data
public class ProdutoNoCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_Id")
    private Usuario usuarioId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "produto_Id")
    private Produto produtoId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carrinho_Id")
    private Carrinho carrinhoId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ProdutoNoCarrinho(Usuario usuarioId, Produto produtoId, Carrinho carrinhoId) {
        this.usuarioId = usuarioId;
        this.produtoId = produtoId;
        this.carrinhoId = carrinhoId;
    }

}
