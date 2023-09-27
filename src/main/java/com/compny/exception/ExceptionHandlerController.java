package com.compny.exception;

import com.compny.exception.dto.ErrorDto;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController extends RuntimeException {
    @ExceptionHandler({ItemAlreadyExistsException.class,
            AppBadRequestException.class, PasswordNotCorrectException.class,})
    public ResponseEntity<ErrorDto> handleBadRequestException(RuntimeException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorDto(
                                request.getRequestURI(),
                                e.getMessage(),
                                400
                        )
                );
    }


    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<ErrorDto> handleNotFoundException(RuntimeException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorDto(
                                request.getRequestURI(),
                                e.getMessage(),
                                404
                        )
                );
    }

    @ExceptionHandler({EmailSendException.class})
    public ResponseEntity<ErrorDto> handleEmailNotSendException(RuntimeException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorDto(
                                request.getRequestURI(),
                                e.getMessage(),
                                500
                        )
                );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> AccessDeniedExceptionHandler(AccessDeniedException e, HttpServletRequest request) {
        return ResponseEntity.status(403)
                .body(
                        new ErrorDto(
                                request.getRequestURI(),
                                "Forbidden.",
                                403
                        )
                );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorDto> ExpiredJwtExceptionHandler(ExpiredJwtException e, HttpServletRequest request) {
        return ResponseEntity.status(401)
                .body(
                        new ErrorDto(
                                request.getRequestURI(),
                                "The Token has expired.",
                                401
                        )
                );
    }

}
