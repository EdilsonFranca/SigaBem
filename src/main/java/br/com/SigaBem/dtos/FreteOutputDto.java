package br.com.SigaBem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreteOutputDto {
    private String cepOrigem;
    private String cepDestino;
    private BigDecimal vlTotalFrete;
    private LocalDate dataPrevistaEntrega;
}
