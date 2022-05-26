package com.teste.pedidos.controller;

import com.teste.pedidos.model.Produto;
import com.teste.pedidos.service.ClienteService;
import com.teste.pedidos.service.dto.ClienteDto;
import com.teste.pedidos.service.dto.ProdutoDto;
import com.teste.pedidos.service.filter.ClienteFilter;
import com.teste.pedidos.service.filter.ProdutoFilter;
import com.teste.pedidos.service.form.ClienteForm;
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

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @ApiOperation(httpMethod = "GET", value = "busca todos os clientes", response = ClienteDto[].class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Página que você quer receber.(0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Número de registros por página."),
    })
    @GetMapping("/buscar-clientes")
    public ResponseEntity<List<ClienteDto>> clientes(ClienteFilter filter, @ApiIgnore Pageable pageable){
        Page<ClienteDto> clientes = clienteService.bucarClientes(filter, pageable);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.set("totalRegistros", String.valueOf(clientes.getTotalElements()));
        responseHeaders.set("Access-Control-Expose-Headers", "totalRegistros");

        return ResponseEntity.ok().headers(responseHeaders).body(clientes.getContent());
    }

    @ApiOperation(httpMethod = "POST", value = "cadastrar um cliente", response = ClienteDto[].class)
    @PostMapping("/cadastrar-cliente")
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody ClienteForm clienteForm){
        return ResponseEntity.ok().body(clienteService.cadastrarCliente(clienteForm));
    }
}
