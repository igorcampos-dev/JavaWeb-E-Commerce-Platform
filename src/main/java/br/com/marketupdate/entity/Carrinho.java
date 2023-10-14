package br.com.marketupdate.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "usuario_Id")
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinhoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoNoCarrinho> produtosNoCarrinho = new ArrayList<>();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
