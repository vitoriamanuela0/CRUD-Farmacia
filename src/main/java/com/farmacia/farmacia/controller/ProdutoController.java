package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.model.Produto;
import com.farmacia.farmacia.repository.CategoriaRepository;
import com.farmacia.farmacia.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<List<Produto>> getByCodigo(@PathVariable String codigo){
        return ResponseEntity.ok(produtoRepository.findAllByCodigoContainingIgnoreCase(codigo));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Produto>> getByDescricao(@PathVariable String descricao){
        return ResponseEntity.ok(produtoRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @GetMapping("/lote/{lote}")
    public ResponseEntity<List<Produto>> getByLote(@PathVariable String lote){
        return ResponseEntity.ok(produtoRepository.findAllByLoteContainingIgnoreCase(lote));
    }

    @GetMapping("/quantidade/menor/{quantidade}")
    public ResponseEntity<List<Produto>> getByQuantidadeMenor(@PathVariable Long quantidade) {
        return ResponseEntity.ok(produtoRepository.findAllByQuantidadeLessThan(quantidade));
    }

    @GetMapping("/quantidade/maior/{quantidade}")
    public ResponseEntity<List<Produto>> getByQuantidadeMaior(@PathVariable Long quantidade) {
        return ResponseEntity.ok(produtoRepository.findAllByQuantidadeGreaterThan(quantidade));
    }

    @GetMapping("/valor/menor/{valor}")
    public ResponseEntity<List<Produto>> getByValorMenor(@PathVariable BigDecimal valor) {
        return ResponseEntity.ok(produtoRepository.findAllByValorLessThan(valor));
    }

    @GetMapping("/valor/maior/{valor}")
    public ResponseEntity<List<Produto>> getByValorMaior(@PathVariable BigDecimal valor) {
        return ResponseEntity.ok(produtoRepository.findAllByValorGreaterThan(valor));
    }

    @PostMapping
    public ResponseEntity<Produto> post (@Valid @RequestBody Produto produto) {
        if (categoriaRepository.existsById(produto.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produtoRepository.save(produto));

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        produtoRepository.deleteById(id );
    }
}
