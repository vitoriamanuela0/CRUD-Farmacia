package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.model.Categoria;
import com.farmacia.farmacia.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
    public List<Produto> findAllByQuantidadeLessThan(Long quantidade);
    public List<Produto> findAllByQuantidadeGreaterThan(Long quantidade);
    public List<Produto> findAllByValorLessThan(BigDecimal valor);
    public List<Produto> findAllByValorGreaterThan(BigDecimal valor);
    public List<Produto> findAllByDescricaoContainingIgnoreCase(String descricao);
    public List<Produto> findAllByCodigoContainingIgnoreCase(String codigo);
    public List<Produto> findAllByLoteContainingIgnoreCase(String lote);


}
