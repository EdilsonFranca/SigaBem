package br.com.SigaBem.desconto;
import br.com.SigaBem.models.Endereco;
import java.math.BigDecimal;

public class SemDesconto extends Desconto {

    public SemDesconto() {
        super(null);
    }

    public Util calcular(Endereco enderecoOrigem, Endereco enderecoDestino , BigDecimal valorFrete) {
        return new Util(valorFrete,10);
    }
}
