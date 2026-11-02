package daiku.app.advice;

import com.google.firebase.auth.FirebaseAuthException;
import daiku.domain.exception.GoenBadRequestException;
import daiku.domain.exception.GoenIntegrityException;
import daiku.domain.exception.GoenNotFoundException;
import daiku.domain.entity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GoenNotFoundException.class)
    public ResponseEntity<Object> handleGoenNotFoundException(GoenNotFoundException e, WebRequest request) {
        log.error("not found!!! EXCEPTION IS: " + e.toString());
        log.error("ERROR MESSAGE=" + e.getMessage());
        if(e.getDetail() != null) {
            log.error("ERROR DATA=" + e.getDetail().toString());
        }
        return super.handleExceptionInternal(
                e,
                ErrorResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .errorCd(e.getErrorCd()).build(),
                null,
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(GoenBadRequestException.class)
    public ResponseEntity<Object> handleGoenBadRequestException(GoenBadRequestException e, WebRequest request) {
        return super.handleExceptionInternal(
                e,
                ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage()).build(),
                null,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(GoenIntegrityException.class)
    public ResponseEntity<Object> handleGoenIntegrityException(GoenIntegrityException e, WebRequest request) {
        return super.handleExceptionInternal(
                e,
                ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .errorCd(e.getErrorCd()).build(),
                null,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(FirebaseAuthException.class)
    public ResponseEntity<Object> handleFirebaseAuthException(FirebaseAuthException e, WebRequest request) {
        return super.handleExceptionInternal(
                e,
                ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .errorCd("E0002").build(),
                null,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(
                e,
                ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .errorCd("").build(),
                null,
                HttpStatus.BAD_REQUEST,
                request
        );
    }
}
