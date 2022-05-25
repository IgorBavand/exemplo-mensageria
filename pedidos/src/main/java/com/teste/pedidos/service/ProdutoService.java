package com.teste.pedidos.service;

import com.teste.pedidos.exception.BadRequestException;
import com.teste.pedidos.exception.NotFoundException;
import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.model.Produto;
import com.teste.pedidos.repository.ProdutoRepository;
import com.teste.pedidos.repository.specification.PedidoSpecification;
import com.teste.pedidos.repository.specification.ProdutoSpecificartion;
import com.teste.pedidos.service.dto.PedidoDto;
import com.teste.pedidos.service.dto.ProdutoDto;
import com.teste.pedidos.service.filter.PedidoFilter;
import com.teste.pedidos.service.filter.ProdutoFilter;
import com.teste.pedidos.service.form.PedidoForm;
import com.teste.pedidos.service.form.ProdutoForm;
import com.teste.pedidos.service.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public ProdutoDto cadastrarProduto(ProdutoForm produtoForm){

        Produto produto = new Produto();
        produto.setCodigoProduto(produtoForm.getCodigoProduto());
        produto.setNomeProduto(produtoForm.getNomeProduto());

        try {
            produtoRepository.save(produto);
        }catch (BadRequestException e){
            throw new BadRequestException("Não foi possivel salval o produto");
        }



        return produtoMapper.toProdutoDto(produto);
    }

}
