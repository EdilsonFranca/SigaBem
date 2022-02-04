package br.com.SigaBem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreteDto {
    @NotNull(message= "Por favor entre com o peso")
    @Range(min=1, max=100 , message = "o peso minimo é 1 kg e o maximo é 100 kg")
    private double peso;

    @NotBlank(message="Por favor entre com o cep de origem")
    @Size(min = 8, max = 9, message = "O CEP de origem  é inválido.")
    private String cepOrigem;

    @NotBlank(message="Por favor entre com o cep de destino")
    @Size(min = 8, max = 9, message = "O CEP de destino é inválido.")
    private String cepDestino;

    @NotBlank(message="Por favor entre com o nome do destinatario")
    private String nomeDestinatario;
}
