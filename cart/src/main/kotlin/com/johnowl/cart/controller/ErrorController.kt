package com.johnowl.cart.controller

import com.johnowl.cart.model.CartNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

class ApiError(val message: String)

@ControllerAdvice
class ErrorController {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(CartNotFoundException::class)
    fun handleError(exception: CartNotFoundException): ResponseEntity<ApiError> {
        return ResponseEntity(ApiError(exception.message.toString()), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleError(exception: Exception): ResponseEntity<ApiError> {
        val message = "An error ocurred."
        logger.error(message, exception)
        return ResponseEntity(ApiError(message), HttpStatus.INTERNAL_SERVER_ERROR)
    }

}