package br.com.marketupdate.entity.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private int id;
    private String name;
    private String image;
    private double price;
    private String category;

}
