package com.teste.pedidos.service;

import com.teste.pedidos.exception.BadRequestException;
import com.teste.pedidos.exception.NotFoundException;
import com.teste.pedidos.model.Produto;
import com.teste.pedidos.repository.ProdutoRepository;
import com.teste.pedidos.repository.specification.ProdutoSpecificartion;
import com.teste.pedidos.service.dto.ProdutoDto;
import com.teste.pedidos.service.filter.ProdutoFilter;
import com.teste.pedidos.service.form.ProdutoForm;
import com.teste.pedidos.service.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public Page<ProdutoDto> buscarProdutos(ProdutoFilter filter, Pageable pageable) throws NotFoundException {
        Page<Produto> produtos = produtoRepository.findAll(ProdutoSpecificartion.of(filter), pageable);
        log.info("buscando produtos..");
        if(!produtos.hasContent()){
            throw new NotFoundException("Nenhum produto encontrado");
        }
        return new PageImpl<>(produtoMapper.toDtos(produtos.getContent()), pageable, produtos.getTotalElements());
    }

    public ProdutoDto buscarProdutoId(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(!produto.isPresent()){
            throw new NotFoundException("Produto não encontrado.");
        }
        return produtoMapper.toProdutoDto(produto.get());
    }

    public ProdutoDto cadastrarProduto(ProdutoForm produtoForm){

        Produto produto = new Produto();
        produto.setNomeProduto(produtoForm.getNomeProduto());
        produto.setPreco(produtoForm.getPreco());

        try {
            produtoRepository.save(produto);
        }catch (BadRequestException e){
            throw new BadRequestException("Não foi possivel salvar o produto");
        }

        return produtoMapper.toProdutoDto(produto);
    }

}
