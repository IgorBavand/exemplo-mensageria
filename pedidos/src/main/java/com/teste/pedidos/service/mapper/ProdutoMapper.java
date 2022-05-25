package com.teste.pedidos.service.mapper;

import com.teste.pedidos.model.Produto;
import com.teste.pedidos.service.dto.ProdutoDto;
import com.teste.pedidos.service.form.ProdutoForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toModel(ProdutoDto produtoDto);
    ProdutoDto toProdutoDto(Produto produto);
    Produto toEntity(ProdutoForm produtoForm);
    List<ProdutoDto> toDtos(List<Produto> produtos);
    //ProdutoDto copiar(Produto produto);
    void atualizarProduto(ProdutoForm form, @MappingTarget Produto produto);

}
