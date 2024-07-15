package org.ismetg.exception;

import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Getter
public class BlogException extends RuntimeException {
    private ErrorType errorType;

    public BlogException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public BlogException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }
}
