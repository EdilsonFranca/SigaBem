package br.com.SigaBem.services;

import br.com.SigaBem.desconto.*;
import br.com.SigaBem.dtos.FreteDto;
import br.com.SigaBem.dtos.FreteOutputDto;
import br.com.SigaBem.exception.EnderecoNotFoundException;
import br.com.SigaBem.models.Endereco;
import br.com.SigaBem.models.Frete;
import br.com.SigaBem.repositories.FreteRepositories;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class FreteServices {

    @Value("${VIACEP:https://viacep.com.br/ws/}")
    private String viacepApiURL;

    @Autowired
    private FreteRepositories freteRepositories;

    public Util calculaFrete(FreteDto freteDto) throws EnderecoNotFoundException {
            RestTemplate restTemplate = new RestTemplate();

            String urlEnderecoOrigem  = viacepApiURL + freteDto.getCepOrigem() +"/json/";
            String urlEnderecoDestino = viacepApiURL + freteDto.getCepDestino() +"/json/";

            BigDecimal valorFrete = BigDecimal.valueOf(freteDto.getPeso()).setScale(0);

            ResponseEntity<Endereco> responseOrigem  = restTemplate.getForEntity(urlEnderecoOrigem, Endereco.class);
            ResponseEntity<Endereco> responseDestino = restTemplate.getForEntity(urlEnderecoDestino, Endereco.class);

            Endereco enderecoOrigem  = responseOrigem.getBody();
            Endereco enderecoDestino = responseDestino.getBody();

            if(enderecoOrigem.getDdd() == 0){
                throw new EnderecoNotFoundException("Origem");
            }

            if(enderecoDestino.getDdd() == 0 ){
                throw new EnderecoNotFoundException("Destino");
            }

            Desconto desconto =  new DescontoParaDddsIguais(
                                 new DescontoParaUfsIguais(
                                 new SemDesconto()));

            return desconto.calcular(enderecoOrigem, enderecoDestino, valorFrete);
    }

    public FreteOutputDto create(Frete frete) {
        FreteOutputDto FreteOutputDto = new FreteOutputDto();
        frete = freteRepositories.save(frete);
        BeanUtils.copyProperties(frete, FreteOutputDto);
       return FreteOutputDto;
    }
}
