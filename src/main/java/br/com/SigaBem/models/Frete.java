package br.com.SigaBem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="frete")
public class Frete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_frete;
    private double peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;
    private BigDecimal vlTotalFrete;
    private LocalDate dataPrevistaEntrega;
    private LocalDate dataConsulta;
}
