package com.example.meosasshu.exception.handler;

import com.example.meosasshu.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = createApiException(ExceptionMessage.USER_NOT_FOUND_MESSAGE, httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {DuplicateNicknameException.class})
    public ResponseEntity<Object> handleDuplicateNicknameException(DuplicateNicknameException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = createApiException(ExceptionMessage.DUPLICATE_NICKNAME_MESSAGE, httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = createApiException(ExceptionMessage.PRODUCT_NOT_FOUND_MESSAGE, httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {ReviewNotFoundException.class})
    public ResponseEntity<Object> handleReviewNotFoundException(ReviewNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = createApiException(ExceptionMessage.REVIEW_NOT_FOUND_MESSAGE, httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {OrderNotFoundException.class})
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = createApiException(ExceptionMessage.ORDER_NOT_FOUND_MESSAGE, httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {CartProductNotFoundException.class})
    public ResponseEntity<Object> handleCartProductNotFoundException(CartProductNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = createApiException(ExceptionMessage.CART_PRODUCT_NOT_FOUND_MESSAGE, httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {PermissionDeniedException.class})
    public ResponseEntity<Object> handlePermissionDeniedException(PermissionDeniedException e){
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ApiException apiException = createApiException(ExceptionMessage.PERMISSION_DENIED_MESSAGE, httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }
    private ApiException createApiException(String message, HttpStatus httpStatus) {
        return new ApiException(
                message,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
    }
}
