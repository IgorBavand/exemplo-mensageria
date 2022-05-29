package com.teste.pedidos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.pedidos.model.Cliente;
import com.teste.pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void verificarCadastroClienteOk() throws Exception {
        Cliente cliente = new Cliente();

        cliente.setNomeCliente("Igor n");

        mockMvc.perform(post("/clientes/cadastrar-cliente")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk());
    }

}
