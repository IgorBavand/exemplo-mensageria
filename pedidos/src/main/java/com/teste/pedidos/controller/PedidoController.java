package com.teste.pedidos.controller;

import com.teste.pedidos.service.dto.PedidoDto;
import com.teste.pedidos.service.filter.PedidoFilter;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    @ApiOperation(httpMethod = "GET", value = "busca todos os pedidos", response = PedidoDto[].class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Página que você quer receber.(0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Número de registros por página."),
    })
    @GetMapping("/buscar-pedidos")
    public ResponseEntity<List<PedidoDto>> pedidos(PedidoFilter filter, @ApiIgnore Pageable pageable){
        return null;
    }


}
