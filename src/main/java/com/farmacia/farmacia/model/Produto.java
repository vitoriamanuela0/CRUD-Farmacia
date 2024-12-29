package com.farmacia.farmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atritubo nome é obrigatório!")
    @Size(min = 3, max = 100, message = "O atributo nome deve ter no mínimo 3 e no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "O atributo descrição é obrigatório!")
    @Size(min = 10, max = 255, message = "O atributo descrição deve ter no mínimo 10 e no máximo 255 caracteres.")
    private String descricao;

    @NotBlank(message = "O atributo código de barras é obrigatório!")
    @Size(min = 10, max = 10, message = "O atributo código de barras deve ter no mínimo e máximo 10 caracteres.")
    private String codigo;

    @NotNull(message = "O atributo Data de validade é obrigatório!")
    private LocalDate dataValidade;

    @NotBlank(message = "O atributo lote é obrigatório!")
    private String lote;

    @NotNull(message = "O atributo quantidade é obrigatório!")
    private Long quantidade;

    @NotNull(message = "O atributo valor é obrigatório!")
    private BigDecimal valor;

    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Categoria categoria;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
