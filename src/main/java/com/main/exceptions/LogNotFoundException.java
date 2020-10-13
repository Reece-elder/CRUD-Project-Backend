package com.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Log does not exist with that ID")
public class LogNotFoundException extends RuntimeException {

}
