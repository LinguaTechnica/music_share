package com.lotech.musicshare.lib;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(PlaylistNotFoundError.class)
    public ResponseEntity<ApiException> playlistNotFound(HttpServletRequest request, Exception exception) {
        ApiException apiException = new ApiException(exception.getMessage(), exception.getLocalizedMessage());
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlaylistInvalidError.class)
    public ResponseEntity playlistInvalidError(HttpServletRequest request, Exception exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity songNotFound(HttpServletRequest request, Exception exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
