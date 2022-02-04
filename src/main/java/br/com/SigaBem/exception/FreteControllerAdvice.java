package br.com.SigaBem.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.SigaBem.dtos.DtoError;
import br.com.SigaBem.dtos.ErrorStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(basePackages = "br.com.SigaBem.controllers")
@RestControllerAdvice
public class FreteControllerAdvice {

    @Autowired
    private MessageSource mensagemSource;

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EnderecoNotFoundException.class)
    public ErrorStatusDto handleEnderecoNotFound(EnderecoNotFoundException enderecoNotFoundException) {
        ErrorStatusDto errorDTO = new ErrorStatusDto();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("endereço de "+enderecoNotFoundException.getField()+" não encontrado.");
        errorDTO.setTimestamp(new Date());
        return errorDTO;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
        public List<DtoError> handle(MethodArgumentNotValidException exception) {
        List<DtoError> dtoError = new ArrayList<>();
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
        fieldError.forEach(e -> {
            String mensagem = mensagemSource.getMessage(e, LocaleContextHolder.getLocale());
            DtoError error = new DtoError(e.getField(), mensagem);
            dtoError.add(error);
        });
        return dtoError;
    }
}