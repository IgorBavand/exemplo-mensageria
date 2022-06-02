package com.teste.pedidos.controller;

import com.teste.pedidos.model.Produto;
import com.teste.pedidos.service.ProdutoService;
import com.teste.pedidos.service.dto.ProdutoDto;
import com.teste.pedidos.service.filter.ProdutoFilter;
import com.teste.pedidos.service.form.ProdutoForm;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProdutoController {

    private final ProdutoService produtoService;


    @ApiOperation(httpMethod = "GET", value = "busca todos os produtos", response = ProdutoDto[].class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Página que você quer receber.(0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Número de registros por página."),
    })
    @GetMapping("/buscar-produtos")
    public ResponseEntity<List<ProdutoDto>> produtos(ProdutoFilter filter, @ApiIgnore Pageable pageable){
        Page<ProdutoDto> produtos = produtoService.buscarProdutos(filter, pageable);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.set("totalRegistros", String.valueOf(produtos.getTotalElements()));
        responseHeaders.set("Access-Control-Expose-Headers", "totalRegistros");

        return ResponseEntity.ok().headers(responseHeaders).body(produtos.getContent());
    }

    @ApiOperation(httpMethod = "GET", value = "buscar produto por id", response = ProdutoDto.class)
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> buscarProdutoId(@PathVariable Long id){
        return ResponseEntity.ok().body(produtoService.buscarProdutoId(id));
    }

    @ApiOperation(httpMethod = "POST", value = "cadastrar um produto", response = Produto[].class)
    @PostMapping("/cadastrar-produto")
    @ResponseBody
    public ResponseEntity<ProdutoDto> cadastrarProduto(@RequestBody @Valid ProdutoForm produtoForm){
        return ResponseEntity.ok().body(produtoService.cadastrarProduto(produtoForm));
    }
}
