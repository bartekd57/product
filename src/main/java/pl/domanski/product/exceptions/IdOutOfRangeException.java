package pl.domanski.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Id out of range")
public class IdOutOfRangeException extends RuntimeException {

}
