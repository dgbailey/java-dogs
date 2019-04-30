package com.lambdaschool.projectrestdogs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

 @ResponseStatus(value = HttpStatus.NOT_FOUND)

 //@ResponseStatus interface that marks our exception class with the status code that should be returned
 public class ResourceNotFoundException extends RuntimeException
 {
     private static final long serialVersionUID = 1L;

     public ResourceNotFoundException(String message)
     {
         super(message);
     }

     public ResourceNotFoundException(String message, Throwable cause)
     {
         super(message, cause);
     }
 }
