package br.com.SigaBem.desconto;

import br.com.SigaBem.models.Endereco;

import java.math.BigDecimal;

public class DescontoParaUfsIguais extends Desconto {

    public DescontoParaUfsIguais(Desconto proximo) {
        super(proximo);
    }

    public Util calcular(Endereco enderecoOrigem,Endereco enderecoDestino ,BigDecimal valorFrete) {
        if (enderecoOrigem.getUf().equals(enderecoDestino.getUf())) {
            return new Util(valorFrete.subtract( valorFrete.multiply(new BigDecimal("0.75"))),3);
        }
        return proximo.calcular( enderecoOrigem, enderecoDestino , valorFrete);
    }
}
