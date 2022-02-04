package br.com.SigaBem.controllers;

import javax.validation.Valid;

import br.com.SigaBem.desconto.Util;
import br.com.SigaBem.dtos.FreteDto;
import br.com.SigaBem.dtos.FreteOutputDto;
import br.com.SigaBem.exception.EnderecoNotFoundException;
import br.com.SigaBem.models.Frete;
import br.com.SigaBem.services.FreteServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/frete")
@Api(value="API REST frete")
public class FreteController {

    @Autowired
    private FreteServices freteService;

    @PostMapping
    @ApiOperation(value="Calcula o valor do frete")
    public ResponseEntity<FreteOutputDto> calculaFrete(@RequestBody @Valid FreteDto freteDto) throws EnderecoNotFoundException {
        Util calculo = freteService.calculaFrete(freteDto);

        Frete frete = new Frete();
        BeanUtils.copyProperties(freteDto, frete);

        frete.setVlTotalFrete(calculo.getDesconto());
        frete.setDataConsulta(LocalDate.now());
        frete.setDataPrevistaEntrega(LocalDate.now().plusDays(calculo.getDias()));

        FreteOutputDto freteOutputDto = freteService.create(frete);

        return new ResponseEntity<>(freteOutputDto, HttpStatus.CREATED);
    }
}

