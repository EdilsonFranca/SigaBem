package br.com.SigaBem.desconto;

import br.com.SigaBem.models.Endereco;

import java.math.BigDecimal;

public class DescontoParaDddsIguais extends Desconto {

    public DescontoParaDddsIguais(Desconto proximo) {
        super(proximo);
    }

    public Util calcular(Endereco enderecoOrigem, Endereco enderecoDestino , BigDecimal valorFrete) {
        if (enderecoOrigem.getUf().equals(enderecoDestino.getUf())) {
            return new Util(valorFrete.subtract( valorFrete.multiply(new BigDecimal("0.5"))),1);
        }
        return proximo.calcular( enderecoOrigem, enderecoDestino , valorFrete);
    }
}
