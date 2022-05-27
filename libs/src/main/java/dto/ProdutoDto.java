package dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProdutoDto implements Serializable {

    private Long codigoProduto;
    private String nomeProduto;
}
