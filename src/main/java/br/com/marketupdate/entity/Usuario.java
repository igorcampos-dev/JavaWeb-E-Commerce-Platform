package br.com.marketupdate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode estar em branco")
    @Pattern(regexp = "^(?>[A-Za-z]+(\\s(?:de\\s)?[A-Za-z]+)*+)$", message = "O nome não está em um formato válido")
    @Column(name = "name")
    private String name;

    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode estar em branco")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@gmail\\.com$", message = "O email não está em um formato válido")
    @Column(name = "email")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode estar em branco")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "A senha não está em um formato válido")
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "usuarioId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProdutoNoCarrinho> carrinho = new ArrayList<>();
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}