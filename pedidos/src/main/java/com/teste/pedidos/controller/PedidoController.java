package com.teste.pedidos.controller;

import com.teste.pedidos.repository.specification.PedidoSpecification;
import com.teste.pedidos.service.PedidoService;
import com.teste.pedidos.service.dto.PedidoDto;
import com.teste.pedidos.service.filter.PedidoFilter;
import com.teste.pedidos.service.form.PedidoForm;
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
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @ApiOperation(httpMethod = "GET", value = "busca todos os pedidos", response = PedidoDto[].class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Página que você quer receber.(0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Número de registros por página."),
    })
    @GetMapping("/buscar-pedidos")
    public ResponseEntity<List<PedidoDto>> pedidos(PedidoFilter filter, @ApiIgnore Pageable pageable){
        Page<PedidoDto> pedidos = pedidoService.buscarPedidods(filter, pageable);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.set("totalRegistros", String.valueOf(pedidos.getTotalElements()));
        responseHeaders.set("Access-Control-Expose-Headers", "totalRegistros");

        return ResponseEntity.ok().headers(responseHeaders).body(pedidos.getContent());
    }

    @ApiOperation(httpMethod = "POST", value = "gerar um pedido", response = PedidoDto[].class)
    @PostMapping("/gerar-pedido")
    public ResponseEntity<PedidoDto> gerarPedido(@RequestBody PedidoForm pedidoForm){
        return ResponseEntity.ok().body(pedidoService.gerarPedido(pedidoForm));
    }


}
