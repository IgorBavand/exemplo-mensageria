package com.teste.entregas.service;

import com.teste.entregas.model.Entrega;
import com.teste.entregas.repository.EntregaRepository;
import dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntregaService {
    private final EntregaRepository entregaRepository;

    public ResponseEntity<String> salvarEntrega(PedidoDto pedidoDto){
        Entrega entrega = new Entrega();
        entrega.setEnderecoEntrega(pedidoDto.getEnderecoEntrega());
        entrega.setIdPedido(pedidoDto.getIdPedido());

        log.info("registrando entrega");
        entregaRepository.save(entrega);

        return ResponseEntity.ok().body("ENTREGA_REGISTRADA");
    }

}
