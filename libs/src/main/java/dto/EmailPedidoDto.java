package dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class EmailPedidoDto implements Serializable {

    private String emailDestinatario;
    private String assunto;
    private UUID codigoPedido;
    private String nomeCliente;
    private String enderecoEntrega;
    private Double valorTotal;
}
