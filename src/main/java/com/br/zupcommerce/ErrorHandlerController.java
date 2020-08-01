package com.br.zupcommerce;

import com.br.zupcommerce.produtos.InvalidProdutoException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ErrorHandlerController {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({InvalidProdutoException.class})
    @ResponseBody
    public String handleProdutoNotValid(InvalidProdutoException ex) {
        return "Error: " + ex.getMessage();
    }
}
