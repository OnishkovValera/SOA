package org.onishkovvalery.vehicleservice.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class VehicleControllerAdvice {
    @ExceptionHandler(HttpMessageConversionException::class)
    fun onMessageConvertError(error: HttpMessageConversionException) = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, error.message)

    @ExceptionHandler(IllegalArgumentException::class)
    fun onIllegalArgument(error: IllegalArgumentException) = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, error.message)
}