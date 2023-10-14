package br.com.marketupdate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Expose
    private String name;

    @Column(name = "image")
    @Expose
    private String image;

    @Column(name = "price")
    @Expose
    private double price;

    @Column(name = "category")
    private String category;
    @JsonIgnore
    @OneToMany(mappedBy = "produtoId")
    private List<ProdutoNoCarrinho> produtosNoCarrinho = new ArrayList<>();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
}
