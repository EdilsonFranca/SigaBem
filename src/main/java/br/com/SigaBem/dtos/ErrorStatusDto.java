package br.com.SigaBem.dtos;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorStatusDto {
        private int status;
        private String message;
        private Date timestamp;
}

