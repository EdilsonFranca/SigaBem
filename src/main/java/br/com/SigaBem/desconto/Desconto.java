package br.com.SigaBem.desconto;

import br.com.SigaBem.models.Endereco;

import java.math.BigDecimal;

public abstract class Desconto {

    protected Desconto proximo;

    public Desconto(Desconto proximo) {
        this.proximo = proximo;
    }

    public abstract Util calcular(Endereco enderecoOrigem, Endereco enderecoDestino , BigDecimal valorFrete);
}
