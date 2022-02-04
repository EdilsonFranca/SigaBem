package br.com.SigaBem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoNotFoundException extends RuntimeException {
    private String field;
}
